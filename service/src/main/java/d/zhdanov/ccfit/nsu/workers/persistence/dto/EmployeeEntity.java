package d.zhdanov.ccfit.nsu.workers.persistence.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Table("employees")
public class EmployeeEntity {
  @Id
  private UUID id;
  
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

