package d.zhdanov.ccfit.nsu.persistence.workers;

import d.zhdanov.ccfit.nsu.persistence.workers.dto.WorkerDTO;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WorkersRepository
  extends PagingAndSortingRepository<WorkerDTO, UUID>,
          CrudRepository<WorkerDTO, UUID> {
  @Modifying
  @Query("INSERT INTO workers (employee_id, position_id) VALUES (:employeeId, :positionId)")
  void insertWorker(@Param("employeeId") UUID employeeId,
                    @Param("positionId") int positionId
  ) throws DataIntegrityViolationException;
  
  @Modifying
  @Query("UPDATE workers SET position_id = :positionId WHERE employee_id = :employeeId")
  void updatePosition(@Param("employeeId") UUID employeeId,
                      @Param("positionId") int positionId
  );
}