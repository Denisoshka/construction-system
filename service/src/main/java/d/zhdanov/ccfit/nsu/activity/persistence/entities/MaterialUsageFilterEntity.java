package d.zhdanov.ccfit.nsu.activity.persistence.entities;

import lombok.Data;

import java.util.UUID;

@Data
public class MaterialUsageFilterEntity {
  private UUID siteId;
  private UUID contractId;
}
