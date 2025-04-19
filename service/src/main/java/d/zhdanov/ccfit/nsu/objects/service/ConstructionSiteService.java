package d.zhdanov.ccfit.nsu.objects.service;

import d.zhdanov.ccfit.nsu.objects.dto.ConstructionSiteDTO;
import d.zhdanov.ccfit.nsu.objects.exceptions.ConstructionSiteAbsent;
import d.zhdanov.ccfit.nsu.objects.mappers.ConstructionObjectsMapper;
import d.zhdanov.ccfit.nsu.objects.persistence.ConstructionSiteRepository;
import d.zhdanov.ccfit.nsu.utils.Utils;
import d.zhdanov.graphql.types.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ConstructionSiteService {
  private final ConstructionObjectsMapper  constructionObjectsMapper;
  private final ConstructionSiteRepository constructionSiteRepository;
  
  public ConstructionSiteService(
    @Autowired ConstructionObjectsMapper constructionObjectsMapper,
    @Autowired ConstructionSiteRepository constructionSiteRepository
  ) {
    this.constructionObjectsMapper  = constructionObjectsMapper;
    this.constructionSiteRepository = constructionSiteRepository;
  }
  
  public ConstructionSiteDTO findConstructionSite(UUID uuid) {
    final var saved = constructionSiteRepository.findById(uuid)
      .orElseThrow(ConstructionSiteAbsent::new);
    return constructionObjectsMapper.toConstructionSiteDTO(saved);
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  public ConstructionSiteDTO findConstructionSiteBySiteManager(UUID uuid) {
    final var saved = constructionSiteRepository.findBySiteManagerId(uuid)
      .orElseThrow(ConstructionSiteAbsent::new);
    return constructionObjectsMapper.toConstructionSiteDTO(saved);
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  public List<ConstructionSiteDTO> findAllConstructionSites(
    final Pagination pagination
  ) {
    final var pageable = Utils.getPageable(pagination);
    final var sites    = constructionSiteRepository.findAll(pageable).toList();
    return constructionObjectsMapper.toConstructionSiteDTOList(sites);
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  public List<ConstructionSiteDTO> findConstructionSites(
    Pagination pagination
  ) {
    final var paged = Utils.getPageable(pagination);
    final var ret   = constructionSiteRepository.findAll(paged).toList();
    return constructionObjectsMapper.toConstructionSiteDTOList(ret);
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  public ConstructionSiteDTO findConstructionSiteByEngineer(
    UUID uuid
  ) {
    final var ret = constructionSiteRepository.findByEngineerId(uuid)
      .orElseThrow(ConstructionSiteAbsent::new);
    return constructionObjectsMapper.toConstructionSiteDTO(ret);
  }
}
