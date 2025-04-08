package d.zhdanov.ccfit.nsu.workers.persistence;

import d.zhdanov.ccfit.nsu.utils.persistence.employees.EngineerRowMapper;
import d.zhdanov.ccfit.nsu.workers.persistence.entities.EngineerEntity;
import org.springframework.dao.DataIntegrityViolationException;
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
  @Query(
    value = """
            SELECT e.employee_id, emp.system_id,
                   emp.name, emp.surname,
                   emp.patronymic, emp.employment_date, emp.post,
                   pos.id AS engineer_position_id,
                   pos.name AS engineer_position_name
            FROM engineers e
            JOIN employees emp ON e.employee_id = emp.id
            JOIN engineer_position pos ON e.position_id = pos.id
            """, rowMapperClass = EngineerRowMapper.class
  )
  List<EngineerEntity> findAllEngineersWithPositionEntity();
  
  @Query(
    value = """
            SELECT e.employee_id, emp.system_id,
                   emp.name, emp.surname,
                   emp.patronymic, emp.employment_date, emp.post,
                   pos.id AS engineer_position_id,
                   pos.name AS engineer_position_name
            FROM engineers e
            JOIN employees emp ON e.employee_id = emp.id
            JOIN engineer_position pos ON e.position_id = pos.id
            LIMIT :limit OFFSET :offset
            """, rowMapperClass = EngineerRowMapper.class
  )
  List<EngineerEntity> findAllEngineersWithPositionEntity(
    long offset, int limit
  );
  
  @Query(
    value = """
            SELECT e.employee_id, emp.system_id,
                   emp.name, emp.surname,
                   emp.patronymic, emp.employment_date, emp.post,
                   pos.id AS engineer_position_id,
                   pos.name AS engineer_position_name
            FROM engineers e
            JOIN employees emp ON e.employee_id = emp.id
            JOIN engineer_position pos ON e.position_id = pos.id
            JOIN site_team_management stm ON e.employee_id = stm.engineer_id
            WHERE stm.site_id = :siteId
            LIMIT :limit OFFSET :offset
            """, rowMapperClass = EngineerRowMapper.class
  )
  List<EngineerEntity> findAllEngineersBySiteWithPositionEntity(
    UUID siteId, long offset, int limit
  );
  
  @Query(
    value = """
                SELECT e.employee_id, e.position_id,
                       emp.id, emp.system_id,
                       emp.name, emp.surname,
                       emp.patronymic, emp.employment_date, emp.post,
                       pos.id AS engineer_position_id,
                       pos.name AS engineer_position_name
                FROM engineers e
                JOIN employees emp ON e.employee_id = emp.id
                LEFT JOIN engineer_position pos ON e.position_id = pos.id
                WHERE e.employee_id = :id
            """, rowMapperClass = EngineerRowMapper.class
  )
  Optional<EngineerEntity> findEngineerWithPositionEntity(UUID id);
}
