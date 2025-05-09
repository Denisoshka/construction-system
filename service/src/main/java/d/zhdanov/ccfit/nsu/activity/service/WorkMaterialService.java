package d.zhdanov.ccfit.nsu.activity.service;

import d.zhdanov.ccfit.nsu.activity.exceptions.MaterialUsageAbsent;
import d.zhdanov.ccfit.nsu.activity.exceptions.WorkTypeAbsent;
import d.zhdanov.ccfit.nsu.activity.mapper.WorkMaterialMapper;
import d.zhdanov.ccfit.nsu.activity.persistence.ManufacturerRepository;
import d.zhdanov.ccfit.nsu.activity.persistence.MaterialTypeRepository;
import d.zhdanov.ccfit.nsu.activity.persistence.MaterialUsageRepository;
import d.zhdanov.ccfit.nsu.activity.persistence.WorkTypeRepository;
import d.zhdanov.ccfit.nsu.objects.exceptions.ManufacturerAbsent;
import d.zhdanov.ccfit.nsu.objects.exceptions.MaterialTypeAbsent;
import d.zhdanov.ccfit.nsu.utils.Utils;
import d.zhdanov.graphql.types.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class WorkMaterialService {
  private final MaterialTypeRepository  materialTypeRepository;
  private final ManufacturerRepository  manufacturerRepository;
  private final MaterialUsageRepository materialUsageRepository;
  private final WorkTypeRepository      workTypeRepository;
  private final WorkMaterialMapper      workMaterialMapper;
  
  public WorkMaterialService(
    @Autowired MaterialTypeRepository materialTypeRepository,
    @Autowired ManufacturerRepository manufacturerRepository,
    @Autowired WorkMaterialMapper workMaterialMapper,
    @Autowired MaterialUsageRepository materialUsageRepository,
    @Autowired WorkTypeRepository workTypeRepository
  ) {
    this.materialTypeRepository  = materialTypeRepository;
    this.manufacturerRepository  = manufacturerRepository;
    this.materialUsageRepository = materialUsageRepository;
    this.workTypeRepository      = workTypeRepository;
    this.workMaterialMapper      = workMaterialMapper;
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  public List<Material> findAllMaterialTypes(final Pagination pagination) {
    final var paged = Utils.getPageable(pagination);
    final var ret =
      materialTypeRepository.findMaterialsWithManufacturer(
        paged.getOffset(),
        paged.getPageSize()
      );
    return workMaterialMapper.toMaterial(ret);
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  public Material findMaterialType(final UUID id) {
    final var ret = materialTypeRepository.findMaterialsWithManufacturer(id)
      .orElseThrow(MaterialTypeAbsent::new);
    return workMaterialMapper.toMaterial(ret);
  }
  
  @PreAuthorize("hasRole('SITE_MANAGER')")
  @Transactional
  public Material createMaterialType(final MaterialInput input) {
    final var entity = workMaterialMapper.toMaterialTypeEntity(input);
    final var ret    = materialTypeRepository.save(entity);
    return workMaterialMapper.toMaterial(ret);
  }
  
  @PreAuthorize("hasRole('SITE_MANAGER')")
  @Transactional
  public void deleteMaterial(final UUID id) {
    materialTypeRepository.deleteById(id);
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  public Manufacturer findManufacturer(final UUID id) {
    final var ret =
      manufacturerRepository.findById(id).orElseThrow(ManufacturerAbsent::new);
    return workMaterialMapper.toManufacturer(ret);
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  public List<Manufacturer> findAllManufacturers(final Pagination pagination) {
    final var paged = Utils.getPageable(pagination);
    final var ret   = manufacturerRepository.findAll(paged).toList();
    return workMaterialMapper.toManufacturer(ret);
  }
  
  @PreAuthorize("hasRole('SITE_MANAGER')")
  @Transactional
  public Manufacturer createManufacturer(ManufacturerInput input) {
    final var entity = workMaterialMapper.toManufacturerEntity(input);
    final var ret    = manufacturerRepository.save(entity);
    return workMaterialMapper.toManufacturer(ret);
  }
  
  @PreAuthorize("hasRole('SITE_MANAGER')")
  @Transactional
  public void deleteManufacturer(final UUID id) {
    manufacturerRepository.deleteById(id);
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  public Iterable<MaterialUsage> findAllMaterialUsageByScheduleUnit(
    final UUID workUnitId, final Pagination pagination) {
    final var paged = Utils.getPageable(pagination);
    final var ret =
      materialUsageRepository.findMaterialUsageWithDetails(
        workUnitId,
        paged.getOffset(),
        paged.getPageSize()
      );
    return workMaterialMapper.toMaterialUsage(ret);
  }
  
  @PreAuthorize("hasAnyRole('ENGINEER, FOREMEN')")
  public Iterable<MaterialUsage> findMaterialsOverruns(
    Pagination pagination, MaterialUsageFilter filter) {
    final var paged = Utils.getPageable(pagination);
    final var filterEntity = workMaterialMapper.toMaterialUsageFilter(filter);
    final var ret =
      materialUsageRepository.findMaterialUsageWithDetails(
        filterEntity.getSiteId(),
        filterEntity.getContractId(),
        paged.getOffset(),
        paged.getPageSize()
      );
    return workMaterialMapper.toMaterialUsage(ret);
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  public List<WorkType> findAllWorkTypes(
    final Pagination pagination
  ) {
    final var paged = Utils.getPageable(pagination);
    final var ret   = workTypeRepository.findAll(paged).toList();
    return workMaterialMapper.toWorkType(ret);
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  public WorkType findWorkType(UUID id) {
    final var ret =
      workTypeRepository.findById(id).orElseThrow(WorkTypeAbsent::new);
    return workMaterialMapper.toWorkType(ret);
  }
  
  @PreAuthorize("hasAnyRole('ENGINEER, FOREMEN')")
  public Boolean deleteMaterials(List<UUID> matUUIDs) {
    materialUsageRepository.deleteAllById(matUUIDs);
    return true;
  }
  
  @PreAuthorize("hasAnyRole('ENGINEER, FOREMEN')")
  @Transactional
  public Iterable<MaterialUsage> addWorkMaterialsUsage(
    UUID uuid,
    List<MaterialUsageInput> materials
  ) {
    final var forSave = workMaterialMapper.toMaterialUsageEntityList(materials);
    forSave.forEach(e -> e.setWorkUnitId(uuid));
    final var ret = materialUsageRepository.saveAll(forSave);
    return workMaterialMapper.toMaterialUsage(ret);
  }
  
  @PreAuthorize("hasAnyRole('ENGINEER, FOREMEN')")
  @Transactional
  public MaterialUsage updateWorkMaterialUsage(
    UUID id, MaterialUsageInput input) {
    final var saved =
      materialUsageRepository.findById(id)
        .orElseThrow(MaterialUsageAbsent::new);
    workMaterialMapper.updateMaterialUsage(saved, input);
    final var ret = materialUsageRepository.save(saved);
    return workMaterialMapper.toMaterialUsage(ret);
  }
}
