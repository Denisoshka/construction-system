package d.zhdanov.ccfit.nsu.workers.controllers;

import com.netflix.graphql.dgs.*;
import d.zhdanov.ccfit.nsu.workers.service.EngineersService;
import d.zhdanov.ccfit.nsu.workers.service.WorkersService;
import d.zhdanov.graphql.types.*;
import org.springframework.beans.factory.annotation.Autowired;

@DgsComponent
public class WorkersEngineersRankDataFetcher {
  private final WorkerEngineerPositionService workerEngineerPositionService;
  private final PostsPositionsMapper          postsPositionsMapper;
  private final WorkersService                workersService;
  private final EngineersService              engineersService;
  
  public WorkersEngineersRankDataFetcher(
    @Autowired
    final WorkerEngineerPositionService workerEngineerPositionService,
    @Autowired final PostsPositionsMapper postsPositionsMapper,
    @Autowired final WorkersService workersService,
    @Autowired final EngineersService engineersService
  ) {
    this.workerEngineerPositionService = workerEngineerPositionService;
    this.postsPositionsMapper          = postsPositionsMapper;
    this.workersService                = workersService;
    this.engineersService              = engineersService;
  }
  
  @DgsQuery
  public EngineerPosition engineerPosition(@InputArgument Integer id) {
    return engineersService.engineerPosition(id);
  }
  
  
  @DgsMutation
  public WorkerPosition createWorkerPosition(final @InputArgument WorkerPositionInput input) {
    return workersService.createWorkerPosition(input);
  }
  
  @DgsMutation
  public EngineerPosition createEngineerPosition(final @InputArgument EngineerPositionInput input) {
    final var ret = engineersService.createEngineerPosition(input);
    return postsPositionsMapper.fromEngineerPositionEntity(ret);
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
