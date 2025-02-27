package d.zhdanov.ccfit.nsu.persistence.workers.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("workers")
public class WorkerDTO {
  @Id
  private Integer           employeeId;
  @MappedCollection(idColumn = "position_id")
  private WorkerPositionDTO position;
}
