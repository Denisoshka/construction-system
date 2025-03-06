package d.zhdanov.ccfit.nsu.activity.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("work_type")
@AllArgsConstructor
public class WorkTypeEntity {
  private UUID   id;
  private String name;
}
