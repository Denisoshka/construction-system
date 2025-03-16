package d.zhdanov.ccfit.nsu.utils.persistence.employees;

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
      rs.getString("worker_position_id")
    );
  }
}
