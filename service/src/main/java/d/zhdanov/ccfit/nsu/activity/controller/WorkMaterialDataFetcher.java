package d.zhdanov.ccfit.nsu.activity.controller;

import com.netflix.graphql.dgs.*;
import d.zhdanov.ccfit.nsu.activity.service.WorkMaterialService;
import d.zhdanov.graphql.DgsConstants;
import d.zhdanov.graphql.types.*;

import java.util.List;
import java.util.UUID;

@DgsComponent
public class WorkMaterialDataFetcher {
  private final WorkMaterialService workMaterialService;
  
  public WorkMaterialDataFetcher(WorkMaterialService workMaterialService) {
    this.workMaterialService = workMaterialService;
  }
  
  @DgsQuery
  public Material materialType(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    return workMaterialService.findMaterialType(uuid);
  }
  
  @DgsQuery
  public List<Material> materialTypes(@InputArgument Pagination pagination) {
    return workMaterialService.findAllMaterialTypes(pagination);
  }
  
  @DgsMutation
  public Material createMaterialType(@InputArgument MaterialInput input) {
    return workMaterialService.createMaterialType(input);
  }
  
  @DgsMutation
  public Boolean deleteMaterialType(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    workMaterialService.deleteMaterial(uuid);
    return true;
  }
  
  @DgsQuery
  public Manufacturer manufacturer(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    return workMaterialService.findManufacturer(uuid);
  }
  
  @DgsQuery
  public List<Manufacturer> manufacturers(
    @InputArgument Pagination pagination
  ) {
    return workMaterialService.findAllManufacturers(pagination);
  }
  
  @DgsMutation
  public Manufacturer createManufacturer(
    @InputArgument ManufacturerInput input
  ) {
    return workMaterialService.createManufacturer(input);
  }
  
  @DgsMutation
  Boolean deleteManufacturer(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    workMaterialService.deleteManufacturer(uuid);
    return true;
  }
  
  @DgsMutation
  public Boolean deleteWorkMaterials(@InputArgument List<String> materials) {
    final var matUUIDs = materials.stream().map(UUID::fromString).toList();
    return workMaterialService.deleteMaterials(matUUIDs);
  }
  
  @DgsMutation
  public boolean addWorkMaterials(
    @InputArgument String workUnitId,
    @InputArgument List<MaterialUsageInput> materials
  ) {
    final var uuid = UUID.fromString(workUnitId);
    return workMaterialService.addWorkMaterials(uuid, materials);
  }
  
  @DgsQuery
  public List<MaterialUsage> workMaterials(
    @InputArgument String scheduleUnitID,
    @InputArgument Pagination pagination
  ) {
    final var uuid = UUID.fromString(scheduleUnitID);
    return workMaterialService.findAllMaterialUsageByScheduleUnit(
      uuid, pagination);
  }
  
  @DgsQuery
  public List<WorkType> workTypes(@InputArgument Pagination pagination) {
    return workMaterialService.findAllWorkTypes(pagination);
  }
  
  @DgsQuery
  public WorkType workType(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    return workMaterialService.findWorkType(uuid);
  }
  
  @DgsData(
    parentType = DgsConstants.WORKSCHEDULEUNIT.TYPE_NAME, field = DgsConstants.WORKSCHEDULEUNIT.WorkType
  )
  public WorkType workScheduleUnitWorkTypeInfo(DgsDataFetchingEnvironment dfe) {
    final WorkScheduleUnit workScheduleUnit = dfe.getSource();
    return workType(workScheduleUnit.getWorkTypeId());
  }
}
