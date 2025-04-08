package d.zhdanov.ccfit.nsu.activity.persistence;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.ProjectContractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProjectContractRepository
  extends PagingAndSortingRepository<ProjectContractEntity, UUID>,
          CrudRepository<ProjectContractEntity, UUID> {
  Page<ProjectContractEntity> findBySiteId(UUID siteId, Pageable pageable);
}
