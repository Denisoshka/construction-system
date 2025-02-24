package d.zhdanov.ccfit.nsu.persistence.workers.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("engineers")
public class Engineer {
  @Id
  private Integer employeeId;
  @MappedCollection
  private EngineerPosition position;
}
