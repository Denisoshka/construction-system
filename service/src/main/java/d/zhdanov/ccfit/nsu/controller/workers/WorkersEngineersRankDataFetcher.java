package d.zhdanov.ccfit.nsu.controller.workers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import d.zhdanov.ccfit.nsu.mapper.workers.PostsPositionsMapper;
import d.zhdanov.ccfit.nsu.service.workers.WorkerEngineerPositionService;
import d.zhdanov.graphql.types.EngineerPosition;
import d.zhdanov.graphql.types.EngineerPositionInput;
import d.zhdanov.graphql.types.WorkerPosition;
import d.zhdanov.graphql.types.WorkerPositionInput;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

@DgsComponent
public class WorkersEngineersRankDataFetcher {
  private final WorkerEngineerPositionService workerEngineerPositionService;
  private final PostsPositionsMapper          postsPositionsMapper;
  
  public WorkersEngineersRankDataFetcher(@Autowired @NotNull
                                         final WorkerEngineerPositionService workerEngineerPositionService,
                                         @Autowired @NotNull
                                         final PostsPositionsMapper postsPositionsMapper
  ) {
    this.workerEngineerPositionService = workerEngineerPositionService;
    this.postsPositionsMapper          = postsPositionsMapper;
  }
  
  @DgsMutation
  public void deleteWorkerPosition(@InputArgument final int id) {
    workerEngineerPositionService.deleteWorkerPosition(id);
  }
  
  @DgsMutation
  public void deleteEngineerPosition(@InputArgument final int id) {
    workerEngineerPositionService.deleteEngineerPosition(id);
  }
  
  @DgsMutation
  public WorkerPosition createWorkerPosition(final WorkerPositionInput input) {
    final var dto = postsPositionsMapper.toWorkerPositionDTO(input);
    final var ret = workerEngineerPositionService.createWorkerPosition(dto);
    return postsPositionsMapper.fromWorkerPositionDTO(ret);
  }
  
  @DgsMutation
  public EngineerPosition createEngineerPosition(final EngineerPositionInput input) {
    final var dto = postsPositionsMapper.toEngineerPositionDTO(input);
    final var ret = workerEngineerPositionService.createEngineerPosition(dto);
    return postsPositionsMapper.fromEngineerPositionDTO(ret);
  }
}
