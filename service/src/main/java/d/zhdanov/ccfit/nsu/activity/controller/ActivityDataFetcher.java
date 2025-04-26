package d.zhdanov.ccfit.nsu.activity.controller;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import d.zhdanov.ccfit.nsu.activity.service.ProjectService;
import d.zhdanov.graphql.types.Pagination;
import d.zhdanov.graphql.types.ProjectContract;
import d.zhdanov.graphql.types.ProjectContractInput;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@DgsComponent
public class ActivityDataFetcher {
  private final ProjectService projectService;
  
  public ActivityDataFetcher(
    @Autowired ProjectService projectService
  ) {
    this.projectService = projectService;
  }
  
  @DgsQuery
  public ProjectContract projectContract(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    return projectService.findProjectContract(uuid);
  }
  
  @DgsQuery
  public List<ProjectContract> contractsByConstructionSite(
    @InputArgument String id, @InputArgument Pagination pagination) {
    final var uuid = UUID.fromString(id);
    return projectService.contractsByConstructionSite(uuid, pagination);
  }
  
  @DgsMutation
  public Boolean deleteContract(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    return projectService.deleteProjectContract(uuid);
  }
  
  @DgsMutation
  public ProjectContract saveContract(
    @InputArgument ProjectContractInput input
  ) {
    return projectService.saveProjectContract(input);
  }
  
  @DgsMutation
  public ProjectContract updateContract(
    @InputArgument String id,
    @InputArgument ProjectContractInput update
  ) {
    final var uuid = UUID.fromString(id);
    return projectService.updateProjectContract(uuid, update);
  }
}
