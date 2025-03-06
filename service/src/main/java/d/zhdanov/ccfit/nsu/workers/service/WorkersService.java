package d.zhdanov.ccfit.nsu.workers.service;

import d.zhdanov.ccfit.nsu.workers.exceptions.WorkerPositionAlreadyExistsException;
import d.zhdanov.ccfit.nsu.workers.exceptions.WorkerPositionNotFoundException;
import d.zhdanov.ccfit.nsu.workers.persistence.WorkersPositionRepository;
import d.zhdanov.ccfit.nsu.workers.persistence.WorkersRepository;
import d.zhdanov.ccfit.nsu.workers.persistence.dto.WorkerEntity;
import d.zhdanov.ccfit.nsu.workers.persistence.dto.WorkerPositionEntity;
import d.zhdanov.ccfit.nsu.workers.service.dto.EmployeeInfoDTO;
import d.zhdanov.graphql.types.WorkerPositionInput;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class WorkersService {
  private final WorkersPositionRepository workersPositionRepository;
  private final WorkersRepository         workersRepository;
  
  public WorkersService(
    @Autowired WorkersPositionRepository workersPositionRepository,
    @Autowired WorkersRepository workersRepository
  ) {
    this.workersPositionRepository = workersPositionRepository;
    this.workersRepository         = workersRepository;
  }
  
  @Transactional
  public @NotNull WorkerPositionEntity createWorkerPosition(
    final @NotNull WorkerPositionInput workerPosition
  ) throws WorkerPositionAlreadyExistsException {
    try {
      return workersPositionRepository.save(new WorkerPositionEntity(
        null,
                                                                     workerPosition.getName()
      ));
    } catch(DataIntegrityViolationException _) {
      throw new WorkerPositionAlreadyExistsException();
    }
  }
  
  @Transactional
  public void insertWorkerPostInfo(
    final UUID employeeId,
    final @NotNull EmployeeInfoDTO input
  ) {
    final var posInfo =
      workersPositionRepository.findById(input.getPositionId()).orElseThrow(
        WorkerPositionNotFoundException::new);
    workersRepository.insertWorker(employeeId, posInfo.getId());
  }
  
  @Transactional
  public void deleteWorkerPosition(final int id) {
    workersPositionRepository.deleteById(id);
  }
  
  public WorkerPositionEntity workerPosition(final Integer id) {
    return workersPositionRepository.findById(id).orElseThrow(
      WorkerPositionNotFoundException::new);
  }
  
  public Page<WorkerPositionEntity> workers(Pageable paged) {
    return workersPositionRepository.findAll(paged);
  }
  
  public WorkerEntity workerEmployeePosition(final UUID id) {
    return workersRepository.findById(id).orElseThrow();
  }
}
