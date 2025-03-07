package d.zhdanov.ccfit.nsu.workers.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table("engineer_position")
public class EngineerPositionEntity {
  @Id
  private Integer id;
  private String  name;
}
