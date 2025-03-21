package d.zhdanov.ccfit.nsu.objects.persistence.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("site_team_management")
public class SiteTeamManagement {
  private UUID teamId;
  @Id
  private UUID engineerId;
}
