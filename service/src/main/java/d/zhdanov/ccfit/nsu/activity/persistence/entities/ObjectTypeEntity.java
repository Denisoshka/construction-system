package d.zhdanov.ccfit.nsu.activity.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("object_types")
@AllArgsConstructor
public class ObjectTypeEntity {
  @Id
  private Integer id;
  private String  type;
}
