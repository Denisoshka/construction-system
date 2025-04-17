package d.zhdanov.ccfit.nsu.utils.persistence.employees;

import d.zhdanov.ccfit.nsu.workers.persistence.entities.EmployeeEntity;
import d.zhdanov.ccfit.nsu.workers.persistence.entities.EngineerEntity;
import d.zhdanov.ccfit.nsu.workers.persistence.entities.EngineerPositionEntity;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class EngineerRowMapper implements RowMapper<EngineerEntity> {
  
  @Override
  public EngineerEntity mapRow(ResultSet rs, int rowNum) throws SQLException {
    UUID    employeeId = rs.getObject("employee_id", UUID.class);
    Integer positionId = rs.getObject("engineer_position_id", Integer.class);
    final var employee         = EmployeeEntity.of(rs, employeeId);
    final var engineerPosition = EngineerPositionEntity.of(rs, positionId);
    
    
    return new EngineerEntity(
      employeeId, positionId, employee, engineerPosition);
  }
}
