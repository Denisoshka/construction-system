package d.zhdanov.ccfit.nsu.activity.mapper;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.*;
import d.zhdanov.graphql.types.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING
)
public interface WorkMaterialMapper {
  MaterialTypeEntity toMaterialTypeEntity(MaterialInput materialInput);
  
  @Mapping(target = "manufacturer", source = "manufacturerEntity")
  Material toMaterial(MaterialTypeEntity materialTypeEntity);
  
  List<Material> toMaterial(
    List<MaterialTypeEntity> materialTypeEntities
  );
  
  ManufacturerEntity toManufacturerEntity(ManufacturerInput manufacturerInput);
  
  Manufacturer toManufacturer(ManufacturerEntity manufacturerEntity);
  
  List<Manufacturer> toManufacturer(
    List<ManufacturerEntity> manufacturerEntities
  );
  
  @Mapping(target = "material", source = "materialType")
  MaterialUsage toMaterialUsage(MaterialUsageEntity entity);
  
  Iterable<MaterialUsage> toMaterialUsage(
    Iterable<MaterialUsageEntity> materialUsageEntities
  );
  
  WorkType toWorkType(WorkTypeEntity entity);
  
  List<WorkType> toWorkType(List<WorkTypeEntity> workTypeEntities);
  
  @Mapping(target = "id", ignore = true)
  MaterialUsageEntity toMaterialUsageEntity(
    MaterialUsageInput materialUsageInput
  );
  
  List<MaterialUsageEntity> toMaterialUsageEntityList(
    List<MaterialUsageInput> materialUsageInputs
  );
  
  @Mapping(target = "id", ignore = true)
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateMaterialUsage(
    @MappingTarget MaterialUsageEntity entity,
    MaterialUsageInput input
  );
  
  MaterialUsageFilterEntity toMaterialUsageFilter(MaterialUsageFilter usageFilter);
}
