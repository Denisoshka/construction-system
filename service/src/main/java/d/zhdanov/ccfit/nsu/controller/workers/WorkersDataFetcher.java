package d.zhdanov.ccfit.nsu.controller.workers;

import com.netflix.graphql.dgs.*;
import d.zhdanov.ccfit.nsu.mapper.workers.WorkersMapper;
import d.zhdanov.ccfit.nsu.service.workers.EmployeeService;
import d.zhdanov.ccfit.nsu.util.Utils;
import d.zhdanov.graphql.types.Employee;
import d.zhdanov.graphql.types.EmployeeInput;
import d.zhdanov.graphql.types.Pagination;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@DgsComponent
public class WorkersDataFetcher {
  private final EmployeeService employeeService;
  private final WorkersMapper   employeeMapper;
  
  public WorkersDataFetcher(@Autowired EmployeeService employeeService,
                            @Autowired WorkersMapper employeeMapper
  ) {
    this.employeeService = employeeService;
    this.employeeMapper  = employeeMapper;
  }
  
  @DgsQuery
  public Employee getEmployee(@InputArgument String systemId) {
    final var id  = UUID.fromString(systemId);
    final var emp = employeeService.getById(id);
    return employeeMapper.toEmployeeResponse(emp);
  }
  
  @DgsQuery
  public List<Employee> getEmployees(Pagination pagination) {
    final var paged     = Utils.getPageable(pagination);
    final var employees = employeeService.getAll(paged).toList();
    return employeeMapper.toEmployeeResponseList(employees);
  }
  
  @DgsMutation
  public Employee createEmployee(final @InputArgument EmployeeInput input) {
    final var info = employeeMapper.toEmployeeInfoDTO(input);
    final var ret  = employeeService.create(info);
    return employeeMapper.toEmployeeResponse(ret);
  }
  
  @DgsMutation
  public Employee updateEmployee(final @InputArgument String id,
                                 final @InputArgument EmployeeInput employee
  ) {
    final var uuid  = UUID.fromString(id);
    final var input = employeeMapper.toEmployeeInfoDTO(employee);
    final var ret   = employeeService.update(uuid, input);
    return employeeMapper.toEmployeeResponse(ret);
  }
  
  @DgsMutation
  public boolean deleteEmployee(final @InputArgument String id) {
    final var uuid = UUID.fromString(id);
    employeeService.delete(uuid);
    return true;
  }
  
  @DgsEntityFetcher(name = "Employee")
  public Employee fetchEmployee(@NotNull final Map<String, Object> values) {
    final var id  = UUID.fromString((String) values.get("id"));
    final var ent = employeeService.getById(id);
    return employeeMapper.toEmployeeResponse(ent);
  }
}
