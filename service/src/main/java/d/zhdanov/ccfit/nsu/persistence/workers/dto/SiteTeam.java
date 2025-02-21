package d.zhdanov.ccfit.nsu.persistence.workers.dto;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table("site_team")
public class SiteTeam {
    @Id
    private long id;
    private long siteHeadId;
}
