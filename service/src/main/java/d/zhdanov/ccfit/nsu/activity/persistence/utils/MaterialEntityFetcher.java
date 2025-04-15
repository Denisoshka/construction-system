package d.zhdanov.ccfit.nsu.activity.persistence.utils;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.MaterialTypeEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MaterialEntityFetcher {
  public static MaterialTypeEntity of(ResultSet rs) throws SQLException {
    final var materialType = new MaterialTypeEntity();
    materialType.setId(rs.getObject("mt_id", UUID.class));
    materialType.setManufacturerId(
      rs.getObject("mt_manufacturer_id", UUID.class));
    materialType.setName(rs.getString("mt_name"));
    materialType.setCost(rs.getInt("mt_cost"));
    return materialType;
  }
}
