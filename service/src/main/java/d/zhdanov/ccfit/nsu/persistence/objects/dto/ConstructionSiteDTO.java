package d.zhdanov.ccfit.nsu.persistence.objects.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("construction_site")
public class ConstructionSiteDTO {
  @Id
  private UUID   id;
  private String name;
  private String description;
  private String phone;
  private UUID   managementId;
  private UUID   siteManagerId;
}
