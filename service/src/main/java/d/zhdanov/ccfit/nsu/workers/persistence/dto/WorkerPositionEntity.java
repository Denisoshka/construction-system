package d.zhdanov.ccfit.nsu.workers.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table("worker_position")
public class WorkerPositionEntity {
  @Id
  private Integer id;
  private String name;
}
