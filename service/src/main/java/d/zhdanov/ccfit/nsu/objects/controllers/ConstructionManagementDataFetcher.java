package d.zhdanov.ccfit.nsu.objects.controllers;

import com.netflix.graphql.dgs.*;
import d.zhdanov.ccfit.nsu.objects.service.ConstructionManagementService;
import d.zhdanov.graphql.DgsConstants;
import d.zhdanov.graphql.types.ConstructionManagement;
import d.zhdanov.graphql.types.ConstructionManagementInput;
import d.zhdanov.graphql.types.ConstructionSite;
import d.zhdanov.graphql.types.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@DgsComponent
public class ConstructionManagementDataFetcher {
  private final ConstructionManagementService constructionManagementService;
  
  public ConstructionManagementDataFetcher(
    @Autowired ConstructionManagementService constructionManagementService
  ) {
    this.constructionManagementService = constructionManagementService;
  }
  
  @DgsQuery
  ConstructionManagement constructionManagement(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    return constructionManagementService.findConstructionManagement(uuid);
  }
  
  @DgsQuery
  List<ConstructionManagement> constructionManagements(
    @InputArgument Pagination pagination
  ) {
    return constructionManagementService.findAllConstructionManagement(
      pagination);
  }
  
  @DgsMutation
  @Transactional
  public ConstructionManagement createConstructionManagement(
    @InputArgument ConstructionManagementInput input
  ) {
    return constructionManagementService.createConstructionManagement(input);
  }
  
  @DgsMutation
  @Transactional
  public ConstructionManagement updateConstructionManagement(
    @InputArgument String id,
    @InputArgument ConstructionManagementInput input
  ) {
    final var managementUUID = UUID.fromString(id);
    return constructionManagementService.updateConstructionManagement(
      managementUUID,
      input
    );
  }
  
  @DgsMutation
  public Boolean deleteConstructionManagement(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    constructionManagementService.deleteConstructionManagement(uuid);
    return true;
  }
  
  @DgsData(
    parentType = DgsConstants.CONSTRUCTIONSITE.TYPE_NAME, field = DgsConstants.CONSTRUCTIONSITE.Management
  )
  public ConstructionManagement getConstructionManagement(
    DgsDataFetchingEnvironment dfe
  ) {
    ConstructionSite site = dfe.getSource();
    return constructionManagement(site.getManagement_id());
  }
}
