package d.zhdanov.ccfit.nsu.objects.service;

import d.zhdanov.ccfit.nsu.objects.dto.ConstructionSiteDTO;
import d.zhdanov.ccfit.nsu.objects.exceptions.ConstructionSiteAbsent;
import d.zhdanov.ccfit.nsu.objects.exceptions.ConstructionSiteCreateException;
import d.zhdanov.ccfit.nsu.objects.mappers.ConstructionObjectsMapper;
import d.zhdanov.ccfit.nsu.objects.persistence.ConstructionProjectRepository;
import d.zhdanov.ccfit.nsu.objects.persistence.ConstructionSiteRepository;
import d.zhdanov.ccfit.nsu.objects.persistence.entities.ConstructionProjectEntity;
import d.zhdanov.ccfit.nsu.objects.persistence.entities.ConstructionSiteEntity;
import d.zhdanov.ccfit.nsu.utils.Utils;
import d.zhdanov.graphql.types.ConstructionSite;
import d.zhdanov.graphql.types.ConstructionSiteInput;
import d.zhdanov.graphql.types.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ConstructionSiteService {
  private final ConstructionProjectRepository constructionProjectRepository;
  private final ConstructionObjectsMapper     constructionObjectsMapper;
  private final ConstructionSiteRepository    constructionSiteRepository;
  
  public ConstructionSiteService(
    @Autowired ConstructionProjectRepository constructionProjectRepository,
    @Autowired ConstructionObjectsMapper constructionObjectsMapper,
    @Autowired ConstructionSiteRepository constructionSiteRepository
  ) {
    this.constructionProjectRepository = constructionProjectRepository;
    this.constructionObjectsMapper     = constructionObjectsMapper;
    this.constructionSiteRepository    = constructionSiteRepository;
  }
  
  public ConstructionSiteDTO findConstructionSite(UUID uuid) {
    final var saved =
      constructionSiteRepository.findById(uuid).orElseThrow(
        ConstructionSiteAbsent::new);
    return constructionObjectsMapper.toConstructionSiteDTO(saved);
  }
  
  
  public ConstructionSiteDTO findConstructionSiteBySiteManager(UUID uuid) {
    final var saved =
      constructionSiteRepository.findBySiteManagerId(uuid).orElseThrow(
        ConstructionSiteAbsent::new);
    return constructionObjectsMapper.toConstructionSiteDTO(saved);
  }
  
  public List<ConstructionSiteDTO> findAllConstructionSites(
    final Pagination pagination
  ) {
    final var pageable = Utils.getPageable(pagination);
    final var sites    = constructionSiteRepository.findAll(pageable).toList();
    return constructionObjectsMapper.toConstructionSiteDTOList(sites);
  }
  
  @Transactional
  public ConstructionProjectEntity prepareNewProjectForSite(final UUID siteId) {
    return constructionProjectRepository.save(new ConstructionProjectEntity(
      null,
      siteId
    ));
  }
  
  @Transactional
  public ConstructionSiteDTO createConstructionSite(ConstructionSiteInput input) {
    try {
      final var target = new ConstructionSiteEntity();
      constructionObjectsMapper.updateConstructionSiteEntity(target, input);
      final var ret = constructionSiteRepository.save(target);
      return constructionObjectsMapper.toConstructionSiteDTO(ret);
    } catch(DataIntegrityViolationException e) {
      log.error("during construction site create", e);
      throw new ConstructionSiteCreateException(e.getMessage());
    }
  }
  
  @Transactional
  public ConstructionSiteDTO updateConstructionSite(
    final UUID uuid,
    final ConstructionSiteInput input
  ) {
    final var saved = constructionSiteRepository.findById(uuid).orElseThrow(
      ConstructionSiteAbsent::new);
    constructionObjectsMapper.updateConstructionSiteEntity(saved, input);
    final var ret = constructionSiteRepository.save(saved);
    return constructionObjectsMapper.toConstructionSiteDTO(ret);
  }
  
  @Transactional
  public Boolean deleteConstructionSite(UUID uuid) {
    constructionSiteRepository.deleteById(uuid);
    return true;
  }
}
