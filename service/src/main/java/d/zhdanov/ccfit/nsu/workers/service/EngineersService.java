package d.zhdanov.ccfit.nsu.workers.service;

import d.zhdanov.ccfit.nsu.workers.exceptions.EngineerPositionAlreadyExistsException;
import d.zhdanov.ccfit.nsu.workers.exceptions.EngineerPositionNotFoundException;
import d.zhdanov.ccfit.nsu.workers.persistence.EngineersPositionRepository;
import d.zhdanov.ccfit.nsu.workers.persistence.EngineersRepository;
import d.zhdanov.ccfit.nsu.workers.persistence.dto.EngineerEntity;
import d.zhdanov.ccfit.nsu.workers.persistence.dto.EngineerPositionEntity;
import d.zhdanov.ccfit.nsu.workers.service.dto.EmployeeInfoDTO;
import d.zhdanov.graphql.types.EngineerPositionInput;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
@Service
public class EngineersService {
  private final EngineersPositionRepository engineersPositionRepository;
  private final EngineersRepository         engineersRepository;
  
  public EngineersService(
    @Autowired EngineersPositionRepository engineersPositionRepository,
    @Autowired EngineersRepository engineersRepository
  ) {
    this.engineersPositionRepository = engineersPositionRepository;
    this.engineersRepository         = engineersRepository;
  }
  
  @Transactional
  public void insertEngineerPostInfo(
    final UUID employeeId,
    final @NotNull EmployeeInfoDTO input
  ) {
    final var posInfo =
      engineersPositionRepository.findById(input.getPositionId()).orElseThrow(
        EngineerPositionNotFoundException::new);
    engineersRepository.insertEngineer(employeeId, posInfo.getId());
  }
  
  public Page<EngineerPositionEntity> engineers(Pageable paged) {
    return engineersPositionRepository.findAll(paged);
  }
  
  /**
   * @throws EngineerPositionNotFoundException
   */
  public EngineerPositionEntity engineerPosition(final Integer id) {
    return engineersPositionRepository.findById(id).orElseThrow(
      EngineerPositionNotFoundException::new);
  }
  
  public EngineerEntity engineerEmployeePosition(final UUID id) {
    return engineersRepository.findById(id).orElseThrow();
  }
  @Transactional
  public void deleteEngineerPosition(final int id) {
    engineersPositionRepository.deleteById(id);
  }
  
  @Transactional
  public @NotNull EngineerPositionEntity createEngineerPosition(
    final @NotNull EngineerPositionInput engineerPosition
  ) throws EngineerPositionAlreadyExistsException {
    try {
      return engineersPositionRepository.save(new EngineerPositionEntity(
        null,
        engineerPosition.getName()
      ));
    } catch(DataIntegrityViolationException _) {
      throw new EngineerPositionAlreadyExistsException();
    }
  }
}
