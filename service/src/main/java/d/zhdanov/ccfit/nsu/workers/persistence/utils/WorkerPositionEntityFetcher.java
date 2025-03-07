package d.zhdanov.ccfit.nsu.workers.persistence.utils;

import d.zhdanov.ccfit.nsu.workers.persistence.entities.EngineerPositionEntity;
import d.zhdanov.ccfit.nsu.workers.persistence.entities.WorkerPositionEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class WorkerPositionEntityFetcher {
  public static WorkerPositionEntity of(
    final ResultSet rs,
    final Integer positionId
  )
  throws SQLException {
    return new WorkerPositionEntity(
      positionId,
      rs.getString("position_name")
    );
  }
}
