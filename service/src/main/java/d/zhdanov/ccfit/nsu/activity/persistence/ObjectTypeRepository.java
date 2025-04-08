package d.zhdanov.ccfit.nsu.activity.persistence;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.ObjectTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface ObjectTypeRepository
  extends PagingAndSortingRepository<ObjectTypeEntity, String>,
          CrudRepository<ObjectTypeEntity, String> {}
