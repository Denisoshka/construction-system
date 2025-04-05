package d.zhdanov.ccfit.nsu.activity.service;

import d.zhdanov.ccfit.nsu.activity.mapper.ContractMapper;
import d.zhdanov.ccfit.nsu.activity.persistence.ApartmentHouseRepository;
import d.zhdanov.ccfit.nsu.activity.persistence.BridgeRepository;
import d.zhdanov.ccfit.nsu.activity.persistence.ProjectContractRepository;
import d.zhdanov.ccfit.nsu.activity.persistence.SchoolRepository;
import d.zhdanov.graphql.types.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProjectService {
  private final ProjectContractRepository projectContractRepository;
  private final ApartmentHouseRepository  apartmentHouseRepository;
  private final SchoolRepository          schoolRepository;
  private final BridgeRepository          bridgeRepository;
  private final ContractMapper            contractMapper;
  
  public ProjectService(
    @Autowired ProjectContractRepository projectContractRepository,
    @Autowired ApartmentHouseRepository apartmentHouseRepository,
    @Autowired SchoolRepository schoolRepository,
    @Autowired BridgeRepository bridgeRepository,
    @Autowired ContractMapper contractMapper
  ) {
    this.projectContractRepository = projectContractRepository;
    this.apartmentHouseRepository  = apartmentHouseRepository;
    this.schoolRepository          = schoolRepository;
    this.bridgeRepository          = bridgeRepository;
    this.contractMapper            = contractMapper;
  }
  
  @PreAuthorize("hasRole('SITE_MANAGER')")
  @Transactional
  public ProjectContract saveSchoolProjectContract(
    final ProjectContractInput contractInput,
    final SchoolProjectInput schoolInput
  ) {
    final var school   = contractMapper.toSchoolEntity(schoolInput);
    final var contract = contractMapper.toProjectContractEntity(contractInput);
    final var ret      = projectContractRepository.save(contract);
    school.setProjectId(ret.getProjectId());
    schoolRepository.save(school);
    return contractMapper.toProjectContract(ret);
  }
  
  @PreAuthorize("hasRole('SITE_MANAGER')")
  @Transactional
  public ProjectContract saveBridgeProjectContract(
    final ProjectContractInput contractInput,
    final BridgeProjectInput bridgeInput
  ) {
    final var bridge   = contractMapper.toBridgeEntity(bridgeInput);
    final var contract = contractMapper.toProjectContractEntity(contractInput);
    final var ret      = projectContractRepository.save(contract);
    bridge.setProjectId(ret.getProjectId());
    bridgeRepository.save(bridge);
    return contractMapper.toProjectContract(ret);
  }
  
  @PreAuthorize("hasRole('SITE_MANAGER')")
  @Transactional
  public ProjectContract saveApartmentHouseProjectContract(
    final ProjectContractInput contractInput,
    final ApartmentHouseProjectInput apartmentHouseInput
  ) {
    final var house =
      contractMapper.toApartmentHouseEntity(apartmentHouseInput);
    final var contract = contractMapper.toProjectContractEntity(contractInput);
    final var ret      = projectContractRepository.save(contract);
    house.setProjectId(ret.getProjectId());
    apartmentHouseRepository.save(house);
    return contractMapper.toProjectContract(ret);
  }
  
  @PreAuthorize("hasRole('SITE_MANAGER')")
  public Boolean deleteSchoolProjectContract(UUID id) {
    schoolRepository.deleteById(id);
    return true;
  }
  
  @PreAuthorize("hasRole('SITE_MANAGER')")
  public Boolean deleteBridgeProjectContract(UUID id) {
    bridgeRepository.deleteById(id);
    return true;
  }
  
  @PreAuthorize("hasRole('SITE_MANAGER')")
  public Boolean deleteApartmentHouseProjectContract(UUID id) {
    apartmentHouseRepository.deleteById(id);
    return true;
  }
}
