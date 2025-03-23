package d.zhdanov.ccfit.nsu.workers.service;

import d.zhdanov.ccfit.nsu.utils.Utils;
import d.zhdanov.ccfit.nsu.workers.exceptions.EmployeeNotFoundException;
import d.zhdanov.ccfit.nsu.workers.exceptions.EngineerPositionAlreadyExistsException;
import d.zhdanov.ccfit.nsu.workers.exceptions.EngineerPositionNotFoundException;
import d.zhdanov.ccfit.nsu.workers.mapper.EngineersMapper;
import d.zhdanov.ccfit.nsu.workers.persistence.EngineersPositionRepository;
import d.zhdanov.ccfit.nsu.workers.persistence.EngineersRepository;
import d.zhdanov.ccfit.nsu.workers.persistence.entities.EngineerEntity;
import d.zhdanov.ccfit.nsu.workers.persistence.entities.EngineerPositionEntity;
import d.zhdanov.graphql.types.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class EngineersService {
  private final EngineersPositionRepository engineersPositionRepository;
  private final EngineersRepository         engineersRepository;
  private final EngineersMapper             engineersMapper;
  
  public EngineersService(
    @Autowired EngineersPositionRepository engineersPositionRepository,
    @Autowired EngineersRepository engineersRepository,
    @Autowired EngineersMapper engineersMapper
  ) {
    this.engineersPositionRepository = engineersPositionRepository;
    this.engineersRepository         = engineersRepository;
    this.engineersMapper             = engineersMapper;
  }
  
  public List<EngineerPosition> engineersPositions(Pagination pagination) {
    final var paged = Utils.getPageable(pagination);
    final var ret   = engineersPositionRepository.findAll(paged).toList();
    return engineersMapper.fromEngineerPositionEntityList(ret);
  }
  
  /**
   * @throws EngineerPositionNotFoundException
   */
  public EngineerPosition engineerPosition(final Integer id) {
    final var ret = engineersPositionRepository.findById(id)
      .orElseThrow(EngineerPositionNotFoundException::new);
    return engineersMapper.fromEngineerPositionEntity(ret);
  }
  
  public EngineerPosition engineerPosition(final String name) {
    final var ret = engineersPositionRepository.findByName(name)
      .orElseThrow(EngineerPositionNotFoundException::new);
    return engineersMapper.fromEngineerPositionEntity(ret);
  }
  
  public EngineerEntity engineerEmployeePosition(final UUID id) {
    return engineersRepository.findById(id).orElseThrow();
  }
  
  @Transactional
  public EngineerPosition updateEngineerPosition(
    final Integer id,
    final @NotNull EngineerPositionInput input
  ) {
    final var saved = engineersPositionRepository.findById(id)
      .orElseThrow(EngineerPositionNotFoundException::new);
    engineersMapper.updateEngineerPositionEntity(saved, input);
    final var ret = engineersPositionRepository.save(saved);
    return engineersMapper.fromEngineerPositionEntity(ret);
  }
  
  @Transactional
  public void deleteEngineerPosition(final int id) {
    engineersPositionRepository.deleteById(id);
  }
  
  @Transactional
  public @NotNull EngineerPosition createEngineerPosition(
    final @NotNull EngineerPositionInput engineerPosition
  ) throws EngineerPositionAlreadyExistsException {
    try {
      final var ret = engineersPositionRepository.save(
        new EngineerPositionEntity(null, engineerPosition.getName()));
      return engineersMapper.fromEngineerPositionEntity(ret);
    } catch(DataIntegrityViolationException _) {
      throw new EngineerPositionAlreadyExistsException();
    }
  }
  
  public EngineerInfo findEngineer(final UUID id) {
    final var ret = engineersRepository.findEngineerWithPositionEntity(id)
      .orElseThrow(EmployeeNotFoundException::new);
    return engineersMapper.fromEngineerEntityWithAdditionalData(ret);
  }
  
  public List<EngineerInfo> findAllEngineers(
    Pagination pagination,
    EngineerFilter engineerFilter
  ) {
    final var paged  = Utils.getPageable(pagination);
    final var filter = Utils.getRepositoryEngineerFilter(engineerFilter);
    
    final var ret =
      engineersRepository.findAllEngineersWithPositionEntity(
        paged.getOffset(),
        paged.getPageSize()
      );
    return engineersMapper.fromEngineerEntityList(ret);
  }
}
