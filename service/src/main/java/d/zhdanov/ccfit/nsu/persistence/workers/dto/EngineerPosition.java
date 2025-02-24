package d.zhdanov.ccfit.nsu.persistence.workers.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("engineer_position")
public class EngineerPosition {
  @Id
  private Integer id;
  private String name;
}
