package d.zhdanov.ccfit.nsu.persistence.workers.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("site_head_team_management_table")
public class SiteHeadTeamManagement {
  @Id
  private Integer teamId;
  @Id
  private Integer engineerId;
}
