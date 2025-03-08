package d.zhdanov.ccfit.nsu.activity.controller;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import d.zhdanov.ccfit.nsu.activity.mapper.ActivityMapper;
import d.zhdanov.ccfit.nsu.activity.persistence.entities.ProjectContractEntity;
import d.zhdanov.ccfit.nsu.activity.service.ProjectService;
import d.zhdanov.ccfit.nsu.objects.service.ObjectsService;
import d.zhdanov.graphql.types.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@DgsComponent
public class ActivityDataFetcher {
  private final ObjectsService objectsService;
  private final ProjectService projectService;
  private final ActivityMapper activityMapper;
  
  public ActivityDataFetcher(
    @Autowired ObjectsService objectsService,
    @Autowired ProjectService projectService,
    @Autowired ActivityMapper activityMapper
  ) {
    this.objectsService = objectsService;
    this.projectService = projectService;
    this.activityMapper = activityMapper;
  }
  
  @DgsMutation
  @Transactional
  public ProjectContract addBridgeProjectContract(
    @InputArgument ProjectContractInput input,
    @InputArgument BridgeProjectInput project
  ) {
    return commonAddProjectContractAction(
      input,
      project,
      projectService::saveBridgeProjectContract
    );
  }
  
  @DgsMutation
  @Transactional
  public ProjectContract addSchoolProjectContract(
    @InputArgument ProjectContractInput input,
    @InputArgument SchoolProjectInput project
  ) {
    return commonAddProjectContractAction(
      input,
      project,
      projectService::saveSchoolProjectContract
    );
  }
  
  @DgsMutation
  @Transactional
  public ProjectContract addApartmentHouseProjectContract(
    @InputArgument ProjectContractInput input,
    @InputArgument ApartmentHouseProjectInput project
  ) {
    return commonAddProjectContractAction(
      input,
      project,
      projectService::saveApartmentHouseProjectContract
    );
  }
  
  
  
  @Transactional
  public <T> ProjectContract commonAddProjectContractAction(
    ProjectContractInput input,
    T project,
    TriFunction<UUID, ProjectContractInput, T, ProjectContractEntity> contractSaver
  ) {
    final var siteIdUUID = UUID.fromString(input.getSiteId());
    final var constructionProjectEntity =
      objectsService.prepareNewProjectForSite(siteIdUUID);
    
    final var contract = contractSaver.apply(
      constructionProjectEntity.getId(),
      input,
      project
    );
    return activityMapper.toProjectContract(contract);
  }
  
  @FunctionalInterface
  public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
  }
}
