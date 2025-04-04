package d.zhdanov.ccfit.nsu.workers.persistence.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("site_head_team_management_table")
public class SiteHeadTeamManagementEntity {
  @Id
  private UUID teamId;
  @Id
  private UUID engineerId;
}
