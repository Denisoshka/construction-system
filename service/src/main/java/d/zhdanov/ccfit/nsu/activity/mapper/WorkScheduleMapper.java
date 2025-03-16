package d.zhdanov.ccfit.nsu.activity.mapper;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.WorkScheduleEntity;
import d.zhdanov.graphql.types.WorkScheduleUnit;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;
@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING
)
public interface WorkScheduleMapper {
  WorkScheduleUnit toWorkScheduleUnit(WorkScheduleEntity entity);
  
  List<WorkScheduleUnit> toWorkScheduleUnitList(
    List<WorkScheduleEntity> workScheduleEntities
  );
}
