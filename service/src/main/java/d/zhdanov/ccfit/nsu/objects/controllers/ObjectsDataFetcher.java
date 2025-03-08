package d.zhdanov.ccfit.nsu.objects.controllers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import d.zhdanov.ccfit.nsu.objects.service.ObjectsService;
import d.zhdanov.ccfit.nsu.workers.service.EmployeeService;
import d.zhdanov.graphql.types.ConstructionManagement;
import d.zhdanov.graphql.types.ConstructionManagementInput;
import d.zhdanov.graphql.types.ConstructionSite;
import d.zhdanov.graphql.types.ConstructionSiteInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@DgsComponent
public class ObjectsDataFetcher {
  private final ObjectsService  objectsService;
  private final EmployeeService employeeService;
  
  public ObjectsDataFetcher(
    @Autowired ObjectsService objectsService,
    @Autowired EmployeeService employeeService
  ) {
    this.objectsService  = objectsService;
    this.employeeService = employeeService;
  }
  
  @DgsMutation
  @Transactional
  public ConstructionSite createConstructionSite(
    @InputArgument ConstructionSiteInput input
  ) {
    final var uuid        = UUID.fromString(input.getSiteManagerId());
    final var siteManager = employeeService.getEmployee(uuid);
    
    final var ret = objectsService.createConstructionSite(input);
    
    ret.setSiteManager(siteManager);
    return ret;
  }
  
  @DgsMutation
  public ConstructionManagement updateConstructionSite(
    @InputArgument String id,
    @InputArgument
    ConstructionSiteInput input
  ) {
    final var siteUUID            = UUID.fromString(id);
    final var employeeUUID        = UUID.fromString(input.getSiteManagerId());
    final var constructionManager = employeeService.getEmployee(employeeUUID);
    
    final var ret = objectsService.updateConstructionManagement(
      siteUUID,
      input
    );
    ret.setManager(constructionManager);
    
    return ret;
  }
  
  @DgsMutation
  public Boolean deleteConstructionSite(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    return objectsService.deleteConstructionSite(uuid);
  }
  
  @DgsMutation
  @Transactional
  public ConstructionManagement createConstructionManagement(
    @InputArgument ConstructionManagementInput input
  ) {
    final var employeeUUID        = UUID.fromString(input.getManagerId());
    final var constructionManager = employeeService.getEmployee(employeeUUID);
    
    final var ret = objectsService.createConstructionManagement(input);
    
    ret.setManager(constructionManager);
    return ret;
  }
  
  @DgsMutation
  @Transactional
  public ConstructionManagement updateConstructionManagement(
    @InputArgument String id,
    @InputArgument ConstructionManagementInput input
  ) {
    final var managementUUID      = UUID.fromString(id);
    final var constructionManager = employeeService.getEmployee(managementUUID);
    final var ret = objectsService.updateConstructionManagement(
      managementUUID,
      input
    );
    ret.setManager(constructionManager);
    return ret;
  }
  
  @DgsMutation
  public Boolean deleteConstructionManagement(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    objectsService.deleteConstructionManagement(uuid);
    return true;
  }
}
