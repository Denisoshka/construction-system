package d.zhdanov.ccfit.nsu.activity.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manufacturer {
  @Id
  private UUID   id;
  @NotNull
  private String manufacturerId;
}
