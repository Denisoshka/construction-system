package d.zhdanov.ccfit.nsu.persistence.workers.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Table("employees")
public class EmployeeDTO {
    @Id
    private long id;

    private String systemId;
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate employmentDate;
    private String post;
}

