package d.zhdanov.ccfit.nsu.service.workers.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeInfoDTO {
  private String    systemId;
  private String    name;
  private String    surname;
  private String    patronymic;
  private LocalDate employmentDate;
  private String    post;
  private String    position;
}
