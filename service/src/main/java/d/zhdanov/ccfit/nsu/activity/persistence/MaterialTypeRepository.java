package d.zhdanov.ccfit.nsu.activity.persistence;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.MaterialTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface MaterialTypeRepository
  extends PagingAndSortingRepository<MaterialTypeEntity, UUID>,
          CrudRepository<MaterialTypeEntity, UUID> {}
