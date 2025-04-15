package d.zhdanov.ccfit.nsu.activity.persistence.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("material_type")
@NoArgsConstructor
public class MaterialTypeEntity {
  @Id
  private UUID               id;
  @NotNull
  private UUID               manufacturerId;
  @NotNull
  private String             name;
  @NotNull
  private Integer            cost;
  @Transient
  private ManufacturerEntity manufacturerEntity;
}
