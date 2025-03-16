package d.zhdanov.ccfit.nsu.objects.dto;

import d.zhdanov.graphql.types.ConstructionSite;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
public class ConstructionSiteDTO extends ConstructionSite {
  private UUID siteManagerId;
}
