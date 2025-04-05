package d.zhdanov.ccfit.nsu.objects.persistence;

import d.zhdanov.ccfit.nsu.objects.persistence.entities.BrigadeEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface BrigadeRepository
  extends PagingAndSortingRepository<BrigadeEntity, UUID>,
          CrudRepository<BrigadeEntity, UUID> {
  List<BrigadeEntity> findAllBySiteId(UUID siteId, Pageable pageable);
}