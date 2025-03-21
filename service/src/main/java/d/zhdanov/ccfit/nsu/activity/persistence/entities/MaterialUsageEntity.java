package d.zhdanov.ccfit.nsu.activity.persistence.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("material_usage")
public class MaterialUsageEntity {
  @Id
  private UUID    id;
  private UUID    workUnitId;
  private UUID    materialId;
  private Integer planQuantity;
  private Integer factQuantity;
}
