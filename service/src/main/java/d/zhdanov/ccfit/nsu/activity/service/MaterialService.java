package d.zhdanov.ccfit.nsu.activity.service;

import d.zhdanov.ccfit.nsu.activity.mapper.MaterialMapper;
import d.zhdanov.ccfit.nsu.activity.persistence.ManufacturerRepository;
import d.zhdanov.ccfit.nsu.activity.persistence.MaterialTypeRepository;
import d.zhdanov.ccfit.nsu.activity.persistence.MaterialUsageRepository;
import d.zhdanov.ccfit.nsu.utils.Utils;
import d.zhdanov.ccfit.nsu.objects.exceptions.ManufacturerAbsent;
import d.zhdanov.ccfit.nsu.objects.exceptions.MaterialTypeAbsent;
import d.zhdanov.graphql.types.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
public class MaterialService {
  private final MaterialTypeRepository  materialTypeRepository;
  private final ManufacturerRepository  manufacturerRepository;
  private final MaterialUsageRepository materialUsageRepository;
  private final MaterialMapper          materialMapper;
  
  public MaterialService(
    @Autowired MaterialTypeRepository materialTypeRepository,
    @Autowired ManufacturerRepository manufacturerRepository,
    @Autowired MaterialMapper materialMapper,
    @Autowired MaterialUsageRepository materialUsageRepository
  ) {
    this.materialTypeRepository  = materialTypeRepository;
    this.manufacturerRepository  = manufacturerRepository;
    this.materialUsageRepository = materialUsageRepository;
    this.materialMapper          = materialMapper;
  }
  
  @PreAuthorize("hasAnyAuthority('EMPLOYEE')")
  public List<Material> findAllMaterialTypes(final Pagination pagination) {
    final var paged = Utils.getPageable(pagination);
    final var ret   = materialTypeRepository.findAll(paged).toList();
    return materialMapper.fromMaterialTypeEntityList(ret);
  }
  
  @PreAuthorize("hasAnyAuthority('EMPLOYEE')")
  public Material findMaterialType(final UUID id) {
    final var ret = materialTypeRepository.findById(id).orElseThrow(
      MaterialTypeAbsent::new);
    return materialMapper.fromMaterialTypeEntity(ret);
  }
  
  @PreAuthorize("hasRole('SITE_MANAGER')")
  @Transactional
  public Material createMaterialType(final MaterialInput input) {
    final var entity = materialMapper.toMaterialTypeEntity(input);
    final var ret    = materialTypeRepository.save(entity);
    return materialMapper.fromMaterialTypeEntity(ret);
  }
  
  @PreAuthorize("hasRole('SITE_MANAGER')")
  @Transactional
  public void deleteMaterial(final UUID id) {
    materialTypeRepository.deleteById(id);
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  public Manufacturer findManufacturer(final UUID id) {
    final var ret = manufacturerRepository.findById(id).orElseThrow(
      ManufacturerAbsent::new);
    return materialMapper.fromManufacturerEntity(ret);
  }
  
  @PreAuthorize("hasAnyAuthority('EMPLOYEE')")
  public List<Manufacturer> findAllManufacturers(final Pagination pagination) {
    final var paged = Utils.getPageable(pagination);
    final var ret   = manufacturerRepository.findAll(paged).toList();
    return materialMapper.fromManufacturerEntityList(ret);
  }
  
  @PreAuthorize("hasAnyAuthority('MANAGER')")
  @Transactional
  public Manufacturer createManufacturer(ManufacturerInput input) {
    final var entity = materialMapper.toManufacturerEntity(input);
    final var ret    = manufacturerRepository.save(entity);
    return materialMapper.fromManufacturerEntity(ret);
  }
  
  @PreAuthorize("hasAnyAuthority('MANAGER')")
  @Transactional
  public void deleteManufacturer(final UUID id) {
    manufacturerRepository.deleteById(id);
  }
  
  @PreAuthorize("hasAnyAuthority('EMPLOYEE')")
  public List<MaterialUsage> findAllMaterialUsageByScheduleUnit(
    final UUID workUnitId,
    final Pagination pagination
  ) {
    final var paged = Utils.getPageable(pagination);
    final var ret = materialUsageRepository.findByWorkUnitId(
      workUnitId,
      paged
    );
    return materialMapper.fromMaterialUsageEntityList(ret);
  }
}
