package d.zhdanov.ccfit.nsu.persistence.workers;

import d.zhdanov.ccfit.nsu.persistence.workers.dto.WorkerPositionDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface WorkersPositionRepository
    extends PagingAndSortingRepository<WorkerPositionDTO, Long>,
    CrudRepository<WorkerPositionDTO, Long> {
  WorkerPositionDTO findByName(@Param("name") String name);

  @Modifying
  @Query("INSERT INTO workers (employee_id, position_id) VALUES (:employeeId, :positionId)")
  void insertWorker(@Param("employeeId") int employeeId,
                    @Param("positionId") int positionId)
      throws DataIntegrityViolationException;

  @Modifying
  @Query("UPDATE workers SET position_id = :positionId WHERE employee_id = :employeeId")
  void updatePosition(@Param("employeeId") Integer employeeId,
                      @Param("positionId") Integer positionId);

  @Modifying
  @Query("DELETE FROM worker_position WHERE name = :name")
  void deleteByName(String name);

  WorkerPositionDTO findById(@Param("id") Integer id);
}
