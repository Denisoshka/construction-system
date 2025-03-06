package d.zhdanov.ccfit.nsu.workers.controllers;

import com.netflix.graphql.dgs.*;
import d.zhdanov.ccfit.nsu.util.Utils;
import d.zhdanov.ccfit.nsu.workers.service.EngineersService;
import d.zhdanov.ccfit.nsu.workers.service.WorkersService;
import d.zhdanov.graphql.types.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@DgsComponent
public class WorkersEngineersRankDataFetcher {
  private final WorkerEngineerPositionService workerEngineerPositionService;
  private final PostsPositionsMapper postsPositionsMapper;
  private final WorkersService   workersService;
  private final EngineersService engineersService;
  
  public WorkersEngineersRankDataFetcher(
    @Autowired
    final WorkerEngineerPositionService workerEngineerPositionService,
    @Autowired final PostsPositionsMapper postsPositionsMapper,
    @Autowired final WorkersService workersService,
    @Autowired final EngineersService engineersService
  ) {
    this.workerEngineerPositionService = workerEngineerPositionService;
    this.postsPositionsMapper = postsPositionsMapper;
    this.workersService   = workersService;
    this.engineersService = engineersService;
  }
  
  @DgsQuery
  public EngineerPosition engineerPosition(@InputArgument Integer id) {
    final var entity = engineersService.engineerPosition(id);
    return postsPositionsMapper.fromEngineerPositionEntity(entity);
  }
  
  @DgsQuery
  public List<WorkerPosition> workersPositions(
    @InputArgument Pagination pagination
  ) {
    final var paged = Utils.getPageable(pagination);
    final var rez   = workersService.workers(paged);
    return
  }
  
  @DgsQuery
  public List<EngineerPosition> engineersPositions(
    @InputArgument Pagination pagination
  ) {
    final var paged = Utils.getPageable(pagination);
    final var rez   = engineersService.engineers(paged).toList();
    return
  }
  
  @DgsMutation
  public void deleteWorkerPosition(@InputArgument Integer id) {
    workersService.deleteWorkerPosition(id);
  }
  
  @DgsMutation
  public void deleteEngineerPosition(@InputArgument Integer id) {
    engineersService.deleteEngineerPosition(id);
  }
  
  @DgsMutation
  public WorkerPosition createWorkerPosition(final @InputArgument WorkerPositionInput input) {
    final var ret = workersService.createWorkerPosition(input);
    return postsPositionsMapper.fromWorkerPositionEntity(ret);
  }
  
  @DgsMutation
  public EngineerPosition createEngineerPosition(final @InputArgument EngineerPositionInput input) {
    final var ret = engineersService.createEngineerPosition(input);
    return postsPositionsMapper.fromEngineerPositionEntity(ret);
  }
  
  @DgsEntityFetcher(name = "EngineerPosition")
  public EngineerPosition fetchEngineerPosition(
    @NotNull final Map<String, Object> values
  ) {
    final var id       = (Integer) values.get("id");
    final var position = engineersService.engineerPosition(id);
    return postsPositionsMapper.fromEngineerPositionEntity(position);
  }
  
  @DgsEntityFetcher(name = "WorkerPosition")
  public WorkerPosition fetchWorkerPosition(
    @NotNull final Map<String, Object> values
  ) {
    final var id       = (Integer) values.get("id");
    final var position = workersService.workerPosition(id);
    return postsPositionsMapper.fromWorkerPositionEntity(position);
  }
  
  @DgsData(parentType = "EngineerInfo", field = "EngineerPosition")
  public EngineerPosition getEngineerInfoEngineerPosition(
    DgsDataFetchingEnvironment dfe
  ) {
    EngineerInfo engineerInfo = dfe.getSource();
    return
  }
  
  @DgsData(parentType = "WorkerInfo", field = "WorkerPosition")
  public EngineerPosition getWorkerInfoWorkerPosition(
    DgsDataFetchingEnvironment dfe
  ) {
    EngineerInfo engineerInfo = dfe.getSource();
    return wo;
  }
}
