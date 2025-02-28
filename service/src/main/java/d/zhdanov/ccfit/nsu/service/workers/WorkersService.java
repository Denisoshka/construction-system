package d.zhdanov.ccfit.nsu.service.workers;

import d.zhdanov.ccfit.nsu.exceptions.workers.EmployeeAlreadyExistsException;
import d.zhdanov.ccfit.nsu.exceptions.workers.EmployeeNotFoundException;
import d.zhdanov.ccfit.nsu.mapper.workers.WorkersMapper;
import d.zhdanov.ccfit.nsu.persistence.workers.EmployeeRepository;
import d.zhdanov.ccfit.nsu.persistence.workers.EngineersPositionRepository;
import d.zhdanov.ccfit.nsu.persistence.workers.WorkersPositionRepository;
import d.zhdanov.ccfit.nsu.persistence.workers.dto.EmployeeDTO;
import d.zhdanov.ccfit.nsu.persistence.workers.dto.EngineerPositionDTO;
import d.zhdanov.ccfit.nsu.persistence.workers.dto.WorkerPositionDTO;
import d.zhdanov.ccfit.nsu.service.workers.dto.EmployeeInfoDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class WorkersService {
  
  private final EmployeeRepository            employeeRepository;
  private final WorkerEngineerPositionService workerEngineerPositionService;
  private final WorkersMapper                 workersMapper;
  
  public WorkersService(@Autowired EmployeeRepository employeeRepository,
                        @Autowired
                        WorkersPositionRepository workersPositionRepository,
                        @Autowired
                        EngineersPositionRepository engineersPositionRepository,
                        @Autowired
                        WorkerEngineerPositionService workerEngineerPositionService,
                        @Autowired WorkersMapper workersMapper
  ) {
    this.workerEngineerPositionService = workerEngineerPositionService;
    this.employeeRepository            = employeeRepository;
    this.workersMapper                 = workersMapper;
  }
  
  @Transactional
  public EmployeeInfoDTO create(final @NotNull EmployeeInfoDTO input) {
    final var current = employeeRepository.findBySystemId(input.getSystemId());
    if(current != null) {
      throw new EmployeeAlreadyExistsException();
    }
    final var saved = workersMapper.toEmployeeDTO(input);
    employeeRepository.save(saved);
//    workerEngineerPositionService.preExecutePostPositionInsertInfo(input);
//    var saved = employeeRepository.save(input);
//    workerEngineerPositionService.insertPostPositionInfo(saved.getId(), input);
    return workersMapper.toEmployeeInfoDTO(saved);
  }
  
  @Transactional
  public EmployeeInfoDTO update(@NotNull final String systemId,
                                @NotNull final EmployeeInfoDTO input
  ) {
    final var current = employeeRepository.findBySystemId(systemId);
    if(current == null) {
      throw new EmployeeNotFoundException();
    }
    workerEngineerPositionService.updatePostPositionInfo(input, current);
    return null;
  }
  
  /**
   * @throws EmployeeNotFoundException if lol
   */
  public EmployeeDTO getBySystemId(final @NotNull String systemId) {
    final var employee = employeeRepository.findBySystemId(systemId);
    if(employee == null) {
      throw new EmployeeNotFoundException();
    }
    fillPositionInfo(employee);
    
    return employee;
  }
  
  private void fillPositionInfo(final @NotNull EmployeeDTO employee) {
    final var posInfo = workerEngineerPositionService.getPostPositionInfo(
      employee.getPost(), employee.getId());
    if(posInfo instanceof EngineerPositionDTO engPos) {
      employee.setPosition(engPos.getName());
    } else if(posInfo instanceof WorkerPositionDTO workerPos) {
      employee.setPosition(workerPos.getName());
    } else {
      employee.setPosition(EmployeeRepository.UNDEFINED_POSITION);
      employee.setPost(EmployeeRepository.UNDEFINED_POST);
    }
  }
  
  public Page<EmployeeDTO> getAll(Pageable pageable) {
    return employeeRepository.findAll(pageable);
  }
  
  @Transactional
  public void delete(@NotNull final String systemId) {
    employeeRepository.deleteBySystemId(systemId);
  }
}
//Глава 8: Support Vector Machines  + Стохастический градиентный спуск (найти самостоятельно)
