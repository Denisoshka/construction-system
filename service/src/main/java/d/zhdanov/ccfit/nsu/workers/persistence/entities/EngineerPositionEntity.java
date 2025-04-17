package d.zhdanov.ccfit.nsu.workers.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
@Table("engineer_position")
public class EngineerPositionEntity {
  @Id
  private Integer id;
  private String  name;
  
  public static EngineerPositionEntity of(
    final ResultSet rs,
    final Integer positionId
  ) throws SQLException {
    return new EngineerPositionEntity(
      positionId,
      rs.getString("engineer_position_name")
    );
  }
}
