package d.zhdanov.ccfit.nsu.persistence.workers.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("workers")
public class Worker {
  @Id
  private Integer employeeId;
  private Integer position;
}
