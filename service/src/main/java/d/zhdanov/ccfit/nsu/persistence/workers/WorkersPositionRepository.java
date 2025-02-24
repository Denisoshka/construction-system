package d.zhdanov.ccfit.nsu.persistence.workers;

import d.zhdanov.ccfit.nsu.persistence.workers.dto.WorkerPosition;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface WorkersPositionRepository
    extends PagingAndSortingRepository<WorkerPosition, Long>,
    CrudRepository<WorkerPosition, Long> {
  WorkerPosition findByName(@Param("name") String name);

  @Modifying
  @Query("INSERT INTO workers (employee_id, position_id) VALUES (:employeeId, :positionId)")
  void insertWorker(@Param("employeeId") int employeeId,
                    @Param("positionId") int positionId)
      throws DataIntegrityViolationException;
  @Modifying
  @Query("UPDATE workers SET position_id = :positionId WHERE employee_id = :employeeId")
  void updatePosition(@Param("employeeId") Integer employeeId, @Param("positionId") Integer positionId);
}
