package d.zhdanov.ccfit.nsu.activity.persistence.entities;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ManufacturerRepository
  extends PagingAndSortingRepository<MaterialTypeEntity, UUID>,
          CrudRepository<MaterialTypeEntity, UUID> {
}
