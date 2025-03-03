package d.zhdanov.ccfit.nsu.persistence.activity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@AllArgsConstructor
public class SchoolDTO {
  @Id
  private UUID    projectId;
  private Integer classroomCount;
  private Integer floors;
}
