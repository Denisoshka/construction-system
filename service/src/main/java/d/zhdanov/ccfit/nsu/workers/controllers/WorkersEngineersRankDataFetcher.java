package d.zhdanov.ccfit.nsu.workers.controllers;

import com.netflix.graphql.dgs.*;
import d.zhdanov.ccfit.nsu.util.Utils;
import d.zhdanov.ccfit.nsu.workers.mapper.PostsPositionsMapper;
import d.zhdanov.ccfit.nsu.workers.service.WorkerEngineerPositionService;
import d.zhdanov.graphql.types.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

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
  
  @DgsQuery
  public List<WorkerPositionInfo> workersPositions(
    @InputArgument Pagination pagination
  ) {
    final var paged = Utils.getPageable(pagination);
    final var rez   = workerEngineerPositionService.workers(paged);
  }
  
  @DgsQuery
  public List<EngineerPositionInfo> engineerPositions(
    @InputArgument Pagination pagination
  ) {
    final var paged = Utils.getPageable(pagination);
    final var rez   = workerEngineerPositionService.engineers(paged).toList();
  }
  
  @DgsMutation
  public void deleteWorkerPosition(@InputArgument Integer id) {
    workerEngineerPositionService.deleteWorkerPosition(id);
  }
  
  @DgsMutation
  public void deleteEngineerPosition(@InputArgument Integer id) {
    workerEngineerPositionService.deleteEngineerPosition(id);
  }
  
  @DgsMutation
  public WorkerPositionInfo createWorkerPosition(final @InputArgument WorkerPositionInput input) {
    final var dto = postsPositionsMapper.toWorkerPositionDTO(input);
    final var ret = workerEngineerPositionService.createWorkerPosition(dto);
    return postsPositionsMapper.fromWorkerPositionDTO(ret);
  }
  
  @DgsMutation
  public EngineerPositionInfo createEngineerPosition(final @InputArgument EngineerPositionInput input) {
    final var dto = postsPositionsMapper.toEngineerPositionDTO(input);
    final var ret = workerEngineerPositionService.createEngineerPosition(dto);
    return postsPositionsMapper.fromEngineerPositionDTO(ret);
  }
  
  @DgsEntityFetcher(name = "EngineerPositionInfo")
  public EngineerPositionInfo fetchEngineerPosition(
    @NotNull final Map<String, Object> values
  ) {
    final int id = (int) values.get("id");
    final var position = workerEngineerPositionService.getEngineerPositionInfo(
      id);
    return postsPositionsMapper.fromEngineerPositionDTO(position);
  }
  
  @DgsEntityFetcher(name = "WorkerPositionInfo")
  public WorkerPositionInfo fetchWorkerPosition(
    @NotNull final Map<String, Object> values
  ) {
    final int id = (int) values.get("id");
    final var position = workerEngineerPositionService.getWorkerPositionInfo(
      id);
    return postsPositionsMapper.fromWorkerPositionDTO(position);
  }
}
