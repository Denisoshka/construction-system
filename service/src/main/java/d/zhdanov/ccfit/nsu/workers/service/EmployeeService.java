package d.zhdanov.ccfit.nsu.workers.service;

import d.zhdanov.ccfit.nsu.workers.exceptions.EmployeeAlreadyExistsException;
import d.zhdanov.ccfit.nsu.workers.mapper.EmployeeMapper;
import d.zhdanov.ccfit.nsu.workers.persistence.EmployeeRepository;
import d.zhdanov.ccfit.nsu.workers.persistence.EngineersPositionRepository;
import d.zhdanov.ccfit.nsu.workers.persistence.WorkersPositionRepository;
import d.zhdanov.ccfit.nsu.workers.persistence.entities.EmployeeEntity;
import d.zhdanov.graphql.types.EmployeeInput;
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
  
  private final EmployeeRepository employeeRepository;
  private final EmployeeMapper     employeeMapper;
  
  public EmployeeService(
    @Autowired EmployeeRepository employeeRepository,
    @Autowired
    WorkersPositionRepository workersPositionRepository,
    @Autowired
    EngineersPositionRepository engineersPositionRepository,
    @Autowired EmployeeMapper employeeMapper
  ) {
    this.employeeRepository = employeeRepository;
    this.employeeMapper     = employeeMapper;
  }
  
  @Transactional
  public EmployeeInfoDTO create(final @NotNull EmployeeInput input) {
    final var cur = employeeRepository.findBySystemId(input.getSystemId());
    if(cur.isPresent()) {
      throw new EmployeeAlreadyExistsException();
    }
  }
  
  public Page<EmployeeEntity> getAll(Pageable pageable) {
    return employeeRepository.findAll(pageable);
  }
  
  @Transactional
  public void delete(@NotNull final UUID id) {
    employeeRepository.deleteById(id);
  }
}
