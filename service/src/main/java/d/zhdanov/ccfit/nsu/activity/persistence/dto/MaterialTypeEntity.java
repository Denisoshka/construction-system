package d.zhdanov.ccfit.nsu.activity.persistence.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("material_type")
public class MaterialTypeEntity {
  @Id
  private UUID    id;
  private String  name;
  private Integer cost;
}
