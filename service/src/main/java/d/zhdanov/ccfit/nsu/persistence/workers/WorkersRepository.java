package d.zhdanov.ccfit.nsu.persistence.workers;

import d.zhdanov.ccfit.nsu.persistence.workers.dto.EmployeeDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkersRepository
    extends PagingAndSortingRepository<EmployeeDTO, String>,
    CrudRepository<EmployeeDTO, String> {
  EmployeeDTO findById(long id);

  EmployeeDTO findBySystemId(String systemId);

  @NotNull List<EmployeeDTO> findAll(PageRequest pageable);

  @Query("SELECT e.* FROM employees e " +
      "JOIN engineers eng ON e.id = eng.employee_id " +
      "WHERE eng.position_id = :positionId")
  @NotNull List<EmployeeDTO> findEngineersByPositionId(
      @Param("positionId") long positionId, Pageable pageable);

  @Query("SELECT e.* FROM employees e " +
      "JOIN workers wor ON e.id = wor.employee_id " +
      "WHERE wor.position_id = :positionId")
  @NotNull List<EmployeeDTO> findWorkersByPositionId(
      @Param("positionId") long positionId, Pageable pageable);
@Modifying
  EmployeeDTO update()
}
