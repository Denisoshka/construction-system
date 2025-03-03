package d.zhdanov.ccfit.nsu.activity.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@AllArgsConstructor
@Table("bridge")
public class BridgeEntity {
  @Id
  private UUID    projectId;
  private Integer span;
  private Integer width;
  private Integer trafficLanesNumber;
}
