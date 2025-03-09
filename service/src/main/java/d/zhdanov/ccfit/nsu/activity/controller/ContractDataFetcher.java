package d.zhdanov.ccfit.nsu.activity.controller;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import d.zhdanov.graphql.types.CustomerOrganization;

@DgsComponent
public class ContractDataFetcher {
  @DgsQuery
  public CustomerOrganization customerOrganization(@InputArgument String id){
  
  }
}
