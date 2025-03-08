package d.zhdanov.ccfit.nsu.workers.controllers;

import com.netflix.graphql.dgs.*;
import d.zhdanov.ccfit.nsu.workers.mapper.EngineersMapper;
import d.zhdanov.ccfit.nsu.workers.service.EngineersService;
import d.zhdanov.graphql.types.EngineerFilter;
import d.zhdanov.graphql.types.EngineerInfo;
import d.zhdanov.graphql.types.EngineerPosition;
import d.zhdanov.graphql.types.Pagination;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@DgsComponent
public class EngineersDataFetcher {
  private final EngineersService engineersService;
  
  public EngineersDataFetcher(
    @Autowired EngineersService engineersService,
    @Autowired EngineersMapper engineersMapper
  ) {
    this.engineersService = engineersService;
  }
  
  @DgsEntityFetcher(name = "EngineerPosition")
  public EngineerPosition fetchEngineerPosition(
    @NotNull final Map<String, Object> values
  ) {
    final var id = (Integer) values.get("id");
    return engineersService.engineerPosition(id);
  }
  
  @DgsQuery
  public List<EngineerInfo> engineers(
    @InputArgument Pagination pagination,
    @InputArgument EngineerFilter engineerFilter
  ) {
    return engineersService.findAll(pagination, engineerFilter);
  }
  
  @DgsQuery
  public EngineerInfo engineer(@InputArgument String id) {
    return engineersService.find(UUID.fromString(id));
  }
  
  @DgsQuery
  public List<EngineerPosition> engineersPositions(
    @InputArgument Pagination pagination
  ) {
    return engineersService.engineersPositions(pagination);
  }
  
  @DgsQuery
  public EngineerPosition engineerPositionByName(@InputArgument String name) {
    return engineersService.engineerPosition(name);
  }
  
  @DgsMutation
  public void deleteEngineerPosition(@InputArgument Integer id) {
    engineersService.deleteEngineerPosition(id);
  }
}
