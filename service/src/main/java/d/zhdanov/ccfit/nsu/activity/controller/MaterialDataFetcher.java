package d.zhdanov.ccfit.nsu.activity.controller;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import d.zhdanov.ccfit.nsu.activity.service.MaterialService;
import d.zhdanov.graphql.types.*;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.UUID;

@DgsComponent
public class MaterialDataFetcher {
  private final MaterialService materialService;
  
  public MaterialDataFetcher(MaterialService materialService) {
    this.materialService = materialService;
  }
  
  @DgsQuery
  public Material materialType(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    return materialService.findMaterialType(uuid);
  }
  
  @DgsQuery
  public List<Material> materialTypes(@InputArgument Pagination pagination) {
    return materialService.findAllMaterialTypes(pagination);
  }
  
  @DgsMutation
  public Material createMaterialType(@InputArgument MaterialInput input) {
    return materialService.createMaterialType(input);
  }
  
  @DgsMutation
  public Boolean deleteMaterialType(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    materialService.deleteMaterial(uuid);
    return true;
  }
  
  @DgsQuery
  public Manufacturer manufacturer(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    return materialService.findManufacturer(uuid);
  }
  
  @DgsQuery
  public List<Manufacturer> manufacturers(
    @InputArgument Pagination pagination
  ) {
    return materialService.findAllManufacturers(pagination);
  }
  
  @DgsMutation
  public Manufacturer createManufacturer(
    @InputArgument ManufacturerInput input
  ) {
    return materialService.createManufacturer(input);
  }
  
  @DgsMutation
  Boolean deleteManufacturer(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    materialService.deleteManufacturer(uuid);
    return true;
  }
  
  @DgsQuery
  public List<MaterialUsage> workMaterials(
    @InputArgument String scheduleUnitID,
    @InputArgument Pagination pagination
  ) {
    final var uuid = UUID.fromString(scheduleUnitID);
    return materialService.findAllMaterialUsageByScheduleUnit(uuid, pagination);
  }
}
