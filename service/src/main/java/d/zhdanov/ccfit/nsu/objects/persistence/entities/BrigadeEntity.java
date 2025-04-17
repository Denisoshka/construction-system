package d.zhdanov.ccfit.nsu.objects.persistence.entities;

import d.zhdanov.ccfit.nsu.workers.persistence.entities.WorkerEntity;
import lombok.Data;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Data
@Table("brigade")
public class BrigadeEntity {
  @Id
  private UUID id;
  private UUID foremanId;
  private UUID siteId;
  
  @Transient
  private WorkerEntity           workerInfo;
  @Transient
  private ConstructionSiteEntity constructionSiteEntity;
  
  public static BrigadeEntity of(@NotNull ResultSet rs) throws SQLException {
    BrigadeEntity brigade = new BrigadeEntity();
    brigade.setId(rs.getObject("br_id", UUID.class));
    brigade.setForemanId(rs.getObject("br_foreman_id", UUID.class));
    brigade.setSiteId(rs.getObject("br_site_id", UUID.class));
    return brigade;
  }
}
