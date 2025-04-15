package d.zhdanov.ccfit.nsu.activity.persistence.utils;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.MaterialUsageEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialUsageRowMapper implements RowMapper<MaterialUsageEntity> {
  
  
  @Override
  public MaterialUsageEntity mapRow(@NotNull ResultSet rs, int rowNum)
  throws SQLException {
    final var usage    = MaterialUsageEntityFetcher.of(rs);
    final var material = MaterialEntityFetcher.of(rs);
    usage.setMaterialType(material);
    return usage;
  }
}
