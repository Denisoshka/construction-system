package d.zhdanov.ccfit.nsu.activity.mapper;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.ManufacturerEntity;
import d.zhdanov.ccfit.nsu.activity.persistence.entities.MaterialTypeEntity;
import d.zhdanov.graphql.types.Manufacturer;
import d.zhdanov.graphql.types.ManufacturerInput;
import d.zhdanov.graphql.types.Material;
import d.zhdanov.graphql.types.MaterialInput;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING
)
public interface MaterialMapper {
  MaterialTypeEntity toMaterialTypeEntity(MaterialInput materialInput);
  
  Material fromMaterialTypeEntity(MaterialTypeEntity materialTypeEntity);
  
  List<Material> fromMaterialTypeEntityList(List<MaterialTypeEntity> materialTypeEntities);
  
  ManufacturerEntity toManufacturerEntity(ManufacturerInput manufacturerInput);
  
  Manufacturer fromManufacturerEntity(ManufacturerEntity manufacturerEntity);
  
  List<Manufacturer> fromManufacturerEntityList(List<ManufacturerEntity> manufacturerEntities);
}
