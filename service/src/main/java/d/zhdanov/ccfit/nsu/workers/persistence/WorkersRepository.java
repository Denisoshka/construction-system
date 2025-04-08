package d.zhdanov.ccfit.nsu.workers.persistence;

import d.zhdanov.ccfit.nsu.utils.persistence.employees.WorkerRowMapper;
import d.zhdanov.ccfit.nsu.workers.persistence.entities.WorkerEntity;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
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
                   emp.system_id, emp.name,
                   emp.surname, emp.patronymic,
                   emp.employment_date, emp.post,
                   pos.id AS worker_position_id,
                   pos.name AS worker_position_name
            FROM workers w
            JOIN employees emp ON w.employee_id = emp.id
            LEFT JOIN worker_position pos ON w.position_id = pos.id
            LIMIT :limit OFFSET :offset
            """, rowMapperClass = WorkerRowMapper.class
  )
  List<WorkerEntity> findAllWorkersWithInfo(
    long offset, int limit
  );
  
  @Query(
    value = """
            SELECT w.employee_id, emp.system_id,
                   emp.name, emp.surname,
                   emp.patronymic, emp.employment_date,
                   emp.post,
                   pos.id AS worker_position_id,
                   pos.name AS worker_position_name
            FROM workers w
            JOIN employees emp ON w.employee_id = emp.id
            JOIN worker_position pos ON w.position_id = pos.id
            WHERE w.employee_id = :id
            """, rowMapperClass = WorkerRowMapper.class
  )
  Optional<WorkerEntity> findWorkerWithInfo(@Param("id") UUID id);
  
  @Query(
    value = """
            SELECT
                w.employee_id, emp.system_id,
                emp.name, emp.surname,
                emp.patronymic, emp.employment_date,
                emp.post,
                pos.id AS worker_position_id,
                pos.name AS worker_position_name
            FROM workers w
            JOIN employees emp ON w.employee_id = emp.id
            JOIN worker_position pos ON w.position_id = pos.id
            JOIN brigade_management br_mg ON br_mg.worker_id = w.employee_id
            WHERE br_mg.team_id = :brigadeId
            LIMIT :pageable OFFSET :offset
            """, rowMapperClass = WorkerRowMapper.class
  )
  List<WorkerEntity> findWorkersWithInfoByBrigadeId(
    @Param("brigadeId") UUID brigadeId, long offset, int pageSize
  );
  
  @Query(
    value = """
            SELECT
                w.employee_id, emp.system_id,
                emp.name, emp.surname,
                emp.patronymic, emp.employment_date,
                emp.post,
                pos.id AS worker_position_id,
                pos.name AS worker_position_name
            FROM workers w
            JOIN employees emp ON w.employee_id = emp.id
            JOIN worker_position pos ON w.position_id = pos.id
            JOIN brigade_management br_mg ON br_mg.worker_id = w.employee_id
            JOIN brigade br ON br.site_id = :siteId AND br.id = br_mg.team_id
            LIMIT :pageSize OFFSET :offset
            """, rowMapperClass = WorkerRowMapper.class
  )
  List<WorkerEntity> findAllWorkersBySiteWithPositionEntity(
    UUID siteId, long offset, int pageSize
  );
}