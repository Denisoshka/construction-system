package d.zhdanov.ccfit.nsu.objects.persistence.entities;

import d.zhdanov.ccfit.nsu.workers.persistence.entities.WorkerEntity;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Data
@Table("brigade")
public class BrigadeEntity {
  @Id
  private UUID                   id;
  private UUID                   foremanId;
  private UUID                   siteId;
  @Transient
  private WorkerEntity           workerInfo;
  @Transient
  private ConstructionSiteEntity constructionSiteEntity;
}
