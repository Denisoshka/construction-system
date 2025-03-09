package d.zhdanov.ccfit.nsu.activity.persistence;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.ConstructionProjectContractEntity;
import d.zhdanov.ccfit.nsu.activity.persistence.utils.ConstructionProjectContractWithTypeRowMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ConstructionProjectContractRepository
  extends PagingAndSortingRepository<ConstructionProjectContractEntity, UUID>,
          CrudRepository<ConstructionProjectContractEntity, UUID> {
  
  @Query(
    value = """
            SELECT c.id, c.project_id, c.site_id, c.customer_organization_id,
                   c.type, c.date_of_creation, c.signing_date, c.acceptance_date,
                   c.plan_end_date, c.plan_end_date, c.fact_start_date, c.fact_end_date,
                   o.id as type_id, o.type as type_name
            FROM construction_project_contract c
            JOIN object_types o ON c.type = o.id
            WHERE c.id =:id
            LIMIT :#{#pageable.pageSize} OFFSET :#{#pageable.offset}
            """, rowMapperClass = ConstructionProjectContractWithTypeRowMapper.class
  )
  ConstructionProjectContractEntity findByIdWithObjectType(UUID id);
  
  @Query(
    value = """
            SELECT c.id, c.project_id, c.site_id, c.customer_organization_id,
                   c.type, c.date_of_creation, c.signing_date, c.acceptance_date,
                   c.plan_end_date, c.plan_end_date, c.fact_start_date, c.fact_end_date,
                   o.id as type_id, o.type as type_name
            FROM construction_project_contract c
            JOIN object_types o ON c.type = o.id
            LIMIT :#{#pageable.pageSize} OFFSET :#{#pageable.offset}
            """, rowMapperClass = ConstructionProjectContractWithTypeRowMapper.class
  )
  List<ConstructionProjectContractEntity> findAllWithObjectType(Pageable pageable);
}
