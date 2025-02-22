package d.zhdanov.ccfit.nsu.service.workers;

import d.zhdanov.ccfit.nsu.exceptions.workers.EmployeeNotFoundException;
import d.zhdanov.ccfit.nsu.mapper.workers.WorkersMapper;
import d.zhdanov.ccfit.nsu.persistence.workers.WorkersRepository;
import d.zhdanov.ccfit.nsu.persistence.workers.dto.EmployeeDTO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class WorkersService {
  private final WorkersRepository workersRepository;
  private final WorkersMapper workersMapper;

  public WorkersService(@Autowired WorkersRepository employeeRepository,
                        @Autowired WorkersMapper workersMapper) {
    this.workersRepository = employeeRepository;
    this.workersMapper = workersMapper;
  }

  @Transactional
  public EmployeeDTO create(EmployeeDTO employeeDTO) {
    return workersRepository.save(employeeDTO);
  }

  @Transactional
  public EmployeeDTO update(EmployeeDTO employeeDTO) {
    if
  }

  public @NotNull EmployeeDTO getById(String systemId)
      throws EmployeeNotFoundException {
    final var dto = workersRepository.findBySystemId(systemId);
    if (dto == null) {
      throw new EmployeeNotFoundException();
    }
    return dto;
  }

  public Page<EmployeeDTO> getAll(Pageable pageable) {
    return workersRepository.findAll(pageable);
  }
}