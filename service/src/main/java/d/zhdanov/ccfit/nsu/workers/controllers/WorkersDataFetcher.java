package d.zhdanov.ccfit.nsu.workers.controllers;

import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import d.zhdanov.ccfit.nsu.util.Utils;
import d.zhdanov.ccfit.nsu.workers.service.WorkersService;
import d.zhdanov.graphql.types.EmployeeInfo;
import d.zhdanov.graphql.types.Pagination;
import d.zhdanov.graphql.types.WorkerInfo;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WorkersDataFetcher {
  private final WorkersService workersService;
  
  public WorkersDataFetcher(@Autowired WorkersService workersService) {
    this.workersService = workersService;
  }
  
  @DgsQuery
  public List<WorkerInfo> workers(
    @InputArgument Pagination pagination,
    @InputArgument Integer positionID
  ) {
    final var paged = Utils.getPageable(pagination);
    final var ret   = workersService.workers(paged, positionID);
    return employeeMapper.toEmployeeResponseList(ret);
  }
}
