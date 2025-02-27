package d.zhdanov.ccfit.nsu.controller.workers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import d.zhdanov.ccfit.nsu.mapper.workers.WorkersMapper;
import d.zhdanov.ccfit.nsu.service.workers.WorkersService;
import d.zhdanov.ccfit.nsu.util.Utils;
import d.zhdanov.graphql.types.Employee;
import d.zhdanov.graphql.types.EmployeeInput;
import d.zhdanov.graphql.types.Pagination;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
public class WorkersDataFetcher {
  private final WorkersService workersService;
  private final WorkersMapper  workersMapper;
  
  public WorkersDataFetcher(@Autowired WorkersService workersService,
                            @Autowired WorkersMapper workersMapper
  ) {
    this.workersService = workersService;
    this.workersMapper  = workersMapper;
  }
  
  @DgsQuery
  public Employee getEmployee(@InputArgument String systemId
  ) {
    final var emp = workersService.getBySystemId(systemId);
    return workersMapper.toEmployeeResponse(emp);
  }
  
  @DgsQuery
  public List<Employee> getEmployees(Pagination pagination) {
    final var paged     = Utils.getPageable(pagination);
    final var employees = workersService.getAll(paged).toList();
    return workersMapper.toEmployeeResponseList(employees);
  }
  
  @DgsMutation
  public Employee updateEmployee(@InputArgument String systemId,
                                 @InputArgument EmployeeInput employee
  ) {
    final var input = workersMapper.toEmployeeDTO(employee);
    final var ret   = workersService.update(systemId, input);
    return workersMapper.toEmployeeResponse(ret);
  }
  
  @DgsQuery
  public boolean deleteEployee(@InputArgument String systemId
  ) {
    return workersService.delete(systemId);
  }
  
}
