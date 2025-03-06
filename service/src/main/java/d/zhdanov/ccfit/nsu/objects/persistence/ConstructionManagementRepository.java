package d.zhdanov.ccfit.nsu.objects.persistence;

import d.zhdanov.ccfit.nsu.objects.persistence.entities.ConstructionManagementEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ConstructionManagementRepository
  extends PagingAndSortingRepository<ConstructionManagementEntity, UUID>,
          CrudRepository<ConstructionManagementEntity, UUID> {}
