package d.zhdanov.ccfit.nsu.persistence.objects;

import d.zhdanov.ccfit.nsu.persistence.objects.dto.ConstructionSiteDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConstructionSiteRepository
  extends PagingAndSortingRepository<ConstructionSiteDTO, UUID>,
          CrudRepository<ConstructionSiteDTO, UUID> {}
