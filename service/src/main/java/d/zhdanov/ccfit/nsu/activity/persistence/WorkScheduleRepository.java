package d.zhdanov.ccfit.nsu.activity.persistence;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.WorkScheduleEntity;
import d.zhdanov.ccfit.nsu.utils.persistence.employees.WorkScheduleEntityMapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Sort;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface WorkScheduleRepository
  extends PagingAndSortingRepository<WorkScheduleEntity, UUID>,
          CrudRepository<WorkScheduleEntity, UUID> {
  Sort defSort =
    Sort.by(Sort.Direction.ASC, "wp_plan_start_date", "wp_plan_end_date");
  
  @NotNull
  @Query(
    value = """
                SELECT
                    ws.id AS ws_id,
                    ws.brigade_id AS ws_brigade_id,
                    ws.work_type_id AS ws_work_type_id,
                    ws.contract_id AS ws_contract_id,
                    ws.plan_start_date AS ws_plan_start_date,
                    ws.plan_end_date AS ws_plan_end_date,
                    ws.fact_start_date AS ws_fact_start_date,
                    ws.fact_end_date AS ws_fact_end_date,
                    ws.plan_order AS ws_plan_order,
                    ws.fact_order AS ws_fact_order,
                    wt.id AS wt_id,
                    wt.name AS wt_name,
            
                    br.id AS br_id,
                    br.foreman_id AS br_foreman_id,
                    br.site_id AS br_site_id
            
                FROM work_schedule ws
                JOIN work_type wt ON ws.work_type_id = wt.id
                JOIN brigade br ON ws.brigade_id = br.id
                WHERE ws.id = :id::uuid
            """, rowMapperClass = WorkScheduleEntityMapper.class
  )
  Optional<WorkScheduleEntity> findById(
    @NotNull UUID id
  );
  
  @Query(
    value = """
                SELECT
                    ws.id AS ws_id,
                    ws.brigade_id AS ws_brigade_id,
                    ws.work_type_id AS ws_work_type_id,
                    ws.contract_id AS ws_contract_id,
                    ws.plan_start_date AS ws_plan_start_date,
                    ws.plan_end_date AS ws_plan_end_date,
                    ws.plan_order AS ws_plan_order,
                    ws.fact_start_date AS ws_fact_start_date,
                    ws.fact_end_date AS ws_fact_end_date,
                    ws.fact_order AS ws_fact_order,

                    wt.id AS wt_id,
                    wt.name AS wt_name,
            
                    br.id AS br_id,
                    br.foreman_id AS br_foreman_id,
                    br.site_id AS br_site_id
            
                FROM work_schedule ws
                JOIN work_type wt ON ws.work_type_id = wt.id
                JOIN brigade br ON ws.brigade_id = br.id
                WHERE br.id = :brigadeId::uuid
                LIMIT :limit
                OFFSET :offset
            """, rowMapperClass = WorkScheduleEntityMapper.class
  )
  List<WorkScheduleEntity> findAllByBrigadeId(
    UUID brigadeId, long offset,
    int limit, Sort sort
  );
  @Query(
    value = """
                SELECT
                    ws.id AS ws_id,
                    ws.brigade_id AS ws_brigade_id,
                    ws.work_type_id AS ws_work_type_id,
                    ws.contract_id AS ws_contract_id,
                    ws.plan_start_date AS ws_plan_start_date,
                    ws.plan_end_date AS ws_plan_end_date,
                    ws.plan_order AS ws_plan_order,
                    ws.fact_start_date AS ws_fact_start_date,
                    ws.fact_end_date AS ws_fact_end_date,
                    ws.fact_order AS ws_fact_order,
            
                    wt.id AS wt_id,
                    wt.name AS wt_name,
            
                    br.id AS br_id,
                    br.foreman_id AS br_foreman_id,
                    br.site_id AS br_site_id
            
                FROM work_schedule ws
                JOIN work_type wt ON ws.work_type_id = wt.id
                JOIN brigade br ON ws.brigade_id = br.id
                WHERE
                    (br.id = :brigadeId::uuid)
                    AND (:startDate::DATE <= ws.plan_start_date)
                    AND (ws.plan_end_date <= :endDate::DATE )
                LIMIT :limit
                OFFSET :offset
            """, rowMapperClass = WorkScheduleEntityMapper.class
  )
  List<WorkScheduleEntity> findAllByBrigadeId(
    UUID brigadeId, long offset, int limit, LocalDate startDate,
    LocalDate endDate, Sort sort
  );
  
  @Query(
    value = """
                SELECT
                    ws.id AS ws_id,
                    ws.brigade_id AS ws_brigade_id,
                    ws.work_type_id AS ws_work_type_id,
                    ws.contract_id AS ws_contract_id,
                    ws.plan_start_date AS ws_plan_start_date,
                    ws.plan_end_date AS ws_plan_end_date,
                    ws.plan_order AS ws_plan_order,
                    ws.fact_start_date AS ws_fact_start_date,
                    ws.fact_end_date AS ws_fact_end_date,
                    ws.fact_order AS ws_fact_order,
                    wt.id AS wt_id,
                    wt.name AS wt_name,
            
                    br.id AS br_id,
                    br.foreman_id AS br_foreman_id,
                    br.site_id AS br_site_id
            
                FROM work_schedule ws
                JOIN work_type wt ON ws.work_type_id = wt.id
                JOIN brigade br ON ws.brigade_id = br.id
                WHERE ws.contract_id = :contractId::uuid
                LIMIT :limit
                OFFSET :offset
            """, rowMapperClass = WorkScheduleEntityMapper.class
  )
  List<WorkScheduleEntity> findAllByContractId(
    UUID contractId, long offset,
    int limit, Sort sort
  );
}
