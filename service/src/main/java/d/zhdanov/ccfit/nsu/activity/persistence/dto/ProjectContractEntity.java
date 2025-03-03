package d.zhdanov.ccfit.nsu.activity.persistence.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.Date;
import java.util.UUID;

@Data
public class ProjectContractEntity {
  @Id
  private UUID   id;
  private UUID   projectId;
  private UUID   siteId;
  private UUID   customerOrganizationId;
  private String type;
  private Date   dateOfCreation;
  private Date   singingDate;
  private Date   plan_start_date;
  private Date   plan_end_date;
  private Date   fact_start_date;
  private Date   fact_end_date;
}