package d.zhdanov.ccfit.nsu.activity.persistence.utils;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.ManufacturerEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class ManufacturerEntityFetcher {
  static ManufacturerEntity of(ResultSet rs) throws SQLException {
    return new ManufacturerEntity(
      rs.getObject("mr_id", UUID.class),
      rs.getString("mr_manufacturer")
    );
  }
}
