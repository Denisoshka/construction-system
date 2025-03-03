package d.zhdanov.ccfit.nsu.workers.persistence.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("engineers")
public class EngineerEntity {
  @Id
  private Integer                employeeId;
  @MappedCollection(idColumn = "position_id")
  private EngineerPositionEntity position;
}
