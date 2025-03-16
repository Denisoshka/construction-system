package d.zhdanov.ccfit.nsu.objects.persistence.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("foremen_team_management")
public class ForemenTeamManagement {
  @Id
  private UUID teamId;
  @Id
  private UUID workerId;
}
