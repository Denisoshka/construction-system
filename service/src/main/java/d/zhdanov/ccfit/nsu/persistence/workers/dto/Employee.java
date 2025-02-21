package d.zhdanov.ccfit.nsu.persistence.workers.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Table("employees")
public class Employee {
    @Id
    private long id;

    private String system_id;
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate seniority;
    private String post;
}

