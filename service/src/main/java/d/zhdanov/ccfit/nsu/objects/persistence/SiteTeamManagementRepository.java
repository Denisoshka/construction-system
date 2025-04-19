package d.zhdanov.ccfit.nsu.objects.persistence;

import d.zhdanov.ccfit.nsu.objects.persistence.entities.SiteTeamManagement;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface SiteTeamManagementRepository
  extends PagingAndSortingRepository<SiteTeamManagement, UUID>,
          CrudRepository<SiteTeamManagement, UUID> {
  Optional<SiteTeamManagement> findByEngineerId(UUID engineerId);
}
