package d.zhdanov.ccfit.nsu.service.objects;

import d.zhdanov.ccfit.nsu.persistence.objects.ConstructionProjectRepository;
import d.zhdanov.ccfit.nsu.persistence.objects.ConstructionSiteRepository;
import d.zhdanov.ccfit.nsu.persistence.objects.dto.ConstructionProjectDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ObjectsService {
  private final ConstructionProjectRepository constructionProjectRepository;
  private final ConstructionSiteRepository    constructionSiteRepository;
  
  public ObjectsService(
    @Autowired ConstructionProjectRepository constructionProjectRepository,
    @Autowired ConstructionSiteRepository constructionSiteRepository
  ) {
    this.constructionProjectRepository = constructionProjectRepository;
    this.constructionSiteRepository    = constructionSiteRepository;
  }
  
  @Transactional
  public ConstructionProjectDTO prepareNewProjectForSite(final UUID siteId) {
    return constructionProjectRepository.save(
      new ConstructionProjectDTO(null, siteId));
  }
}
