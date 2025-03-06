package d.zhdanov.ccfit.nsu.objects.controllers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import d.zhdanov.graphql.types.*;

@DgsComponent
public class ObjectsDataFetcher {
  
  @DgsMutation
  public ConstructionSite createConstructionSite(
    @InputArgument ConstructionSiteInput input
  ) {
  
  }
  
  @DgsMutation
  public Boolean deleteConstructionSite(@InputArgument String id) {
    return true;
  }
  
  @DgsMutation
  public ConstructionManagement createConstructionManagement(
    @InputArgument ConstructionManagementInput input
  ) {
  
  }
  
  @DgsMutation
  public Boolean deleteConstructionManagement(@InputArgument String id) {
    return true;
  }
}
