package d.zhdanov.ccfit.nsu.activity.persistence;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.WorkTypeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface WorkTypeRepository
  extends PagingAndSortingRepository<WorkTypeEntity, UUID>,
          CrudRepository<WorkTypeEntity, UUID> {}
