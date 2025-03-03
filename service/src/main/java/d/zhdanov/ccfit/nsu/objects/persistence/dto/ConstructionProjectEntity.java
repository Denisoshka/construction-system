package d.zhdanov.ccfit.nsu.objects.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@AllArgsConstructor
public class ConstructionProjectEntity {
  @Id
  private UUID id;
  private UUID siteId;
}
