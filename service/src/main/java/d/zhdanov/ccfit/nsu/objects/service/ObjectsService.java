package d.zhdanov.ccfit.nsu.objects.service;

import d.zhdanov.ccfit.nsu.objects.persistence.ConstructionProjectRepository;
import d.zhdanov.ccfit.nsu.objects.persistence.ConstructionSiteRepository;
import d.zhdanov.ccfit.nsu.objects.persistence.entities.ConstructionProjectEntity;
import d.zhdanov.graphql.types.ConstructionManagementInput;
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
  public createConstructionManagement(final ConstructionManagementInput input) {
  
  }
  
  @Transactional
  public ConstructionProjectEntity prepareNewProjectForSite(final UUID siteId) {
    return constructionProjectRepository.save(
      new ConstructionProjectEntity(null, siteId));
  }
}
