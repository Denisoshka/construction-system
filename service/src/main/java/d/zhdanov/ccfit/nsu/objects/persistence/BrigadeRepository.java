package d.zhdanov.ccfit.nsu.objects.persistence;

import d.zhdanov.ccfit.nsu.objects.persistence.entities.BrigadeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BrigadeRepository
  extends PagingAndSortingRepository<BrigadeEntity, UUID>,
          CrudRepository<BrigadeEntity, UUID> {
}