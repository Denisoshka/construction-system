package d.zhdanov.ccfit.nsu.persistence.workers;

import d.zhdanov.ccfit.nsu.persistence.workers.dto.Employee;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkersRepository extends PagingAndSortingRepository<Employee, String>, CrudRepository<Employee, String> {
    Employee findBySystemid(@Param("positionId") long positionId);

    Employee findBySystemId(@Param("sysmtem_id") long employeeId);

    List<Employee> findAll(PageRequest pageable);

    @NotNull List<Employee> findAll();

    @Query("SELECT e.* FROM employees e " +
            "JOIN engineers eng ON e.id = eng.employee_id " +
            "WHERE eng.position_id = :positionId")
    @NotNull List<Employee> findEngineersByPositionId(@Param("positionId") long positionId, Pageable pageable);

    @Query("SELECT e.* FROM employees e " +
            "JOIN workers wor ON e.id = wor.employee_id " +
            "WHERE wor.position_id = :positionId")
    @NotNull List<Employee> findWorkersByPositionId(@Param("positionId") long positionId, Pageable pageable);
}
