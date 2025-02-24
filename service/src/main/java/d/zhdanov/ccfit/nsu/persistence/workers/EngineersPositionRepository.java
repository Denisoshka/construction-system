package d.zhdanov.ccfit.nsu.persistence.workers;

import d.zhdanov.ccfit.nsu.persistence.workers.dto.EngineerPosition;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface EngineersPositionRepository
    extends PagingAndSortingRepository<EngineerPosition, Long>,
    CrudRepository<EngineerPosition, Long> {
  EngineerPosition findByName(@Param("name") String name);

  @Modifying
  @Transactional
  @Query("INSERT INTO engineers (employee_id, position_id) VALUES (:employeeId, :positionId)")
  void insertEngineer(@Param("employeeId") int employeeId,
                      @Param("positionId") int positionId)
      throws DataIntegrityViolationException;

  @Modifying
  @Query("UPDATE engineers SET position_id = :positionId WHERE employee_id = :employeeId")
  void updatePosition(@Param("employeeId") Integer employeeId,
                      @Param("positionId") Integer positionId);
}

