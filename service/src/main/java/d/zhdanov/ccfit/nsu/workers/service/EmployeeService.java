package d.zhdanov.ccfit.nsu.workers.service;

import d.zhdanov.ccfit.nsu.util.Utils;
import d.zhdanov.ccfit.nsu.workers.exceptions.EmployeeAlreadyExistsException;
import d.zhdanov.ccfit.nsu.workers.exceptions.EmployeeNotFoundException;
import d.zhdanov.ccfit.nsu.workers.mapper.EmployeeMapper;
import d.zhdanov.ccfit.nsu.workers.persistence.EmployeeRepository;
import d.zhdanov.ccfit.nsu.workers.persistence.EngineersPositionRepository;
import d.zhdanov.ccfit.nsu.workers.persistence.WorkersPositionRepository;
import d.zhdanov.ccfit.nsu.workers.persistence.entities.EmployeeEntity;
import d.zhdanov.graphql.types.EmployeeFilter;
import d.zhdanov.graphql.types.EmployeeInfo;
import d.zhdanov.graphql.types.EmployeeInput;
import d.zhdanov.graphql.types.Pagination;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@Transactional(readOnly = true)
public class EmployeeService {
  
  private final EmployeeRepository employeeRepository;
  private final EmployeeMapper     employeeMapper;
  
  public EmployeeService(
    @Autowired EmployeeRepository employeeRepository,
    @Autowired WorkersPositionRepository workersPositionRepository,
    @Autowired EngineersPositionRepository engineersPositionRepository,
    @Autowired EmployeeMapper employeeMapper
  ) {
    this.employeeRepository = employeeRepository;
    this.employeeMapper     = employeeMapper;
  }
  
  public EmployeeInfo getEmployee(@NotNull final UUID id) {
    final var ret = employeeRepository.findById(id)
      .orElseThrow(EmployeeNotFoundException::new);
    return employeeMapper.toEmployeeInfo(ret);
  }
  
  public List<EmployeeEntity> getAllEmployees(
    final Pagination pagination,
    final EmployeeFilter employeeFilter
  ) {
    final var paged  = Utils.getPageable(pagination);
    final var filter = Utils.getRepositoryEmployeeFilter(employeeFilter);
    
    if(filter.getPost() != null) {
      return employeeRepository.findAllByPost(filter.getPost(), paged);
    }
    return employeeRepository.findAll(paged).toList();
  }
  
  @Transactional
  public EmployeeEntity create(final @NotNull EmployeeInput input) {
    final var cur = employeeRepository.findBySystemId(input.getSystemId());
    if(cur.isPresent()) {
      throw new EmployeeAlreadyExistsException();
    }
    final var entity = employeeMapper.toEmployeeEntity(input);
    
    return employeeRepository.save(entity);
  }
  
  @Transactional
  public void delete(@NotNull final UUID id) {
    employeeRepository.deleteById(id);
  }
}
