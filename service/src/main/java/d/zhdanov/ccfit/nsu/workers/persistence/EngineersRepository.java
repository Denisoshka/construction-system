package d.zhdanov.ccfit.nsu.workers.persistence;

import d.zhdanov.ccfit.nsu.util.Utils;
import d.zhdanov.ccfit.nsu.workers.persistence.entities.EngineerEntity;
import d.zhdanov.ccfit.nsu.workers.persistence.utils.EngineerRowMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Modifying;
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
  
  @Query(
    value = """
            SELECT e.employee_id, e.employee_id,
                   emp.system_id, emp.name, emp.surname, emp.patronymic, emp.employment_date, emp.post,
                   pos.id AS position_id, pos.name AS position_name
            FROM engineers e
            JOIN employees emp ON e.employee_id = emp.id
            LEFT JOIN engineer_position pos ON e.position_id = pos.id
            """, rowMapperClass = EngineerRowMapper.class
  )
  List<EngineerEntity> findAllEngineersWithPositionEntity();
  
  @Query(
    value = """
            SELECT e.employee_id, e.employee_id,
                   emp.id AS emp_id, emp.system_id, emp.name, emp.surname, emp.patronymic, emp.employment_date, emp.post,
                   pos.id AS position_id, pos.name AS position_name
            FROM engineers e
            JOIN employees emp ON e.employee_id = emp.id
            LEFT JOIN engineer_position pos ON e.employee_id = pos.id
            WHERE (:#{#filter.position} IS NULL OR e.position_id = :#{#filter.position})
            LIMIT :#{#pageable.pageSize} OFFSET :#{#pageable.offset}
            """, rowMapperClass = EngineerRowMapper.class
  )
  List<EngineerEntity> findAllEngineersWithPositionEntity(
    Pageable pageable,
    Utils.EngineerRepositoryFilter filter
  );
  
  @Query(
    value = """
                SELECT e.employee_id, e.position_id,
                       emp.id AS emp_id, emp.system_id, emp.name, emp.surname, emp.patronymic, emp.employment_date, emp.post,
                       pos.id AS position_id, pos.name AS position_name
                FROM engineers e
                JOIN employees emp ON e.employee_id = emp.id
                LEFT JOIN engineer_position pos ON e.position_id = pos.id
                WHERE e.employee_id = :id
            """, rowMapperClass = EngineerRowMapper.class
  )
  Optional<EngineerEntity> findEngineerWithPositionEntity(UUID id);
}
