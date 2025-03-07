package d.zhdanov.ccfit.nsu.workers.persistence;

import d.zhdanov.ccfit.nsu.workers.persistence.entities.EmployeeEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository
  extends PagingAndSortingRepository<EmployeeEntity, UUID>,
          CrudRepository<EmployeeEntity, UUID> {
  
  @Query(
    "SELECT e.* FROM employees e "
    + "JOIN engineers eng ON e.id = eng.employee_id "
    + "WHERE eng.position_id = :positionId"
  )
  @NotNull List<EmployeeEntity> findEngineersByPositionId(
    @Param("positionId") long positionId, Pageable pageable);
  
  @Query(
    "SELECT e.* FROM employees e "
    + "JOIN workers wor ON e.id = wor.employee_id "
    + "WHERE wor.position_id = :positionId"
  )
  @NotNull List<EmployeeEntity> findWorkersByPositionId(
    @Param("positionId") long positionId, Pageable pageable);
  @Transactional
  void deleteBySystemId(@Param("system_id") String systemId);
  
  Optional<EmployeeEntity> findBySystemId(@Param("system_id") String systemId);
}
