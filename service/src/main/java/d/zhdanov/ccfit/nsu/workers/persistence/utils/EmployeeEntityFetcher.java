package d.zhdanov.ccfit.nsu.workers.persistence.utils;

import d.zhdanov.ccfit.nsu.workers.persistence.entities.EmployeeEntity;
import d.zhdanov.ccfit.nsu.workers.persistence.entities.JobPost;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class EmployeeEntityFetcher {
  protected EmployeeEntityFetcher() {
  }
  
  public static EmployeeEntity of(final ResultSet rs, final UUID employeeId)
  throws SQLException {
    return new EmployeeEntity(
      employeeId,
      rs.getString("system_id"),
      rs.getString("name"),
      rs.getString("surname"),
      rs.getString("patronymic"),
      rs.getDate("employment_date").toLocalDate(),
      JobPost.valueOf(rs.getString("post"))
    );
  }
}
