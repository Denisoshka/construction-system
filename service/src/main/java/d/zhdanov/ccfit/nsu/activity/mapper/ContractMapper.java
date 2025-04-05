package d.zhdanov.ccfit.nsu.activity.mapper;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.*;
import d.zhdanov.graphql.types.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING
)
public interface ContractMapper {
  @Mapping(target = "id", ignore = true)
  ProjectContractEntity toProjectContractEntity(
    final ProjectContractInput input
  );
  
  ProjectContract toProjectContract(final ProjectContractEntity entity);
  
  CustomerOrganization toCustomerOrganization(
    final CustomerOrganisationEntity entity
  );
  
  List<CustomerOrganization> toCustomerOrganizationList(
    final List<CustomerOrganisationEntity> entityList
  );
  
  @Mapping(target = "id", ignore = true)
  CustomerOrganisationEntity toCustomerOrganisationEntity(
    final CustomerOrganizationInput input
  );
  
  @Mapping(target = "id", ignore = true)
  void updateCustomerOrganisation(
    @MappingTarget final CustomerOrganisationEntity entity,
    final CustomerOrganizationInput input
  );
  
  @Mapping(target = "projectId", ignore = true)
  SchoolEntity toSchoolEntity(SchoolProjectInput schoolInput);
  
  @Mapping(target = "projectId", ignore = true)
  BridgeEntity toBridgeEntity(BridgeProjectInput bridgeInput);
  
  @Mapping(target = "projectId", ignore = true)
  ApartmentHouseEntity toApartmentHouseEntity(ApartmentHouseProjectInput bridgeInput);
}
