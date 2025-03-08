package d.zhdanov.ccfit.nsu.activity.service;

import d.zhdanov.ccfit.nsu.activity.mapper.MaterialMapper;
import d.zhdanov.ccfit.nsu.activity.persistence.ManufacturerRepository;
import d.zhdanov.ccfit.nsu.activity.persistence.MaterialTypeRepository;
import d.zhdanov.ccfit.nsu.objects.exceptions.ManufacturerAbsent;
import d.zhdanov.ccfit.nsu.objects.exceptions.MaterialTypeAbsent;
import d.zhdanov.ccfit.nsu.util.Utils;
import d.zhdanov.graphql.types.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
public class MaterialService {
  private final MaterialTypeRepository materialTypeRepository;
  private final ManufacturerRepository manufacturerRepository;
  private final MaterialMapper         materialMapper;
  
  public MaterialService(
    @Autowired MaterialTypeRepository materialTypeRepository,
    @Autowired ManufacturerRepository manufacturerRepository,
    @Autowired MaterialMapper materialMapper
  ) {
    this.materialTypeRepository = materialTypeRepository;
    this.manufacturerRepository = manufacturerRepository;
    this.materialMapper         = materialMapper;
  }
  
  public List<Material> getAllMaterialTypes(final Pagination pagination) {
    final var paged = Utils.getPageable(pagination);
    final var ret   = materialTypeRepository.findAll(paged).toList();
    return materialMapper.fromMaterialTypeEntityList(ret);
  }
  
  public Material getMaterialType(final UUID id) {
    final var ret = materialTypeRepository.findById(id).orElseThrow(
      MaterialTypeAbsent::new);
    return materialMapper.fromMaterialTypeEntity(ret);
  }
  
  @Transactional
  public Material createMaterialType(final MaterialInput input) {
    final var entity = materialMapper.toMaterialTypeEntity(input);
    final var ret    = materialTypeRepository.save(entity);
    return materialMapper.fromMaterialTypeEntity(ret);
  }
  
  @Transactional
  public void deleteMaterial(final UUID id) {
    materialTypeRepository.deleteById(id);
  }
  
  public Manufacturer getManufacturer(final UUID id) {
    final var ret = manufacturerRepository.findById(id).orElseThrow(
      ManufacturerAbsent::new);
    return materialMapper.fromManufacturerEntity(ret);
  }
  
  public List<Manufacturer> getAllManufacturers(final Pagination pagination) {
    final var paged = Utils.getPageable(pagination);
    final var ret   = manufacturerRepository.findAll(paged).toList();
    return materialMapper.fromManufacturerEntityList(ret);
  }
  
  @Transactional
  public Manufacturer createManufacturer(ManufacturerInput input) {
    final var entity = materialMapper.toManufacturerEntity(input);
    final var ret    = manufacturerRepository.save(entity);
    return materialMapper.fromManufacturerEntity(ret);
  }
  
  @Transactional
  public void deleteManufacturer(final UUID id) {
    manufacturerRepository.deleteById(id);
  }
}
