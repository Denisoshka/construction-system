package d.zhdanov.ccfit.nsu.utils.persistence.employees;

import d.zhdanov.ccfit.nsu.workers.persistence.entities.EmployeeEntity;
import d.zhdanov.ccfit.nsu.workers.persistence.entities.WorkerEntity;
import d.zhdanov.ccfit.nsu.workers.persistence.entities.WorkerPositionEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class WorkerRowMapper implements RowMapper<WorkerEntity> {
  
  @Override
  public WorkerEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
    UUID    employeeId = rs.getObject("employee_id", UUID.class);
    Integer positionId = rs.getObject("worker_position_id", Integer.class);
    final var employee       = EmployeeEntity.of(rs, employeeId);
    final var workerPosition = WorkerPositionEntity.of(rs, positionId);
    
    return new WorkerEntity(employeeId, positionId, employee, workerPosition);
  }
}
