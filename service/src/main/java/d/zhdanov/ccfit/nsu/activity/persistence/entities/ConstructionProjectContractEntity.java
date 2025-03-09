package d.zhdanov.ccfit.nsu.activity.persistence.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Table("construction_project_contract")
public class ConstructionProjectContractEntity {
  @Id
  private UUID      id;
  private UUID      projectId;
  private UUID      siteId;
  private UUID      customerId;
  private Integer   objectType;
  private LocalDate dateOfCreation;
  private LocalDate signingDate;
  private LocalDate acceptanceDate;
  private LocalDate planStartDate;
  private LocalDate planEndDate;
  private LocalDate factStartDate;
  private LocalDate factEndDate;
}
