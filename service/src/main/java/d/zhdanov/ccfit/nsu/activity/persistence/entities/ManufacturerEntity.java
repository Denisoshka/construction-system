package d.zhdanov.ccfit.nsu.activity.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("manufacturer_table")
public class ManufacturerEntity {
  @Id
  private UUID   id;
  @NotNull
  private String manufacturerId;
}
