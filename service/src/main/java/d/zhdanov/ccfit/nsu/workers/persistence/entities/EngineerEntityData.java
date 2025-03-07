package d.zhdanov.ccfit.nsu.workers.persistence.entities;

import lombok.Data;

@Data
public class EngineerEntityData {
  EmployeeEntity         employee;
  EngineerPositionEntity engineerPosition;
}
