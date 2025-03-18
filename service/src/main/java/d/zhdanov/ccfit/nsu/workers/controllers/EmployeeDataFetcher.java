package d.zhdanov.ccfit.nsu.workers.controllers;

import com.netflix.graphql.dgs.*;
import d.zhdanov.ccfit.nsu.workers.mapper.EmployeeMapper;
import d.zhdanov.ccfit.nsu.workers.service.EmployeeService;
import d.zhdanov.graphql.DgsConstants;
import d.zhdanov.graphql.types.EmployeeFilter;
import d.zhdanov.graphql.types.EmployeeInfo;
import d.zhdanov.graphql.types.EmployeeInput;
import d.zhdanov.graphql.types.Pagination;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@DgsComponent
public class EmployeeDataFetcher {
  private final EmployeeService employeeService;
  private final EmployeeMapper  employeeMapper;
  
  public EmployeeDataFetcher(
    @Autowired EmployeeService employeeService,
    @Autowired EmployeeMapper employeeMapper
  ) {
    this.employeeService = employeeService;
    this.employeeMapper  = employeeMapper;
  }
  
  @DgsQuery
  public EmployeeInfo employee(final @InputArgument String id) {
    final var uuid = UUID.fromString(id);
    return employeeService.getEmployee(uuid);
  }
  
  @DgsQuery
  public List<EmployeeInfo> employees(
    final @InputArgument Pagination pagination,
    final @InputArgument EmployeeFilter employeeFilter
  ) {
    final var employees = employeeService.getAllEmployees(
      pagination,
      employeeFilter
    );
    return employeeMapper.toEmployeeResponseList(employees);
  }
  
  @DgsMutation
  public EmployeeInfo createEmployee(final @InputArgument EmployeeInput input) {
    return employeeService.create(input);
  }
  
  @DgsMutation
  public EmployeeInfo updateEmployee(
    final @InputArgument String id,
    final @InputArgument EmployeeInput input
  ) {
    final var uuid = UUID.fromString(id);
    return employeeService.update(uuid, input);
  }
  
  @DgsMutation
  public boolean deleteEmployee(final @InputArgument String id) {
    final var uuid = UUID.fromString(id);
    employeeService.delete(uuid);
    return true;
  }
  
  @DgsEntityFetcher(name = DgsConstants.EMPLOYEEINFO.TYPE_NAME)
  public EmployeeInfo fetchEmployeeInfo(
    @NotNull final Map<String, Object> values
  ) {
    final var id =
      UUID.fromString((String) values.get(DgsConstants.EMPLOYEEINFO.Id));
    return employeeService.getEmployee(id);
  }
}
