package d.zhdanov.ccfit.nsu.workers.persistence.utils;

import d.zhdanov.ccfit.nsu.workers.persistence.entities.EngineerPositionEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EngineerPositionEntityFetcher {
  public static EngineerPositionEntity of(
    final ResultSet rs,
    final Integer positionId
  )
  throws SQLException {
    return new EngineerPositionEntity(
      positionId,
      rs.getString("position_name")
    );
  }
}
