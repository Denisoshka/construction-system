package d.zhdanov.ccfit.nsu.activity.persistence;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.MaterialUsageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;
import java.util.UUID;

public interface MaterialUsageRepository extends
                                         PagingAndSortingRepository<MaterialUsageEntity, UUID>,
                                         CrudRepository<MaterialUsageEntity, UUID> {
  List<MaterialUsageEntity> findByWorkUnitId(UUID workUnitId, Pageable pageable);
}
