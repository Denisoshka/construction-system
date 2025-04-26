package d.zhdanov.ccfit.nsu.activity.mapper;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.CustomerOrganisationEntity;
import d.zhdanov.ccfit.nsu.activity.persistence.entities.ObjectTypeEntity;
import d.zhdanov.ccfit.nsu.activity.persistence.entities.ProjectContractEntity;
import d.zhdanov.graphql.types.*;
import org.mapstruct.*;

import java.util.List;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING
)
public interface ContractMapper {
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "type", source = "objectType")
  ProjectContractEntity toProjectContractEntity(ProjectContractInput input);
  
  @Mapping(target = "objectType", source = "type")
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
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  void updateCustomerOrganisation(
    @MappingTarget CustomerOrganisationEntity entity,
    CustomerOrganizationInput input
  );
  
  ObjectType toObjectType(ObjectTypeEntity objectTypeEntity);
  
  List<ObjectType> toObjectTypeList(List<ObjectTypeEntity> objectTypeEntities);
  
  @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
  @Mapping(target = "id", ignore = true)
  void updateObjectTypeEntity(
    ProjectContractInput addition,
    @MappingTarget ProjectContractEntity entity
  );
}
