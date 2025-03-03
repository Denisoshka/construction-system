package d.zhdanov.ccfit.nsu.persistence.objects.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ConstructionProjectDTO {
  private UUID id;
  private UUID siteId;
}
