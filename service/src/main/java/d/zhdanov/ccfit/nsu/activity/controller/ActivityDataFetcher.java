package d.zhdanov.ccfit.nsu.activity.controller;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import d.zhdanov.ccfit.nsu.activity.mapper.ContractMapper;
import d.zhdanov.ccfit.nsu.activity.persistence.entities.ProjectContractEntity;
import d.zhdanov.ccfit.nsu.activity.service.ProjectService;
import d.zhdanov.ccfit.nsu.objects.service.ConstructionSiteService;
import d.zhdanov.graphql.types.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@DgsComponent
public class ActivityDataFetcher {
  private final ConstructionSiteService constructionSiteService;
  private final ProjectService          projectService;
  private final ContractMapper          contractMapper;
  
  public ActivityDataFetcher(
    @Autowired ConstructionSiteService constructionSiteService,
    @Autowired ProjectService projectService,
    @Autowired ContractMapper contractMapper
  ) {
    this.constructionSiteService = constructionSiteService;
    this.projectService          = projectService;
    this.contractMapper          = contractMapper;
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
  
  @Transactional
  public <T> ProjectContract commonAddProjectContractAction(
    ProjectContractInput input,
    T project,
    TriFunction<UUID, ProjectContractInput, T, ProjectContractEntity> contractSaver
  ) {
    final var siteIdUUID = UUID.fromString(input.getSiteId());
    final var constructionProjectEntity =
      constructionSiteService.prepareNewProjectForSite(siteIdUUID);
    
    final var contract = contractSaver.apply(
      constructionProjectEntity.getId(),
      input,
      project
    );
    return contractMapper.toProjectContract(contract);
  }
  
  @FunctionalInterface
  public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);
  }
}
