package d.zhdanov.ccfit.nsu.persistence.workers;

import d.zhdanov.ccfit.nsu.persistence.workers.dto.EmployeeDTO;
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
  extends PagingAndSortingRepository<EmployeeDTO, UUID>,
          CrudRepository<EmployeeDTO, UUID> {
  String UNDEFINED_POST     = "UNKNOWN";
  String UNDEFINED_POSITION = "UNKNOWN";
  String ENGINEER_POST      = "engineer";
  String WORKERS_POST       = "worker";

//  EmployeeDTO findById(UUID id);
  
  @Query(
    "SELECT e.* FROM employees e "
    + "JOIN engineers eng ON e.id = eng.employee_id "
    + "WHERE eng.position_id = :positionId"
  )
  @NotNull List<EmployeeDTO> findEngineersByPositionId(
    @Param("positionId") long positionId, Pageable pageable);
  
  @Query(
    "SELECT e.* FROM employees e "
    + "JOIN workers wor ON e.id = wor.employee_id "
    + "WHERE wor.position_id = :positionId"
  )
  @NotNull List<EmployeeDTO> findWorkersByPositionId(
    @Param("positionId") long positionId, Pageable pageable);
  
  @Transactional
  void deleteBySystemId(@Param("system_id") String systemId);
  
  Optional<EmployeeDTO> findBySystemId(@Param("system_id") String systemId);
}
