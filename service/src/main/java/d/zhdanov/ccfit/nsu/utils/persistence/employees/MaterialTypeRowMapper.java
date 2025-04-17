package d.zhdanov.ccfit.nsu.utils.persistence.employees;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.ManufacturerEntity;
import d.zhdanov.ccfit.nsu.activity.persistence.entities.MaterialTypeEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialTypeRowMapper implements RowMapper<MaterialTypeEntity> {
  
  @Override
  public MaterialTypeEntity mapRow(@NotNull ResultSet rs, int rowNum)
  throws SQLException {
    final var materialType       = MaterialTypeEntity.of(rs);
    final var manufacturerEntity = ManufacturerEntity.of(rs);
    materialType.setManufacturerEntity(manufacturerEntity);
    
    return materialType;
  }
}

