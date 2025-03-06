package d.zhdanov.ccfit.nsu.workers.persistence.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("workers")
public class WorkerEntity {
  @Id
  private Integer              employeeId;
  @MappedCollection(idColumn = "position_id")
  private WorkerPositionEntity position;
}
