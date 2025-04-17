package d.zhdanov.ccfit.nsu.activity.persistence;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.MaterialUsageEntity;
import d.zhdanov.ccfit.nsu.utils.persistence.employees.MaterialUsageRowMapper;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MaterialUsageRepository
  extends PagingAndSortingRepository<MaterialUsageEntity, UUID>,
          CrudRepository<MaterialUsageEntity, UUID> {
  @Query(
    value = """
            SELECT
                mu.id as mu_id,
                mu.work_unit_id as mu_work_unit_id,
                mu.material_id as mu_material_id,
                mu.plan_quantity as mu_plan_quantity,
                mt.id AS mt_id,
                mt.manufacturer_id AS mt_manufacturer_id,
                mt.name AS mt_name,
                mt.cost AS mt_cost,
                mr.id AS m_id,
                mr.manufacturer AS m_manufacturer
            FROM material_usage mu
            JOIN material_type mt ON mu.material_id = mt.id
            JOIN manufacturer_table mr ON mt.manufacturer_id = mr.id
            WHERE mu.work_unit_id = :workUnitId::uuid
            LIMIT :limit OFFSET :offset
            """, rowMapperClass = MaterialUsageRowMapper.class
  )
  List<MaterialUsageEntity> findMaterialUsageWithDetails(
    UUID workUnitId, long offset, int limit);
  
  @Query(
    value = """
            SELECT
                mu.id as mu_id,
                mu.work_unit_id as mu_work_unit_id,
                mu.material_id as mu_material_id,
                mu.plan_quantity as mu_plan_quantity,
                mt.id AS mt_id,
                mt.manufacturer_id AS mt_manufacturer_id,
                mt.name AS mt_name,
                mt.cost AS mt_cost,
                mr.id AS m_id,
                mr.manufacturer AS m_manufacturer
            FROM material_usage mu
            JOIN material_type mt ON mu.material_id = mt.id
            JOIN manufacturer_table mr ON mt.manufacturer_id = mr.id
            WHERE mu.id = :unitId::uuid
            LIMIT :limit OFFSET :offset
            """, rowMapperClass = MaterialUsageRowMapper.class
  )
  Optional<MaterialUsageEntity> findMaterialUsageWithDetails(UUID workUnitId);
}
