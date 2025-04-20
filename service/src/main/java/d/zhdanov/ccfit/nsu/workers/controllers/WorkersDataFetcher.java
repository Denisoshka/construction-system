package d.zhdanov.ccfit.nsu.workers.controllers;

import com.netflix.graphql.dgs.*;
import d.zhdanov.ccfit.nsu.objects.dto.BrigadeDTO;
import d.zhdanov.ccfit.nsu.workers.mapper.WorkersMapper;
import d.zhdanov.ccfit.nsu.workers.service.WorkersService;
import d.zhdanov.graphql.DgsConstants;
import d.zhdanov.graphql.types.*;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Slf4j
@DgsComponent
public class WorkersDataFetcher {
  private final WorkersService workersService;
  
  public WorkersDataFetcher(
    @Autowired WorkersService workersService,
    @Autowired WorkersMapper workersMapper
  ) {
    this.workersService = workersService;
  }
  
  @DgsEntityFetcher(name = DgsConstants.WORKERPOSITION.TYPE_NAME)
  public WorkerPosition fetchWorkerPosition(
    @NotNull final Map<String, Object> values
  ) {
    final var id = (Integer) values.get(DgsConstants.WORKERPOSITION.Id);
    return workersService.workerPosition(id);
  }
  
  @DgsQuery
  public WorkerInfo worker(@InputArgument String id) {
    return workersService.findWorker(UUID.fromString(id));
  }
  
  @DgsQuery
  public List<WorkerInfo> workers(
    @InputArgument Pagination pagination,
    @InputArgument WorkerFilter filter
  ) {
    return workersService.findAllWorkers(pagination, filter);
  }
  
  @DgsQuery
  public List<WorkerInfo> workersByBrigade(
    @InputArgument String id, @InputArgument Pagination pagination){
    final var uuid = UUID.fromString(id);
    return workersService.findAllWorkersByBrigade(uuid, pagination);
  }
  @DgsQuery
  public List<WorkerInfo> workersByConstructionSite(
    @InputArgument String id, @InputArgument Pagination pagination
  ) {
    final var uuid = UUID.fromString(id);
    return workersService.findAllWorkersByConstructionSite(
      uuid, pagination
    );
  }

  @DgsMutation
  public WorkerPosition createWorkerPosition(
    final @InputArgument WorkerPositionInput input
  ) {
    return workersService.createWorkerPosition(input);
  }
  
  @DgsMutation
  public WorkerPosition updateWorkerPosition(
    @InputArgument Integer id,
    @InputArgument
    WorkerPositionInput input
  ) {
    return workersService.updateWorkerPosition(id, input);
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
  
  @DgsData(
    parentType = DgsConstants.BRIGADE.TYPE_NAME, field = DgsConstants.BRIGADE.Foreman
  )
  public WorkerInfo brigadeWorkerInfo(
    DgsDataFetchingEnvironment dfe
  ) {
    final BrigadeDTO brigadeDTO = dfe.getSource();
    return worker(brigadeDTO.getForemanId().toString());
  }
  
  @DgsQuery
  public List<WorkerInfo> brigadeWorkers(
    @InputArgument String id,
    @InputArgument Pagination pagination
  ) {
    final var uuid = UUID.fromString(id);
    return workersService.findAllWorkersInBrigade(uuid, pagination);
  }
}
