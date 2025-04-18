package d.zhdanov.ccfit.nsu.workers.service.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
public class EmployeeInfoDTO {
  private UUID      id;
  private String    systemId;
  private String    name;
  private String    surname;
  private String    patronymic;
  private LocalDate employmentDate;
  private String    post;
  private Integer   positionId;
}
