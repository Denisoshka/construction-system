package d.zhdanov.ccfit.nsu.persistence.workers.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@Table("worker_position")
public class WorkerPositionDTO {
  @Id
  private Integer id;
  private String name;
}
