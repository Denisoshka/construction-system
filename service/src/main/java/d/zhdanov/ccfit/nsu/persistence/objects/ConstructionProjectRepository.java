package d.zhdanov.ccfit.nsu.persistence.objects;

import d.zhdanov.ccfit.nsu.persistence.objects.dto.ConstructionProjectDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ConstructionProjectRepository
  extends PagingAndSortingRepository<ConstructionProjectDTO, UUID>,
          CrudRepository<ConstructionProjectDTO, UUID> {}
