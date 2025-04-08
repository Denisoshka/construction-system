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
  ProjectContractEntity toProjectContractEntity(ProjectContractInput input);
  
  ProjectContract toProjectContract(ProjectContractEntity entity);
  
  List<ProjectContract> toProjectContractList(
    List<ProjectContractEntity> entities
  );
  
  CustomerOrganization toCustomerOrganization(
    CustomerOrganisationEntity entity
  );
  
  List<CustomerOrganization> toCustomerOrganizationList(
    List<CustomerOrganisationEntity> entityList
  );
  
  @Mapping(target = "id", ignore = true)
  CustomerOrganisationEntity toCustomerOrganisationEntity(
    CustomerOrganizationInput input
  );
  
  @Mapping(target = "id", ignore = true)
  void updateCustomerOrganisation(
    @MappingTarget CustomerOrganisationEntity entity,
    CustomerOrganizationInput input
  );
  
  @Mapping(target = "projectId", ignore = true)
  SchoolEntity toSchoolEntity(SchoolProjectInput schoolInput);
  
  @Mapping(target = "projectId", ignore = true)
  BridgeEntity toBridgeEntity(BridgeProjectInput bridgeInput);
  
  @Mapping(target = "projectId", ignore = true)
  ApartmentHouseEntity toApartmentHouseEntity(
    ApartmentHouseProjectInput bridgeInput
  );
}
