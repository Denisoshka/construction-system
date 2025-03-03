package d.zhdanov.ccfit.nsu.workers.persistence;

import d.zhdanov.ccfit.nsu.workers.persistence.dto.EngineerEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Repository
public interface EngineersRepository
  extends PagingAndSortingRepository<EngineerEntity, UUID>,
          CrudRepository<EngineerEntity, UUID> {
  @Modifying
  @Transactional
  @Query("INSERT INTO engineers (employee_id, position_id) VALUES (:employeeId, :positionId)")
  void insertEngineer(
    @Param("employeeId") UUID employeeId,
    @Param("positionId") int positionId
  ) throws DataIntegrityViolationException;
  
  @Modifying
  @Query("UPDATE engineers SET position_id = :positionId WHERE employee_id = :employeeId")
  void updatePosition(
    @Param("employeeId") UUID employeeId,
    @Param("positionId") Integer positionId
  );
}
