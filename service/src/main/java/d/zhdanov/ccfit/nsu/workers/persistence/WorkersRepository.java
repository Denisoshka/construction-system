package d.zhdanov.ccfit.nsu.workers.persistence;

import d.zhdanov.ccfit.nsu.workers.persistence.entities.WorkerEntity;
import d.zhdanov.ccfit.nsu.workers.persistence.utils.EngineerRowMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WorkersRepository
  extends PagingAndSortingRepository<WorkerEntity, UUID>,
          CrudRepository<WorkerEntity, UUID> {
  @Modifying
  @Query("INSERT INTO workers (employee_id, position_id) VALUES (:employeeId, :positionId)")
  void insertWorker(
    @Param("employeeId") UUID employeeId,
    @Param("positionId") int positionId
  ) throws DataIntegrityViolationException;
  
  @Modifying
  @Query("UPDATE workers SET position_id = :positionId WHERE employee_id = :employeeId")
  void updatePosition(
    @Param("employeeId") UUID employeeId,
    @Param("positionId") int positionId
  );
  
  @Query(
    value = """
                SELECT w.employee_id, w.position_id,
                       emp.system_id, emp.name, emp.surname, emp.patronymic, emp.employment_date, emp.post,
                       pos.id AS position_id, pos.name AS position_name
                FROM workers w
                JOIN employees emp ON w.employee_id = emp.id
                LEFT JOIN engineer_position pos ON w.position_id = pos.id
            """, rowMapperClass = EngineerRowMapper.class
  )
  List<WorkerEntity> findAllEngineers();
}