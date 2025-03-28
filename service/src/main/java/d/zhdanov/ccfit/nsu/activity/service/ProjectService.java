package d.zhdanov.ccfit.nsu.activity.service;

import d.zhdanov.ccfit.nsu.activity.persistence.ApartmentHouseRepository;
import d.zhdanov.ccfit.nsu.activity.persistence.BridgeRepository;
import d.zhdanov.ccfit.nsu.activity.persistence.ProjectContractRepository;
import d.zhdanov.ccfit.nsu.activity.persistence.SchoolRepository;
import d.zhdanov.ccfit.nsu.activity.persistence.entities.ApartmentHouseEntity;
import d.zhdanov.ccfit.nsu.activity.persistence.entities.BridgeEntity;
import d.zhdanov.ccfit.nsu.activity.persistence.entities.ProjectContractEntity;
import d.zhdanov.ccfit.nsu.activity.persistence.entities.SchoolEntity;
import d.zhdanov.graphql.types.ApartmentHouseProjectInput;
import d.zhdanov.graphql.types.BridgeProjectInput;
import d.zhdanov.graphql.types.ProjectContractInput;
import d.zhdanov.graphql.types.SchoolProjectInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProjectService {
  private final ProjectContractRepository projectContractRepository;
  private final ApartmentHouseRepository  apartmentHouseRepository;
  private final SchoolRepository          schoolRepository;
  private final BridgeRepository          bridgeRepository;
  
  public ProjectService(
    @Autowired ProjectContractRepository projectContractRepository,
    @Autowired ApartmentHouseRepository apartmentHouseRepository,
    @Autowired SchoolRepository schoolRepository,
    @Autowired BridgeRepository bridgeRepository
  ) {
    this.projectContractRepository = projectContractRepository;
    this.apartmentHouseRepository  = apartmentHouseRepository;
    this.schoolRepository          = schoolRepository;
    this.bridgeRepository          = bridgeRepository;
  }
  
  @Transactional
  public ProjectContractEntity saveSchoolProjectContract(
    final UUID projectId,
    final ProjectContractInput contractInput,
    final SchoolProjectInput schoolInput
  ) {
    final var schoolEntity = new SchoolEntity(
      projectId,
      schoolInput.getClassRoomCount(),
      schoolInput.getFloors()
    );
    
    return saveProjectContract(
      projectId,
      contractInput,
      schoolEntity,
      schoolRepository
    );
  }
  
  @Transactional
  public ProjectContractEntity saveBridgeProjectContract(
    final UUID projectId,
    final ProjectContractInput contractInput,
    final BridgeProjectInput bridgeInput
  ) {
    final var bridgeEntity = new BridgeEntity(
      projectId,
      bridgeInput.getSpan(),
      bridgeInput.getWidth(),
      bridgeInput.getTrafficLanesNumber()
    );
    
    return saveProjectContract(
      projectId,
      contractInput,
      bridgeEntity,
      bridgeRepository
    );
  }
  
  @Transactional
  public ProjectContractEntity saveApartmentHouseProjectContract(
    final UUID projectId,
    final ProjectContractInput contractInput,
    final ApartmentHouseProjectInput apartmentHouseInput
  ) {
    final var entity = new ApartmentHouseEntity(
      projectId,
      apartmentHouseInput.getFloors()
    );
    
    return saveProjectContract(
      projectId,
      contractInput,
      entity,
      apartmentHouseRepository
    );
  }
  
  public Boolean deleteSchoolProjectContract(UUID id) {
    schoolRepository.deleteById(id);
    return true;
  }
  
  public Boolean deleteBridgeProjectContract(UUID id) {
    bridgeRepository.deleteById(id);
    return true;
  }
  
  public Boolean deleteApartmentHouseProjectContract(UUID id) {
    apartmentHouseRepository.deleteById(id);
    return true;
  }
  
  //  @Transactional
  public <T, R extends CrudRepository<T, UUID>> ProjectContractEntity saveProjectContract(
    final UUID projectId,
    final ProjectContractInput projectContractInput,
    final T projectEntity,
    final R repository
  ) {
    final var projectContract = ProjectContractEntity.builder()
      .projectId(projectId)
      .customerId(UUID.fromString(projectContractInput.getCustomerId()))
      .siteId(UUID.fromString(projectContractInput.getSiteId()))
      .type(projectContractInput.getObjectType())
      .signingDate(projectContractInput.getSigningDate())
      .planStartDate(projectContractInput.getPlanStartDate())
      .planEndDate(projectContractInput.getPlanEndDate())
      .build();
    repository.save(projectEntity);
    return projectContractRepository.save(projectContract);
  }
}
