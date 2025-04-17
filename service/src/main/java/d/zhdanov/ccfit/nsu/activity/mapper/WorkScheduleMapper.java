package d.zhdanov.ccfit.nsu.activity.mapper;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.WorkScheduleEntity;
import d.zhdanov.ccfit.nsu.objects.mappers.BrigadeMapper;
import d.zhdanov.graphql.types.WorkScheduleUnit;
import d.zhdanov.graphql.types.WorkScheduleUnitInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING,
  uses = {BrigadeMapper.class}
)
public interface WorkScheduleMapper {
  WorkScheduleUnit toWorkScheduleUnit(WorkScheduleEntity entity);
  
  List<WorkScheduleUnit> toWorkScheduleUnitList(
    List<WorkScheduleEntity> workScheduleEntities
  );
  
  @Mapping(target = "id", ignore = true)
  WorkScheduleEntity toWorkScheduleEntity(WorkScheduleUnitInput input);
  
  List<WorkScheduleEntity> toWorkScheduleEntityList(
    List<WorkScheduleUnitInput> input
  );
}
