package d.zhdanov.ccfit.nsu.persistence.workers.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("site_head_team_management_table")
public class SiteHeadTeamManagementDTO {
  @Id
  private UUID teamId;
  @Id
  private UUID engineerId;
}
