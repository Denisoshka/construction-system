package d.zhdanov.ccfit.nsu.objects.persistence;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BrigadeManagementRepository
  extends PagingAndSortingRepository<BrigadeManagementRepository, UUID>,
          CrudRepository<BrigadeManagementRepository, UUID> {}