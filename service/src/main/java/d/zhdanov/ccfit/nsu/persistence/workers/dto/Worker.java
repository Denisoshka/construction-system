package d.zhdanov.ccfit.nsu.persistence.workers.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("workers")
public class Worker {
    @Id
    private Long employeeId;
    @MappedCollection
    private WorkerPosition position;
}
