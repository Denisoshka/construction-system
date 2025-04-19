package d.zhdanov.ccfit.nsu.objects.persistence;

import d.zhdanov.ccfit.nsu.objects.persistence.entities.ConstructionSiteEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConstructionSiteRepository
  extends PagingAndSortingRepository<ConstructionSiteEntity, UUID>,
          CrudRepository<ConstructionSiteEntity, UUID> {
  Optional<ConstructionSiteEntity> findBySiteManagerId(UUID siteManagerId);
  
  List<ConstructionSiteEntity> findBySiteManagerId(UUID siteManagerId, Pageable pageable);
  
  @Query("""
         SELECT * FROM construction_site
         JOIN site_team_management stm ON construction_site.id = stm.site_id
         WHERE stm.engineer_id = :uuid::uuid
         """)
  Optional<ConstructionSiteEntity> findByEngineerId(UUID uuid);
}
