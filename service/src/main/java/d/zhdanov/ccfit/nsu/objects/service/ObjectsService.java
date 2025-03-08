package d.zhdanov.ccfit.nsu.objects.service;

import d.zhdanov.ccfit.nsu.objects.exceptions.ConstructionSiteAbsent;
import d.zhdanov.ccfit.nsu.objects.mappers.ConstructionObjectsMapper;
import d.zhdanov.ccfit.nsu.objects.persistence.ConstructionManagementRepository;
import d.zhdanov.ccfit.nsu.objects.persistence.ConstructionProjectRepository;
import d.zhdanov.ccfit.nsu.objects.persistence.ConstructionSiteRepository;
import d.zhdanov.ccfit.nsu.objects.persistence.entities.ConstructionManagementEntity;
import d.zhdanov.ccfit.nsu.objects.persistence.entities.ConstructionProjectEntity;
import d.zhdanov.graphql.types.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ObjectsService {
  private final ConstructionManagementRepository
                                              constructionManagementRepository;
  private final ConstructionProjectRepository constructionProjectRepository;
  private final ConstructionSiteRepository    constructionSiteRepository;
  private final ConstructionObjectsMapper     constructionObjectsMapper;
  
  public ObjectsService(
    @Autowired ConstructionProjectRepository constructionProjectRepository,
    @Autowired ConstructionSiteRepository constructionSiteRepository,
    @Autowired
    ConstructionManagementRepository constructionManagementRepository,
    @Autowired ConstructionObjectsMapper constructionObjectsMapper
  ) {
    this.constructionManagementRepository = constructionManagementRepository;
    this.constructionProjectRepository    = constructionProjectRepository;
    this.constructionSiteRepository       = constructionSiteRepository;
    this.constructionObjectsMapper        = constructionObjectsMapper;
  }
  
  @Transactional
  public ConstructionManagement createConstructionManagement(
    final ConstructionManagementInput input
  ) {
    final var entity = new ConstructionManagementEntity();
    constructionObjectsMapper.updateConstructionManagementEntity(entity, input);
    final var ret = constructionManagementRepository.save(entity);
    return constructionObjectsMapper.fromConstructionManagementEntity(ret);
  }
  
  @Transactional
  public ConstructionProjectEntity prepareNewProjectForSite(final UUID siteId) {
    return constructionProjectRepository.save(new ConstructionProjectEntity(
      null,
      siteId
    ));
  }
  
  public Boolean deleteConstructionSite(UUID uuid) {
    constructionSiteRepository.deleteById(uuid);
    return true;
  }
  
  public ConstructionSite createConstructionSite(ConstructionSiteInput input) {
    EmployeeInfo employee;
    if(input.getSiteManagerId() != null) {
      employee =
    }
  }
  
  @Transactional
  public ConstructionManagement updateConstructionManagement(
    UUID uuid,
    ConstructionSiteInput input
  ) {
    final var saved = constructionSiteRepository.findById(uuid).orElseThrow(
      ConstructionSiteAbsent::new);
    
  }
}
