package d.zhdanov.ccfit.nsu.workers.mapper;

import d.zhdanov.ccfit.nsu.workers.persistence.entities.EngineerPositionEntity;
import d.zhdanov.graphql.types.EngineerPosition;
import d.zhdanov.graphql.types.EngineerPositionInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface EngineersMapper {
  EngineerPosition fromEngineerPositionEntity(final EngineerPositionEntity entity);
  
  @Mapping(target = "id", ignore = true)
  EngineerPositionEntity toEngineerPositionEntity(final EngineerPositionInput input);
  
  @Mapping(target = "id", expression = "java(Id)")
  EngineerPositionEntity toEngineerPositionDTOWithID(
    final EngineerPositionEntity dto,
    final int Id
  );
}
