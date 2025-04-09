package d.zhdanov.ccfit.nsu.activity.service;

import d.zhdanov.ccfit.nsu.activity.exceptions.ProjectContractAbsent;
import d.zhdanov.ccfit.nsu.activity.mapper.ContractMapper;
import d.zhdanov.ccfit.nsu.activity.persistence.ProjectContractRepository;
import d.zhdanov.ccfit.nsu.utils.Utils;
import d.zhdanov.graphql.types.Pagination;
import d.zhdanov.graphql.types.ProjectContract;
import d.zhdanov.graphql.types.ProjectContractInput;
import d.zhdanov.graphql.types.ProjectContractInputAddition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ProjectService {
  private final ProjectContractRepository projectContractRepository;
  private final ContractMapper            contractMapper;
  
  public ProjectService(
    @Autowired ProjectContractRepository projectContractRepository,
    @Autowired ContractMapper contractMapper
  ) {
    this.projectContractRepository = projectContractRepository;
    this.contractMapper            = contractMapper;
  }
  
  @PreAuthorize("hasRole('SITE_MANAGER')")
  @Transactional
  public ProjectContract saveProjectContract(final ProjectContractInput input) {
    final var contract = contractMapper.toProjectContractEntity(input);
    final var ret      = projectContractRepository.save(contract);
    return contractMapper.toProjectContract(ret);
  }
  
  @PreAuthorize("hasRole('SITE_MANAGER')")
  public ProjectContract findProjectContract(final UUID id) {
    final var contract = projectContractRepository.findById(id)
      .orElseThrow(ProjectContractAbsent::new);
    return contractMapper.toProjectContract(contract);
  }
  
  @PreAuthorize("hasRole('SITE_MANAGER')")
  public List<ProjectContract> contractsByConstructionSite(
    final UUID id,
    final Pagination pagination
  ) {
    final var paged = Utils.getPageable(pagination);
    final var contracts =
      projectContractRepository.findBySiteId(id, paged).toList();
    return contractMapper.toProjectContractList(contracts);
  }
  
  @PreAuthorize("hasRole('SITE_MANAGER')")
  @Transactional
  public Boolean deleteProjectContract(UUID id) {
    projectContractRepository.deleteById(id);
    return true;
  }
  
  @PreAuthorize("hasRole('SITE_MANAGER')")
  @Transactional
  public ProjectContract updateProjectContract(
    UUID id,
    ProjectContractInputAddition update
  ) {
    final var saved = projectContractRepository.findById(id)
      .orElseThrow(ProjectContractAbsent::new);
    contractMapper.updateObjectTypeEntity(update, saved);
    final var ret = projectContractRepository.save(saved);
    return contractMapper.toProjectContract(ret);
  }
}
