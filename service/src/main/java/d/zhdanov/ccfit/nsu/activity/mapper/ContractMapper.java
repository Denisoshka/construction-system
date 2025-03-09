package d.zhdanov.ccfit.nsu.activity.mapper;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.CustomerOrganisationEntity;
import d.zhdanov.ccfit.nsu.activity.persistence.entities.ProjectContractEntity;
import d.zhdanov.graphql.types.CustomerOrganization;
import d.zhdanov.graphql.types.CustomerOrganizationInput;
import d.zhdanov.graphql.types.ProjectContract;
import d.zhdanov.graphql.types.ProjectContractInput;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING
)
public interface ContractMapper {
  ProjectContractEntity toProjectContractDTO(final ProjectContractInput input);
  
  ProjectContract toProjectContract(final ProjectContractEntity entity);
  
  
  CustomerOrganization toCustomerOrganization(final CustomerOrganisationEntity entity);
  
  List<CustomerOrganization> toCustomerOrganizationList(final List<CustomerOrganisationEntity> entityList);
  
  CustomerOrganisationEntity toCustomerOrganisationEntity(final CustomerOrganizationInput input);
  
  CustomerOrganisationEntity toCustomerOrganisation(final CustomerOrganization entity);
  
  
}
