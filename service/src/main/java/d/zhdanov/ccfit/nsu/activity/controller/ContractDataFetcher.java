package d.zhdanov.ccfit.nsu.activity.controller;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import d.zhdanov.ccfit.nsu.activity.service.ContractService;
import d.zhdanov.graphql.types.CustomerOrganization;
import d.zhdanov.graphql.types.CustomerOrganizationInput;
import d.zhdanov.graphql.types.Pagination;

import java.util.List;
import java.util.UUID;

@DgsComponent
public class ContractDataFetcher {
  ContractService contractService;
  
  @DgsQuery
  public CustomerOrganization customerOrganization(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    return contractService.findCustomerOrganization(uuid);
  }
  
  @DgsQuery
  public List<CustomerOrganization> customerOrganizations(
    @InputArgument Pagination pagination
  ) {
    return contractService.findAllCustomerOrganizations(pagination);
  }
  
  @DgsMutation
  public CustomerOrganization createCustomerOrganization(
    @InputArgument CustomerOrganizationInput input
  ) {
    return contractService.createCustomerOrganization(input);
  }
  
  @DgsMutation
  public CustomerOrganization updateCustomerOrganization(
    @InputArgument String id, @InputArgument CustomerOrganizationInput input) {
    final var uuid = UUID.fromString(id);
    return contractService.updateCustomerOrganization(uuid, input);
  }
  
  @DgsMutation
  public Boolean deleteCustomerOrganization(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    contractService.deleteCustomerOrganization(uuid);
    return true;
  }
}
