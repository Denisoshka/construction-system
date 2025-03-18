package d.zhdanov.ccfit.nsu.workers.persistence;

import d.zhdanov.ccfit.nsu.workers.persistence.entities.EngineerPositionEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EngineersPositionRepository
  extends PagingAndSortingRepository<EngineerPositionEntity, Integer>,
          CrudRepository<EngineerPositionEntity, Integer> {
  Optional<EngineerPositionEntity> findByName(String name);
  
  List<EngineerPositionEntity> findAll(Integer id);
  
  @Modifying
  void deleteByName(String name);
  
  @Query(
    """
        SELECT ep.id, name FROM engineer_position ep
        JOIN engineers e ON ep.id = e.position_id
        WHERE e.employee_id = :employeeId
    """
  )
  Optional<EngineerPositionEntity> findByEmployeeId(UUID employeeId);
}

