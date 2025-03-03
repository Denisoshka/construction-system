package d.zhdanov.ccfit.nsu.activity.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@AllArgsConstructor
@Table("apartment_house")
public class ApartmentHouseEntity {
  @Id
  private UUID    projectId;
  private Integer floors;
}

