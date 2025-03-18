package d.zhdanov.ccfit.nsu.objects.persistence.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("brigade")
public class BrigadeEntity {
  @Id
  private UUID id;
  private UUID foremanId;
  private UUID siteId;
  /*@Transient
  private WorkerEntity           workerInfo;
  @Transient
  private ConstructionSiteEntity constructionSiteEntity;*/
}
