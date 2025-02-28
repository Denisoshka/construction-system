package d.zhdanov.ccfit.nsu.service.workers;

import d.zhdanov.ccfit.nsu.exceptions.workers.EngineerPositionAlreadyExistsException;
import d.zhdanov.ccfit.nsu.exceptions.workers.PostPositionNotFoundException;
import d.zhdanov.ccfit.nsu.exceptions.workers.UnknownPostException;
import d.zhdanov.ccfit.nsu.exceptions.workers.WorkerPositionAlreadyExistsException;
import d.zhdanov.ccfit.nsu.persistence.workers.EmployeeRepository;
import d.zhdanov.ccfit.nsu.persistence.workers.EngineersPositionRepository;
import d.zhdanov.ccfit.nsu.persistence.workers.WorkersPositionRepository;
import d.zhdanov.ccfit.nsu.persistence.workers.dto.EmployeeDTO;
import d.zhdanov.ccfit.nsu.persistence.workers.dto.EngineerPositionDTO;
import d.zhdanov.ccfit.nsu.persistence.workers.dto.WorkerPositionDTO;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.transaction.annotation.Transactional;

public class WorkerEngineerPositionService {
  private final WorkersPositionRepository   workersPositionRepository;
  private final EngineersPositionRepository engineersPositionRepository;
  
  public WorkerEngineerPositionService(
    @Autowired WorkersPositionRepository workersPositionRepository,
    @Autowired EngineersPositionRepository engineersPositionRepository
  ) {
    this.workersPositionRepository   = workersPositionRepository;
    this.engineersPositionRepository = engineersPositionRepository;
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
    workersPositionRepository.deleteById((long) id);
  }
  
  
  @Transactional
  public void deleteEngineerPosition(final int id) {
    workersPositionRepository.deleteById((long) id);
  }
  
  @Transactional
  
  public void deletePositionInfo(final @NotNull EmployeeDTO current) {
    if(EmployeeRepository.ENGINEER_POST.equals(current.getPost())) {
      engineersPositionRepository.deleteById((long) current.getId());
    } else if(EmployeeRepository.WORKERS_POST.equals(current.getPost())) {
      workersPositionRepository.deleteById((long) current.getId());
    } else if(!EmployeeRepository.UNDEFINED_POST.equals(current.getPost())) {
      throw new UnknownPostException();
    }
    current.setPost(EmployeeRepository.UNDEFINED_POST);
    current.setPosition(EmployeeRepository.UNDEFINED_POSITION);
  }
  
  @Transactional
  public void insertPostPositionInfo(final int employeeId,
                                     final @NotNull EmployeeDTO input
  ) {
    if(EmployeeRepository.ENGINEER_POST.equals(input.getPost())) {
      insertEngineerPostInfo(employeeId, input);
    } else if(EmployeeRepository.WORKERS_POST.equals(input.getPost())) {
      insertWorkerPostInfo(employeeId, input);
    } else if(!EmployeeRepository.UNDEFINED_POST.equals(input.getPost())) {
      throw new PostPositionNotFoundException();
    }
  }
  
  private void insertWorkerPostInfo(final int employeeId,
                                    final @NotNull EmployeeDTO input
  ) {
    final var posInfo = engineersPositionRepository.findByName(
      input.getPosition());
    if(posInfo == null) {
      throw new PostPositionNotFoundException();
    }
    engineersPositionRepository.insertEngineer(employeeId, posInfo.getId());
  }
  
  private void insertEngineerPostInfo(final int employeeId,
                                      final @NotNull EmployeeDTO input
  ) {
    final var posInfo = workersPositionRepository.findByName(
      input.getPosition());
    if(posInfo == null) {
      throw new PostPositionNotFoundException();
    }
    workersPositionRepository.insertWorker(employeeId, posInfo.getId());
  }
  
  @Transactional
  public void updatePostPositionInfo(final @NotNull EmployeeDTO input,
                                     final @NotNull EmployeeDTO current
  ) {
    if(input.getPost() == null) {
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
  
  public @Nullable Object getPostPositionInfo(final String post,
                                              final long id
  ) {
    if(EmployeeRepository.ENGINEER_POST.equals(post)) {
      return engineersPositionRepository.findById(id);
    } else if(EmployeeRepository.WORKERS_POST.equals(post)) {
      return workersPositionRepository.findById(id);
    }
    return null;
  }
  
  private void updateEngineerPosition(final String positionName,
                                      final @NotNull EmployeeDTO current
  ) {
    final var posInfo = engineersPositionRepository.findByName(positionName);
    if(posInfo == null) {
      throw new PostPositionNotFoundException();
    }
    engineersPositionRepository.updatePosition(
      current.getId(),
      posInfo.getId()
    );
    current.setPosition(posInfo.getName());
    current.setPost(EmployeeRepository.ENGINEER_POST);
  }
  
  private void updateWorkerPosition(final String positionName,
                                    final @NotNull EmployeeDTO current
  ) {
    final var posInfo = workersPositionRepository.findByName(positionName);
    if(posInfo == null) {
      throw new PostPositionNotFoundException();
    }
    workersPositionRepository.updatePosition(current.getId(), posInfo.getId());
    current.setPosition(posInfo.getName());
    current.setPost(EmployeeRepository.WORKERS_POST);
  }
}
