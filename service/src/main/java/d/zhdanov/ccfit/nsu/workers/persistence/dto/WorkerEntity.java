package d.zhdanov.ccfit.nsu.workers.persistence.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("workers")
public class WorkerEntity {
  @Id
  @MappedCollection(idColumn = "employee_id")
  private EmployeeEntity       employee;
  @MappedCollection(idColumn = "position_id")
  private WorkerPositionEntity position;
}
