package d.zhdanov.ccfit.nsu.activity.controller;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import d.zhdanov.ccfit.nsu.activity.service.WorkMaterialService;
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
  
  @DgsQuery
  public List<MaterialUsage> workMaterials(
    @InputArgument String scheduleUnitID,
    @InputArgument Pagination pagination
  ) {
    final var uuid = UUID.fromString(scheduleUnitID);
    return workMaterialService.findAllMaterialUsageByScheduleUnit(uuid, pagination);
  }
  
  @DgsQuery
  public List<WorkType> workTypes(@InputArgument Pagination pagination){
    return workMaterialService.findAllWorkTypes(pagination);
  }
}
