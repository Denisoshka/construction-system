package d.zhdanov.ccfit.nsu.activity.controller;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import d.zhdanov.ccfit.nsu.activity.service.ProjectService;
import d.zhdanov.graphql.types.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@DgsComponent
public class ActivityDataFetcher {
  private final ProjectService projectService;
  
  public ActivityDataFetcher(
    @Autowired ProjectService projectService
  ) {
    this.projectService = projectService;
  }
  
  @DgsMutation
  @Transactional
  public ProjectContract addBridgeProjectContract(
    @InputArgument ProjectContractInput input,
    @InputArgument BridgeProjectInput project
  ) {
    return projectService.saveBridgeProjectContract(input, project);
  }
  
  @DgsMutation
  @Transactional
  public ProjectContract addSchoolProjectContract(
    @InputArgument ProjectContractInput input,
    @InputArgument SchoolProjectInput project
  ) {
    return projectService.saveSchoolProjectContract(input, project);
  }
  
  @DgsMutation
  @Transactional
  public ProjectContract addApartmentHouseProjectContract(
    @InputArgument ProjectContractInput input,
    @InputArgument ApartmentHouseProjectInput project
  ) {
    return projectService.saveApartmentHouseProjectContract(
      input, project
    );
  }
  
  @DgsMutation
  Boolean deleteSchoolProjectContract(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    return projectService.deleteSchoolProjectContract(uuid);
  }
  
  @DgsMutation
  Boolean deleteApartmentHouseProjectContract(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    return projectService.deleteApartmentHouseProjectContract(uuid);
  }
  
  @DgsMutation
  Boolean deleteBridgeProjectContract(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    return projectService.deleteBridgeProjectContract(uuid);
  }
}
