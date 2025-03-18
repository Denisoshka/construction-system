package d.zhdanov.ccfit.nsu.activity.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("construction_project_contract")
public class ConstructionProjectContractEntity {
  @Id
  private UUID      id;
  private UUID      siteId;
  private UUID      customerOrganizationId;
  private Integer   objectType;
  private LocalDate dateOfCreation;
  private LocalDate signingDate;
  private LocalDate acceptanceDate;
  private LocalDate planStartDate;
  private LocalDate planEndDate;
  private LocalDate factStartDate;
  private LocalDate factEndDate;
  
  @Deprecated
  @Transient
  private ObjectTypeEntity objectTypeEntity;
}
