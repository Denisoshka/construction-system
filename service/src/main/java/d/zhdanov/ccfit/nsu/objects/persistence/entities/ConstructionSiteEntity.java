package d.zhdanov.ccfit.nsu.objects.persistence.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("construction_site")
public class ConstructionSiteEntity {
  @Id
  private UUID   id;
  private String name;
  private String address;
  private String phone;
  private UUID   siteManagerId;
}
