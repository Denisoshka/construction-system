package d.zhdanov.ccfit.nsu.activity.mapper;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.ManufacturerEntity;
import d.zhdanov.ccfit.nsu.activity.persistence.entities.MaterialTypeEntity;
import d.zhdanov.ccfit.nsu.activity.persistence.entities.MaterialUsageEntity;
import d.zhdanov.ccfit.nsu.activity.persistence.entities.WorkTypeEntity;
import d.zhdanov.graphql.types.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING
)
public interface WorkMaterialMapper {
  MaterialTypeEntity toMaterialTypeEntity(MaterialInput materialInput);
  
  @Mapping(target = "manufacturer", source = "manufacturerEntity")
  Material fromMaterialTypeEntity(MaterialTypeEntity materialTypeEntity);
  
  List<Material> fromMaterialTypeEntityList(
    List<MaterialTypeEntity> materialTypeEntities
  );
  
  ManufacturerEntity toManufacturerEntity(ManufacturerInput manufacturerInput);
  
  Manufacturer fromManufacturerEntity(ManufacturerEntity manufacturerEntity);
  
  List<Manufacturer> fromManufacturerEntityList(
    List<ManufacturerEntity> manufacturerEntities
  );
  
  @Mapping(target = "material", source = "materialType")
  MaterialUsage toMaterialUsage(MaterialUsageEntity entity);
  
  List<MaterialUsage> fromMaterialUsageEntityList(
    List<MaterialUsageEntity> materialUsageEntities
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
  
//  MaterialUsage toMaterialUsage(MaterialUsageEntity entity);
  
}
