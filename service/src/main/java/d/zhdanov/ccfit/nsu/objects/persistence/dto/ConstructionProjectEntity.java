package d.zhdanov.ccfit.nsu.objects.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ConstructionProjectEntity {
  private UUID id;
  private UUID siteId;
}
