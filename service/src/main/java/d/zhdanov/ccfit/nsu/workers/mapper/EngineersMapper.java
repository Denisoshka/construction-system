package d.zhdanov.ccfit.nsu.workers.mapper;

import d.zhdanov.ccfit.nsu.workers.persistence.entities.EngineerEntity;
import d.zhdanov.ccfit.nsu.workers.persistence.entities.EngineerPositionEntity;
import d.zhdanov.graphql.types.EngineerInfo;
import d.zhdanov.graphql.types.EngineerPosition;
import d.zhdanov.graphql.types.EngineerPositionInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING, uses = EmployeeMapper.class
)
public interface EngineersMapper {
  @Mapping(target = "employee", source = "employee")
  EngineerInfo fromEngineerEntityWithAdditionalData(final EngineerEntity dto);
  
  List<EngineerInfo> fromEngineerEntityList(final List<EngineerEntity> dtoList);
  
  EngineerPosition fromEngineerPositionEntity(final EngineerPositionEntity entity);
  
  List<EngineerPosition> fromEngineerPositionEntityList(final List<EngineerPositionEntity> dtoList);
  
  void updateEngineerPositionEntity(
    @MappingTarget final EngineerPositionEntity entity,
    final EngineerPositionInput input
  );
}
