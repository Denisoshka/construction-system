package d.zhdanov.ccfit.nsu.activity.service;

import d.zhdanov.ccfit.nsu.activity.exceptions.WorkScheduleUnitAbsent;
import d.zhdanov.ccfit.nsu.activity.mapper.WorkScheduleMapper;
import d.zhdanov.ccfit.nsu.activity.persistence.WorkScheduleRepository;
import d.zhdanov.ccfit.nsu.utils.Utils;
import d.zhdanov.graphql.types.Pagination;
import d.zhdanov.graphql.types.WorkScheduleUnit;
import d.zhdanov.graphql.types.WorkScheduleUnitInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class WorkScheduleService {
  private final WorkScheduleMapper workScheduleMapper;
  private final WorkScheduleRepository workScheduleRepository;
  
  public WorkScheduleService(
    @Autowired WorkScheduleRepository workScheduleRepository,
    @Autowired WorkScheduleMapper workScheduleMapper
  ) {
    this.workScheduleRepository = workScheduleRepository;
    this.workScheduleMapper     = workScheduleMapper;
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  public WorkScheduleUnit findWorkScheduleById(final UUID id) {
    final var ret = workScheduleRepository.findById(id)
      .orElseThrow(WorkScheduleUnitAbsent::new);
    return workScheduleMapper.toWorkScheduleUnit(ret);
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  public List<WorkScheduleUnit> findWorkScheduleByBrigade(
    final UUID brigadeId,
    final Pagination pagination
  ) {
    final var paged = Utils.getPageable(pagination);
    final var ret =
      workScheduleRepository.findAllByBrigadeId(
        brigadeId, paged.getOffset(), paged.getPageSize(),
        WorkScheduleRepository.defSort
      );
    return workScheduleMapper.toWorkScheduleUnitList(ret);
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  public List<WorkScheduleUnit> findWorkScheduleByProject(
    final UUID projectId,
    final Pagination pagination
  ) {
    final var paged = Utils.getPageable(pagination);
    final var ret =
      workScheduleRepository.findAllByContractId(
        projectId, paged.getOffset(), paged.getPageSize(),
        WorkScheduleRepository.defSort
      );
    return workScheduleMapper.toWorkScheduleUnitList(ret);
  }
  
  @PreAuthorize("hasAnyRole('ENGINEER, FOREMEN')")
  @Transactional
  public WorkScheduleUnit createWorkScheduleUnit(WorkScheduleUnitInput input) {
    final var entity = workScheduleMapper.toWorkScheduleEntity(input);
    workScheduleRepository.save(entity);
    return workScheduleMapper.toWorkScheduleUnit(entity);
  }
  
  @PreAuthorize("hasAnyRole('ENGINEER, FOREMEN')")
  @Transactional
  public WorkScheduleUnit updateWorkScheduleUnit(
    UUID id,
    WorkScheduleUnitInput input
  ) {
    final var entity = workScheduleMapper.toWorkScheduleEntity(input);
    entity.setId(id);
    workScheduleRepository.save(entity);
    return workScheduleMapper.toWorkScheduleUnit(entity);
  }
  
  @PreAuthorize("hasAnyRole('ENGINEER, FOREMEN')")
  @Transactional
  public Boolean deleteWorkSchedule(UUID uuid) {
    workScheduleRepository.deleteById(uuid);
    return true;
  }
  
  @PreAuthorize("hasAnyRole('ENGINEER, FOREMEN')")
  public Boolean deleteWorkSchedule(List<UUID> uuids) {
    workScheduleRepository.deleteAllById(uuids);
    return true;
  }
}
