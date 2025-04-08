package d.zhdanov.ccfit.nsu.objects.dto;

import d.zhdanov.graphql.types.ConstructionSite;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
public class ConstructionSiteDTO extends ConstructionSite {
  private UUID siteManagerId;
}
