package d.zhdanov.ccfit.nsu.service.workers;

import d.zhdanov.ccfit.nsu.exceptions.workers.EmployeeAlreadyExistsException;
import d.zhdanov.ccfit.nsu.exceptions.workers.EmployeeNotFoundException;
import d.zhdanov.ccfit.nsu.mapper.workers.WorkersMapper;
import d.zhdanov.ccfit.nsu.persistence.workers.EmployeeRepository;
import d.zhdanov.ccfit.nsu.persistence.workers.EngineersPositionRepository;
import d.zhdanov.ccfit.nsu.persistence.workers.WorkersPositionRepository;
import d.zhdanov.ccfit.nsu.persistence.workers.dto.EmployeeDTO;
import d.zhdanov.ccfit.nsu.service.workers.dto.EmployeeInfoDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@Transactional(readOnly = true)
public class EmployeeService {
  
  private final EmployeeRepository            employeeRepository;
  private final WorkerEngineerPositionService workerEngineerPositionService;
  private final WorkersMapper                 workersMapper;
  
  public EmployeeService(@Autowired EmployeeRepository employeeRepository,
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
    final var current = employeeRepository.findBySystemId(input.getSystemId())
                                          .orElseThrow(
                                            EmployeeAlreadyExistsException::new);
//    final var xz    = workersMapper.toEmployeeDTO(input);

//    final var saved = employeeRepository.save(xz);
    workerEngineerPositionService.savePostPositionInfo(input);
//    workerEngineerPositionService.preExecutePostPositionInsertInfo(input);
//    var saved = employeeRepository.save(input);
//    workerEngineerPositionService.insertPostPositionInfo(saved.getId(), input);
    return workersMapper.toEmployeeInfoDTO(saved);
  }
  
  @Transactional
  public EmployeeInfoDTO update(@NotNull final UUID id,
                                @NotNull final EmployeeInfoDTO input
  ) {
    final var current = employeeRepository.findById(id).orElseThrow(
      EmployeeNotFoundException::new);
    workerEngineerPositionService.updatePostPositionInfo(input, current);
    return workersMapper.toEmployeeInfoDTO(current);
  }
  
  /**
   * @throws EmployeeNotFoundException if lol
   */
  public EmployeeDTO getById(final @NotNull UUID id) {
    final var employee = employeeRepository.findById(id).orElseThrow(
      EmployeeNotFoundException::new);
    fillPositionInfo(employee);
    
    return employee;
  }
  
  private void fillPositionInfo(final @NotNull EmployeeDTO employee) {
    /*final var posInfo = workerEngineerPositionService.getPostPositionInfo(
      employee.getPost(), employee.getId());
    if(posInfo instanceof EngineerPositionDTO engPos) {
      employee.setPosition(engPos.getName());
    } else if(posInfo instanceof WorkerPositionDTO workerPos) {
      employee.setPosition(workerPos.getName());
    } else {
      employee.setPosition(EmployeeRepository.UNDEFINED_POSITION);
      employee.setPost(EmployeeRepository.UNDEFINED_POST);
    }*/
  }
  
  public Page<EmployeeDTO> getAll(Pageable pageable) {
    return employeeRepository.findAll(pageable);
  }
  
  @Transactional
  public void delete(@NotNull final UUID id) {
    employeeRepository.deleteById(id);
  }
}
//Глава 8: Support Vector Machines  + Стохастический градиентный спуск (найти самостоятельно)
