package d.zhdanov.ccfit.nsu.objects.dto;

import d.zhdanov.graphql.types.Brigade;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class BrigadeDTO extends Brigade {
  private UUID foremanId;
  private UUID siteId;
}
