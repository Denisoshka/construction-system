package d.zhdanov.ccfit.nsu.activity.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectContractEntity {
  @Id
  private UUID   id;
  @NotNull
  private UUID   projectId;
  @NotNull
  private UUID   siteId;
  @Nullable
  private UUID   customerOrganizationId;
  @NotNull
  private String type;
  @NotNull
  private Date   dateOfCreation;
  @Nullable
  private Date   singingDate;
  @NotNull
  private Date   plan_start_date;
  @NotNull
  private Date   plan_end_date;
  @Nullable
  private Date   fact_start_date;
  @Nullable
  private Date   fact_end_date;
}