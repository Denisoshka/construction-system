package d.zhdanov.ccfit.nsu.activity.persistence;

import d.zhdanov.ccfit.nsu.activity.persistence.dto.BridgeEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface BridgeRepository
  extends PagingAndSortingRepository<BridgeEntity, UUID>,
          CrudRepository<BridgeEntity, UUID> {}
