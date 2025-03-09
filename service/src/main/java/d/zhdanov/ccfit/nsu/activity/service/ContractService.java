package d.zhdanov.ccfit.nsu.activity.service;

import d.zhdanov.ccfit.nsu.activity.exceptions.CustomerOrganizationAbsent;
import d.zhdanov.ccfit.nsu.activity.exceptions.CustomerOrganizationCreationException;
import d.zhdanov.ccfit.nsu.activity.mapper.ContractMapper;
import d.zhdanov.ccfit.nsu.activity.persistence.CustomerOrganizationRepository;
import d.zhdanov.ccfit.nsu.util.Utils;
import d.zhdanov.graphql.types.CustomerOrganization;
import d.zhdanov.graphql.types.CustomerOrganizationInput;
import d.zhdanov.graphql.types.Pagination;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class ContractService {
  private final CustomerOrganizationRepository customerOrganizationRepository;
  private final ContractMapper                 contractMapper;
  
  public ContractService(
    @Autowired CustomerOrganizationRepository customerOrganizationRepository,
    @Autowired ContractMapper contractMapper
  ) {
    this.customerOrganizationRepository = customerOrganizationRepository;
    this.contractMapper                 = contractMapper;
  }
  
  public CustomerOrganization getCustomerOrganization(final UUID id) {
    final var ret = customerOrganizationRepository.findById(id).orElseThrow(
      CustomerOrganizationAbsent::new);
    return contractMapper.toCustomerOrganization(ret);
  }
  
  public List<CustomerOrganization> getAllCustomerOrganizations(Pagination pagination) {
    final var paged = Utils.getPageable(pagination);
    final var ret   = customerOrganizationRepository.findAll(paged).toList();
    return contractMapper.toCustomerOrganizationList(ret);
  }
  
  @Transactional
  public CustomerOrganization createCustomerOrganization(final CustomerOrganizationInput input) {
    try {
      final var entity = contractMapper.toCustomerOrganisationEntity(input);
      final var ret    = customerOrganizationRepository.save(entity);
      return contractMapper.toCustomerOrganization(ret);
    } catch(DataIntegrityViolationException e) {
      log.error("Create customer organization failed", e);
      throw new CustomerOrganizationCreationException();
    }
  }
  
  @Transactional
  public void deleteCustomerOrganization(final UUID id) {
    customerOrganizationRepository.deleteById(id);
  }
}
