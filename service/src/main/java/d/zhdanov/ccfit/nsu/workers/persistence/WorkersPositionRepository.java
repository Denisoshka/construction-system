package d.zhdanov.ccfit.nsu.workers.persistence;

import d.zhdanov.ccfit.nsu.workers.persistence.dto.WorkerPositionEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkersPositionRepository
  extends PagingAndSortingRepository<WorkerPositionEntity, Integer>,
          CrudRepository<WorkerPositionEntity, Integer> {
  Optional<WorkerPositionEntity> findByName(String name);
  
  @Modifying
  void deleteByName(String name);
  
  @Query(
    """
        SELECT wp.id, name FROM worker_position wp
        JOIN workers w ON wp.id = w.position_id
        WHERE w.employee_id = :employeeId
    """
  )
  Optional<WorkerPositionEntity> findByEmployeeId(UUID employeeId);
}
