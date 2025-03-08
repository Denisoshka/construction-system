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
import java.util.UUID;

public class WorkersDataFetcher {
  private final WorkersService workersService;
  
  public WorkersDataFetcher(
    @Autowired WorkersService workersService,
    @Autowired WorkersMapper workersMapper
  ) {
    this.workersService = workersService;
  }
  
  @DgsEntityFetcher(name = "WorkerPosition")
  public WorkerPosition fetchWorkerPosition(
    @NotNull final Map<String, Object> values
  ) {
    final var id = (Integer) values.get("id");
    return workersService.workerPosition(id);
  }
  
  @DgsQuery
  public WorkerInfo worker(@InputArgument String id) {
    return workersService.getWorker(UUID.fromString(id));
  }
  
  @DgsQuery
  public List<WorkerInfo> workers(
    @InputArgument Pagination pagination,
    @InputArgument WorkerFilter filter
  ) {
    return workersService.getAllWorkers(pagination, filter);
  }
  
  @DgsMutation
  public void deleteWorkerPosition(@InputArgument Integer id) {
    workersService.deleteWorkerPosition(id);
  }
  
  @DgsQuery
  public WorkerPosition workerPosition(@InputArgument Integer id) {
    return workersService.workerPosition(id);
  }
  
  @DgsQuery
  public WorkerPosition workerPositionByName(@InputArgument String name) {
    return workersService.workerPosition(name);
  }
  
  @DgsQuery
  public List<WorkerPosition> workersPositions(
    @InputArgument Pagination pagination
  ) {
    return workersService.workersPositions(pagination);
  }
}
