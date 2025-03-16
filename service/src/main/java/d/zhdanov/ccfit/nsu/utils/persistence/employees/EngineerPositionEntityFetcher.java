package d.zhdanov.ccfit.nsu.utils.persistence.employees;

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
      rs.getString("engineer_position_name")
    );
  }
}
