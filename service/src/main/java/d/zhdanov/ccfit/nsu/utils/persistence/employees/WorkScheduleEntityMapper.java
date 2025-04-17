package d.zhdanov.ccfit.nsu.utils.persistence.employees;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.WorkScheduleEntity;
import d.zhdanov.ccfit.nsu.activity.persistence.entities.WorkTypeEntity;
import d.zhdanov.ccfit.nsu.objects.persistence.entities.BrigadeEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkScheduleEntityMapper implements RowMapper<WorkScheduleEntity> {
  @Override
  public WorkScheduleEntity mapRow(@NotNull ResultSet rs, int rowNum)
  throws SQLException {
    final var workScheduleEntity = WorkScheduleEntity.of(rs);
    final var workType           = WorkTypeEntity.of(rs);
    final var brigade            = BrigadeEntity.of(rs);
    workScheduleEntity.setWorkType(workType);
    workScheduleEntity.setBrigade(brigade);
    
    return workScheduleEntity;
  }
}
