package d.zhdanov.ccfit.nsu.objects.service;

import d.zhdanov.ccfit.nsu.objects.exceptions.ConstructionManagementAbsent;
import d.zhdanov.ccfit.nsu.objects.exceptions.ConstructionManagementCreateException;
import d.zhdanov.ccfit.nsu.objects.exceptions.ConstructionSiteAbsent;
import d.zhdanov.ccfit.nsu.objects.mappers.ConstructionObjectsMapper;
import d.zhdanov.ccfit.nsu.objects.persistence.ConstructionManagementRepository;
import d.zhdanov.ccfit.nsu.objects.persistence.entities.ConstructionManagementEntity;
import d.zhdanov.ccfit.nsu.util.Utils;
import d.zhdanov.graphql.types.ConstructionManagement;
import d.zhdanov.graphql.types.ConstructionManagementInput;
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
public class ConstructionManagementService {
  private final ConstructionManagementRepository
                                          constructionManagementRepository;
  private final ConstructionObjectsMapper constructionObjectsMapper;
  
  public ConstructionManagementService(
    @Autowired
    ConstructionManagementRepository constructionManagementRepository,
    @Autowired ConstructionObjectsMapper constructionObjectsMapper
  ) {
    this.constructionManagementRepository = constructionManagementRepository;
    this.constructionObjectsMapper        = constructionObjectsMapper;
  }
  
  public ConstructionManagement findConstructionManagement(final UUID uuid) {
    final var saved =
      constructionManagementRepository.findById(uuid).orElseThrow(
        ConstructionManagementAbsent::new);
    return constructionObjectsMapper.toConstructionManagement(saved);
  }
  
  public List<ConstructionManagement> findAllConstructionManagement(
    final Pagination pagination
  ) {
    final var pageable = Utils.getPageable(pagination);
    final var saved =
      constructionManagementRepository.findAll(pageable).toList();
    return constructionObjectsMapper.toConstructionManagementList(saved);
  }
  
  @Transactional
  public ConstructionManagement createConstructionManagement(
    final ConstructionManagementInput input
  ) {
    try {
      final var entity = new ConstructionManagementEntity();
      constructionObjectsMapper.updateConstructionManagementEntity(
        entity,
        input
      );
      final var ret = constructionManagementRepository.save(entity);
      return constructionObjectsMapper.toConstructionManagement(ret);
    } catch(DataIntegrityViolationException e) {
      log.error("during construction site create", e);
      throw new ConstructionManagementCreateException(e.getMessage());
    }
  }
  
  @Transactional
  public ConstructionManagement updateConstructionManagement(
    final UUID uuid,
    final ConstructionManagementInput input
  ) {
    final var saved =
      constructionManagementRepository.findById(uuid).orElseThrow(
        ConstructionSiteAbsent::new);
    constructionObjectsMapper.updateConstructionManagementEntity(saved, input);
    final var ret = constructionManagementRepository.save(saved);
    return constructionObjectsMapper.toConstructionManagement(ret);
  }
  
  public void deleteConstructionManagement(final UUID uuid) {
    constructionManagementRepository.deleteById(uuid);
  }
}
