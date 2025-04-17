package d.zhdanov.ccfit.nsu.workers.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
@Table("worker_position")
public class WorkerPositionEntity {
  @Id
  private Integer id;
  private String  name;
  
  public static WorkerPositionEntity of(
    final ResultSet rs,
    final Integer positionId
  ) throws SQLException {
    return new WorkerPositionEntity(
      positionId,
      rs.getString("worker_position_name")
    );
  }
}
