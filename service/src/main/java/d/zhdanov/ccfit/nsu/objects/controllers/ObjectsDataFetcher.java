package d.zhdanov.ccfit.nsu.objects.controllers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import d.zhdanov.ccfit.nsu.objects.service.ObjectsService;
import d.zhdanov.ccfit.nsu.workers.service.EmployeeService;
import d.zhdanov.graphql.types.*;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;

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
  public ConstructionSite createConstructionSite(
    @InputArgument ConstructionSiteInput input
  ) {
    EmployeeInfo employee = fetchEmployeeInfo(input.getSiteManagerId());
    if(employee != null) {
      input.setSiteManagerId(employee.getId());
    }
    
    final var ret = objectsService.createConstructionSite(input);
    ret.setSiteManager(employee);
    return ret;
  }
  
  @DgsMutation
  public Boolean deleteConstructionSite(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    return objectsService.deleteConstructionSite(uuid);
  }
  
  @DgsMutation
  public ConstructionManagement createConstructionManagement(
    @InputArgument ConstructionManagementInput input
  ) {
    EmployeeInfo employee = fetchEmployeeInfo(input.getManagerId());
    if(employee != null) {
      input.setManagerId(employee.getId());
    }
    
    final var ret = objectsService.createConstructionManagement(input);
    ret.setManager(employee);
    return ret;
  }
  
  @Nullable
  private EmployeeInfo fetchEmployeeInfo(String id) {
    EmployeeInfo employee = null;
    if(id != null) {
      try {
        final var uuid = UUID.fromString(id);
        employee = employeeService.getEmployee(uuid);
      } catch(Exception e) {
        log.error("failed to get manager", e);
      }
    }
    
    return employee;
  }
  
  @DgsMutation
  public ConstructionManagement updateConstructionSite(
    @InputArgument String id,
    @InputArgument
    ConstructionSiteInput input
  ) {
    final var uuid = UUID.fromString(id);
    return objectsService.updateConstructionManagement(uuid, input);
  }
  
  @DgsMutation
  public Boolean deleteConstructionManagement(@InputArgument String id) {
    return true;
  }
}
