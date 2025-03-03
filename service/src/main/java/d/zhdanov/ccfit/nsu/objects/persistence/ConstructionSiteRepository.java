package d.zhdanov.ccfit.nsu.objects.persistence;

import d.zhdanov.ccfit.nsu.objects.persistence.entities.ConstructionSiteEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConstructionSiteRepository
  extends PagingAndSortingRepository<ConstructionSiteEntity, UUID>,
          CrudRepository<ConstructionSiteEntity, UUID> {}
