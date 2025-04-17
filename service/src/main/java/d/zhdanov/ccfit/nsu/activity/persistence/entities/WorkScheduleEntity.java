package d.zhdanov.ccfit.nsu.activity.persistence.entities;

import d.zhdanov.ccfit.nsu.objects.persistence.entities.BrigadeEntity;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@Table("work_schedule")
public class WorkScheduleEntity {
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
  
  @Transient
  private WorkTypeEntity workType;
  @Transient
  private BrigadeEntity  brigade;
  
  public static WorkScheduleEntity of(@NotNull ResultSet rs)
  throws SQLException {
    WorkScheduleEntity ws = new WorkScheduleEntity();
    ws.setId(rs.getObject("ws_id", UUID.class));
    ws.setBrigadeId(rs.getObject("ws_brigade_id", UUID.class));
    ws.setWorkTypeId(rs.getObject("ws_work_type_id", UUID.class));
    ws.setContractId(rs.getObject("ws_contract_id", UUID.class));
    ws.setPlanStartDate(rs.getObject("ws_plan_start_date", LocalDate.class));
    ws.setPlanEndDate(rs.getObject("ws_plan_end_date", LocalDate.class));
    ws.setPlanOrder(rs.getInt("ws_plan_order"));
    return ws;
  }
}
