package d.zhdanov.ccfit.nsu.objects.persistence.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table
public class BrigadeManagementEntity {
  @Id
  private UUID teamId;
  @Id
  private UUID workerId;
}
