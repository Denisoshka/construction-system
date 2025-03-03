package d.zhdanov.ccfit.nsu.objects.persistence;

import d.zhdanov.ccfit.nsu.objects.persistence.entities.ConstructionProjectEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConstructionProjectRepository
  extends PagingAndSortingRepository<ConstructionProjectEntity, UUID>,
          CrudRepository<ConstructionProjectEntity, UUID> {}
