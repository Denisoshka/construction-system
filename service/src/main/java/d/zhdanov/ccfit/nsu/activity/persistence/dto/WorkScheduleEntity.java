package d.zhdanov.ccfit.nsu.activity.persistence.dto;

import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Table("work_schedule")
public class WorkScheduleEntity {
  @NotNull
  private UUID      id;
  @NotNull
  private UUID      brigadeId;
  @NotNull
  private UUID      workTypeId;
  @NotNull
  private UUID      contractId;
  @NotNull
  private LocalDate plan_start_date;
  @NotNull
  private LocalDate plan_end_date;
  @Nullable
  private LocalDate fact_start_date;
  @Nullable
  private LocalDate fact_end_date;
  @NotNull
  private LocalDate plan_order;
  @Nullable
  private LocalDate fact_order;
}
