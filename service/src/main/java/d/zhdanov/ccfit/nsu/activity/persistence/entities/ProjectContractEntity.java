package d.zhdanov.ccfit.nsu.activity.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("construction_project_contract")
public class ProjectContractEntity {
  @Id
  private UUID      id;
  @NotNull
  private UUID      siteId;
  @Nullable
  @Column("customer_organization_id")
  private UUID      customerId;
  @NotNull
  private String type;
  @NotNull
  private LocalDate dateOfCreation;
  @Nullable
  private LocalDate signingDate;
  @NotNull
  private LocalDate planStartDate;
  @NotNull
  private LocalDate planEndDate;
  @Nullable
  private LocalDate factStartDate;
  @Nullable
  private LocalDate factEndDate;
}