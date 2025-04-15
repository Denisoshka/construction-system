package d.zhdanov.ccfit.nsu.activity.persistence.utils;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.MaterialTypeEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialTypeRowMapper implements RowMapper<MaterialTypeEntity> {
  
  @Override
  public MaterialTypeEntity mapRow(@NotNull ResultSet rs, int rowNum)
  throws SQLException {
    final var materialType       = MaterialEntityFetcher.of(rs);
    final var manufacturerEntity = ManufacturerEntityFetcher.of(rs);
    materialType.setManufacturerEntity(manufacturerEntity);
    return materialType;
  }
}

