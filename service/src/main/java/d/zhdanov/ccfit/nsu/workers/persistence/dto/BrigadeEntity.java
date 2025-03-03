package d.zhdanov.ccfit.nsu.workers.persistence.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("brigade")
public class BrigadeEntity {
  @Id
  private Integer id;
  private Integer foremanId;
}
