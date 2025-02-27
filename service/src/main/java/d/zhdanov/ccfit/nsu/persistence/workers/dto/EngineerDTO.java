package d.zhdanov.ccfit.nsu.persistence.workers.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("engineers")
public class EngineerDTO {
  @Id
  private Integer             employeeId;
  @MappedCollection(idColumn = "position_id")
  private EngineerPositionDTO position;
}
