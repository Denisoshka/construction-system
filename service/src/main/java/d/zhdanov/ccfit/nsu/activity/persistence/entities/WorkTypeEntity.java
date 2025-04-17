package d.zhdanov.ccfit.nsu.activity.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Data
@Table("work_type")
@AllArgsConstructor
@NoArgsConstructor
public class WorkTypeEntity {
  private UUID   id;
  private String name;
  
  public static WorkTypeEntity of(@NotNull ResultSet rs) throws SQLException {
    WorkTypeEntity workType = new WorkTypeEntity();
    workType.setId(rs.getObject("wt_id", UUID.class));
    workType.setName(rs.getString("wt_name"));
    return workType;
  }
}
