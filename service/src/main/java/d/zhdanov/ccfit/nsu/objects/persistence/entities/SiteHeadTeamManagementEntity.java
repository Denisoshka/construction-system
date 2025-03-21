package d.zhdanov.ccfit.nsu.objects.persistence.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("site_head_team_management_table")
public class SiteHeadTeamManagementEntity {
  @Id
  private UUID engineerId;
  private UUID teamId;
}
