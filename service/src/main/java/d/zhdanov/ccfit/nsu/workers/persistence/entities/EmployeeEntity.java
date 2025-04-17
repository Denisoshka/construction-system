package d.zhdanov.ccfit.nsu.workers.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Table("employees")
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity {
  @Id
  private UUID id;
  
  private String    systemId;
  private String    name;
  private String    surname;
  private String    patronymic;
  private LocalDate employmentDate;
  private JobPost   post;
  
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

