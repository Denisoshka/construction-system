package d.zhdanov.ccfit.nsu.controller.workers;

import com.netflix.graphql.dgs.*;
import d.zhdanov.ccfit.nsu.mapper.workers.EmployeeMapper;
import d.zhdanov.ccfit.nsu.service.workers.EmployeeService;
import d.zhdanov.ccfit.nsu.util.Utils;
import d.zhdanov.graphql.types.EmployeeInfo;
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
  private final EmployeeMapper  employeeMapper;
  
  public WorkersDataFetcher(@Autowired EmployeeService employeeService,
                            @Autowired EmployeeMapper employeeMapper
  ) {
    this.employeeService = employeeService;
    this.employeeMapper  = employeeMapper;
  }
  
  @DgsQuery
  public EmployeeInfo employee(final @InputArgument String id) {
    final var uuid = UUID.fromString(id);
    final var emp  = employeeService.getById(uuid);
    return employeeMapper.toEmployeeResponse(emp);
  }
  
  @DgsQuery
  public List<EmployeeInfo> employees(final @InputArgument Pagination pagination) {
    final var paged     = Utils.getPageable(pagination);
    final var employees = employeeService.getAll(paged).toList();
    return employeeMapper.toEmployeeResponseList(employees);
  }
  
  @DgsQuery
  public List<EmployeeInfo> engineers(@InputArgument Pagination pagination,
                                      @InputArgument Integer positionID
  ) {
    final var paged = Utils.getPageable(pagination);
    final var ret   = employeeService.engineers(paged, positionID);
  }
  
  @DgsQuery
  public List<EmployeeInfo> workers(@InputArgument Pagination pagination,
                                    @InputArgument Integer positionID
  ) {
    final var paged = Utils.getPageable(pagination);
    final var ret   = employeeService.workers(paged, positionID);
    return employeeMapper.toEmployeeResponseList(ret);
  }
  
  @DgsMutation
  public EmployeeInfo createEmployee(final @InputArgument EmployeeInput input) {
    final var employee = employeeMapper.toEmployeeInfoDTO(input);
    final var ret      = employeeService.create(employee);
    return employeeMapper.toEmployeeResponse(ret);
  }
  
  @DgsMutation
  public EmployeeInfo updateEmployee(final @InputArgument String id,
                                     final @InputArgument EmployeeInput input
  ) {
    final var uuid     = UUID.fromString(id);
    final var employee = employeeMapper.toEmployeeInfoDTO(input);
    final var ret      = employeeService.update(uuid, employee);
    return employeeMapper.toEmployeeResponse(ret);
  }
  
  @DgsMutation
  public boolean deleteEmployee(final @InputArgument String id) {
    final var uuid = UUID.fromString(id);
    employeeService.delete(uuid);
    return true;
  }
  
  @DgsEntityFetcher(name = "EmployeeInfo")
  public EmployeeInfo fetchEmployeeInfo(
    @NotNull final Map<String, Object> values
  ) {
    final var id  = UUID.fromString((String) values.get("id"));
    final var ent = employeeService.getById(id);
    return employeeMapper.toEmployeeResponse(ent);
  }
}
