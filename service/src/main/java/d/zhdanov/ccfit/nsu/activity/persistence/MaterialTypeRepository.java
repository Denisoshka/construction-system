package d.zhdanov.ccfit.nsu.activity.persistence;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.MaterialTypeEntity;
import d.zhdanov.ccfit.nsu.utils.persistence.employees.MaterialTypeRowMapper;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MaterialTypeRepository
  extends PagingAndSortingRepository<MaterialTypeEntity, UUID>,
          CrudRepository<MaterialTypeEntity, UUID> {
  @Query(
    value = """
            SELECT
                mt.id AS mt_id,
                mt.manufacturer_id AS mt_manufacturer_id,
                mt.name AS mt_name,
                mt.cost AS mt_cost,
                mr.id AS mr_id,
                mr.manufacturer AS mr_manufacturer
            FROM material_type mt
            JOIN manufacturer_table mr ON mt.manufacturer_id = mr.id
            LIMIT :limit OFFSET :offset
            """, rowMapperClass = MaterialTypeRowMapper.class
  )
  List<MaterialTypeEntity> findMaterialsWithManufacturer(
    long offset, int limit);
  
  @Query(
    value = """
            SELECT
                mt.id AS mt_id,
                mt.manufacturer_id AS mt_manufacturer_id,
                mt.name AS mt_name,
                mt.cost AS mt_cost,
                mr.id AS mr_id,
                mr.manufacturer AS mr_manufacturer
            FROM material_type mt
            JOIN manufacturer_table mr ON mt.manufacturer_id = mr.id
            WHERE mt.id = :materialId
            """, rowMapperClass = MaterialTypeRowMapper.class
  )
  Optional<MaterialTypeEntity> findMaterialsWithManufacturer(
    UUID materialId
  );
}
