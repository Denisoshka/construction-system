package d.zhdanov.ccfit.nsu.service.workers;

import d.zhdanov.ccfit.nsu.exceptions.workers.EmployeeAlreadyExistsException;
import d.zhdanov.ccfit.nsu.exceptions.workers.EmployeeNotFoundException;
import d.zhdanov.ccfit.nsu.mapper.workers.EmployeeMapper;
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
  private final EmployeeMapper                employeeMapper;
  
  public EmployeeService(@Autowired EmployeeRepository employeeRepository,
                         @Autowired
                         WorkersPositionRepository workersPositionRepository,
                         @Autowired
                         EngineersPositionRepository engineersPositionRepository,
                         @Autowired
                         WorkerEngineerPositionService workerEngineerPositionService,
                         @Autowired EmployeeMapper employeeMapper
  ) {
    this.workerEngineerPositionService = workerEngineerPositionService;
    this.employeeRepository            = employeeRepository;
    this.employeeMapper                = employeeMapper;
  }
  
  @Transactional
  public EmployeeInfoDTO create(final @NotNull EmployeeInfoDTO input) {
    final var cur = employeeRepository.findBySystemId(input.getSystemId());
    if(cur.isPresent()) {
      throw new EmployeeAlreadyExistsException();
    }
//    workerEngineerPositionService.preExecuteForSave(input);
    workerEngineerPositionService.fixPostPositionForSave(input);
    final var forSave       = employeeMapper.toEmployeeDTO(input);
    final var savedEmployee = employeeRepository.save(forSave);
    final var savedPostInfo =
      workerEngineerPositionService.savePostPositionInfo(input);
    final var forRet = employeeMapper.toEmployeeInfoDTO(savedEmployee);
    return forRet;
  }
  
  @Transactional
  public EmployeeInfoDTO update(@NotNull final UUID id,
                                @NotNull final EmployeeInfoDTO input
  ) {
    final var current = employeeRepository.findById(id).orElseThrow(
      EmployeeNotFoundException::new);
    workerEngineerPositionService.updatePostPositionInfo(input, current);
    return employeeMapper.toEmployeeInfoDTO(current);
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
