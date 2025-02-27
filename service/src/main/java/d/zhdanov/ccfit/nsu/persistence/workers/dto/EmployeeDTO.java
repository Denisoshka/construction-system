package d.zhdanov.ccfit.nsu.persistence.workers.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Table("employees")
public class EmployeeDTO {
  @Id
  private Integer id;
  
  private String    systemId;
  private String    name;
  private String    surname;
  private String    patronymic;
  private LocalDate employmentDate;
  private String    post;
//  @MappedCollection(idColumn = "employee_id")
//  private Engineer  engineer;

//  @MappedCollection(idColumn = "employee_id")
//  private Worker worker;
}

