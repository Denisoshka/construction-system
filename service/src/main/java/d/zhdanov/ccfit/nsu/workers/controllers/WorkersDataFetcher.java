package d.zhdanov.ccfit.nsu.workers.controllers;

import com.netflix.graphql.dgs.DgsEntityFetcher;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import d.zhdanov.ccfit.nsu.workers.mapper.WorkersMapper;
import d.zhdanov.ccfit.nsu.workers.service.WorkersService;
import d.zhdanov.graphql.types.Pagination;
import d.zhdanov.graphql.types.WorkerFilter;
import d.zhdanov.graphql.types.WorkerInfo;
import d.zhdanov.graphql.types.WorkerPosition;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

public class WorkersDataFetcher {
  private final WorkersService workersService;
  private final WorkersMapper  workersMapper;
  
  public WorkersDataFetcher(
    @Autowired WorkersService workersService,
    @Autowired WorkersMapper workersMapper
  ) {
    this.workersService = workersService;
    this.workersMapper  = workersMapper;
  }
  
  @DgsEntityFetcher(name = "WorkerPosition")
  public WorkerPosition fetchWorkerPosition(
    @NotNull final Map<String, Object> values
  ) {
    final var id = (Integer) values.get("id");
    return workersService.workerPosition(id);
  }
  
  @DgsQuery
  public List<WorkerInfo> workers(
    @InputArgument Pagination pagination,
    @InputArgument WorkerFilter filter
  ) {
    return workersService.workers(pagination, filter);
  }
  
  @DgsMutation
  public void deleteWorkerPosition(@InputArgument Integer id) {
    workersService.deleteWorkerPosition(id);
  }
  
  @DgsQuery
  public List<WorkerPosition> workersPositions(
    @InputArgument Pagination pagination
  ) {
    return workersService.workersPositions(pagination);
  }
}
