package d.zhdanov.ccfit.nsu.service.workers;

import d.zhdanov.ccfit.nsu.exceptions.workers.*;
import d.zhdanov.ccfit.nsu.persistence.workers.*;
import d.zhdanov.ccfit.nsu.persistence.workers.dto.EmployeeDTO;
import d.zhdanov.ccfit.nsu.persistence.workers.dto.EngineerPositionDTO;
import d.zhdanov.ccfit.nsu.persistence.workers.dto.WorkerPositionDTO;
import d.zhdanov.ccfit.nsu.service.workers.dto.EmployeeInfoDTO;
import io.micrometer.common.util.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public class WorkerEngineerPositionService {
  private final WorkersPositionRepository   workersPositionRepository;
  private final EngineersPositionRepository engineersPositionRepository;
  private final WorkersRepository           workersRepository;
  private final EngineersRepository         engineersRepository;
  
  public WorkerEngineerPositionService(
    @Autowired WorkersPositionRepository workersPositionRepository,
    @Autowired WorkersRepository workersRepository,
    @Autowired EngineersPositionRepository engineersPositionRepository,
    @Autowired EngineersRepository engineersRepository
  ) {
    this.workersPositionRepository   = workersPositionRepository;
    this.engineersPositionRepository = engineersPositionRepository;
    this.engineersRepository         = engineersRepository;
    this.workersRepository           = workersRepository;
  }
  
  @Transactional
  public @NotNull WorkerPositionDTO createWorkerPosition(final @NotNull WorkerPositionDTO workerPosition
  ) throws WorkerPositionAlreadyExistsException {
    try {
      return workersPositionRepository.save(
        new WorkerPositionDTO(null, workerPosition.getName()));
    } catch(DataIntegrityViolationException _) {
      throw new WorkerPositionAlreadyExistsException();
    }
  }
  
  @Transactional
  public @NotNull EngineerPositionDTO createEngineerPosition(final @NotNull EngineerPositionDTO engineerPosition
  ) throws WorkerPositionAlreadyExistsException {
    try {
      return engineersPositionRepository.save(
        new EngineerPositionDTO(null, engineerPosition.getName()));
    } catch(DataIntegrityViolationException _) {
      throw new EngineerPositionAlreadyExistsException();
    }
  }
  
  @Transactional
  public void deleteWorkerPosition(final int id) {
    workersPositionRepository.deleteById(id);
  }
  
  
  @Transactional
  public void deleteEngineerPosition(final int id) {
    workersPositionRepository.deleteById(id);
  }
  
  @Transactional
  public void deletePositionInfo(final @NotNull EmployeeDTO current) {
//    todo
    /*if(EmployeeRepository.ENGINEER_POST.equals(current.getPost())) {
      engineersPositionRepository.deleteById((long) current.getId());
    } else if(EmployeeRepository.WORKERS_POST.equals(current.getPost())) {
      workersPositionRepository.deleteById((long) current.getId());
    } else if(!EmployeeRepository.UNDEFINED_POST.equals(current.getPost())) {
      throw new UnknownPostException();
    }
    current.setPost(EmployeeRepository.UNDEFINED_POST);
    current.setPosition(EmployeeRepository.UNDEFINED_POSITION);*/
  }
  
  @Transactional
  public void insertPostPositionInfo(final int employeeId,
                                     final @NotNull EmployeeDTO input
  ) {
//    todo
    /*if(EmployeeRepository.ENGINEER_POST.equals(input.getPost())) {
      insertEngineerPostInfo(employeeId, input);
    } else if(EmployeeRepository.WORKERS_POST.equals(input.getPost())) {
      insertWorkerPostInfo(employeeId, input);
    } else if(!EmployeeRepository.UNDEFINED_POST.equals(input.getPost())) {
      throw new PostPositionNotFoundException();
    }*/
  }
  
  @Transactional
  public void insertEngineerPostInfo(final UUID employeeId,
                                     final @NotNull EmployeeInfoDTO input
  ) {
    final var posInfo = engineersPositionRepository.findByName(
      input.getPosition()).orElseThrow(EngineerPositionNotFoundException::new);
    engineersRepository.insertEngineer(employeeId, posInfo.getId());
  }
  
  @Transactional
  public void insertWorkerPostInfo(final UUID employeeId,
                                   final @NotNull EmployeeInfoDTO input
  ) {
    final var posInfo = workersPositionRepository.findByName(
      input.getPosition()).orElseThrow(WorkerPositionNotFoundException::new);
    workersRepository.insertWorker(employeeId, posInfo.getId());
  }
  
  @Transactional
  public void updatePostPositionInfo(final @NotNull EmployeeInfoDTO input,
                                     final @NotNull EmployeeDTO current
  ) {
    /*if(input.getPost() == null) {
      input.setPost(current.getPost());
      if(input.getPosition() == null) {
        throw new PostPositionNotFoundException();
      }
    } else if(!current.getPost().equals(input.getPost())) {
      deletePositionInfo(current);
    }
    
    if(EmployeeRepository.WORKERS_POST.equals(input.getPost())) {
      if(!current.getPost().equals(input.getPost())) {
        insertWorkerPostInfo(input.getId(), input);
      } else {
        updateWorkerPosition(input.getPosition(), current);
      }
    } else if(EmployeeRepository.ENGINEER_POST.equals(input.getPost())) {
      if(current.getPost().equals(input.getPost())) {
        insertEngineerPostInfo(input.getId(), current);
      } else {
        updateEngineerPosition(input.getPosition(), current);
      }
    } else if(!EmployeeRepository.UNDEFINED_POST.equals(input.getPost())) {
      throw new UnknownPostException();
    }*/
  }
  
  @Transactional
  public EmployeeInfoDTO savePostPositionInfo(final EmployeeInfoDTO dto) {
    preExecute(dto);
    return dto;
  }
  
  public void preExecute(EmployeeInfoDTO dto) {
    if(StringUtils.isBlank(dto.getPost())) {
      dto.setPost(EmployeeRepository.UNDEFINED_POST);
    }
    if(StringUtils.isBlank(dto.getPosition())) {
      dto.setPosition(EmployeeRepository.UNDEFINED_POSITION);
    }
  }
  
  public void preExecutePostPositionInsertInfo(final @NotNull EmployeeDTO input
  ) {
    if(input.getPost() == null) {
      input.setPost(EmployeeRepository.UNDEFINED_POST);
      input.setPosition(EmployeeRepository.UNDEFINED_POSITION);
    }
    if(!EmployeeRepository.ENGINEER_POST.equals(input.getPost())
       && !EmployeeRepository.WORKERS_POST.equals(input.getPost())
       && !EmployeeRepository.UNDEFINED_POST.equals(input.getPost())) {
      throw new UnknownPostException();
    }
    if(input.getPosition() == null) {
      throw new PostPositionNotFoundException();
    }
  }
  
  /**
   * @throws EngineerPositionNotFoundException
   */
  public EngineerPositionDTO getEngineerPositionInfo(final int id) {
    return engineersPositionRepository.findById(id).orElseThrow(
      EngineerPositionNotFoundException::new);
  }
  
  public WorkerPositionDTO getWorkerPositionInfo(final int id) {
    return workersPositionRepository.findById(id).orElseThrow(
      WorkerPositionNotFoundException::new);
  }
  
  private void updateEngineerPosition(final String positionName,
                                      final @NotNull EmployeeDTO current
  ) {
    final var posInfo = engineersPositionRepository.findByName(positionName)
                                                   .orElseThrow(
                                                     EmployeeNotFoundException::new);
    /*engineersPositionRepository.updatePosition(
      current.getId(), posInfo.getId());*/
//    current.setPosition(posInfo.getName());
//    current.setPost(EmployeeRepository.ENGINEER_POST);
  }
  
  private void updateWorkerPosition(final String positionName,
                                    final @NotNull EmployeeDTO current
  ) {
    final var posInfo = workersPositionRepository.findByName(positionName)
                                                 .orElseThrow(
                                                   WorkerPositionNotFoundException::new);
//    workersPositionRepository.updatePosition(current.getId(), posInfo.getId());
//    current.setPosition(posInfo.getName());
//    current.setPost(EmployeeRepository.WORKERS_POST);
  }
}
