package d.zhdanov.ccfit.nsu.workers.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("workers")
@AllArgsConstructor
public class WorkerEntity {
  @Id
  private UUID                 employeeId;
  private Integer              position;
  @Transient
  private EmployeeEntity       employee;
  @Transient
  private WorkerPositionEntity workerPosition;
}
