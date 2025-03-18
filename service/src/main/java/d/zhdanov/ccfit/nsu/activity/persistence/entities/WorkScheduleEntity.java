package d.zhdanov.ccfit.nsu.activity.persistence.entities;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Table("work_schedule")
public class WorkScheduleEntity {
  @NotNull
  @Id
  private UUID      id;
  @NotNull
  private UUID      brigadeId;
  @NotNull
  private UUID      workTypeId;
  @NotNull
  private UUID      contractId;
  @NotNull
  private LocalDate planStartDate;
  @NotNull
  private LocalDate planEndDate;
  @NotNull
  private Integer   planOrder;
}
