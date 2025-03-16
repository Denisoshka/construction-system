package d.zhdanov.ccfit.nsu.activity.service;

import d.zhdanov.ccfit.nsu.activity.exceptions.WorkScheduleUnitAbsent;
import d.zhdanov.ccfit.nsu.activity.mapper.WorkScheduleMapper;
import d.zhdanov.ccfit.nsu.activity.persistence.WorkScheduleRepository;
import d.zhdanov.ccfit.nsu.utils.Utils;
import d.zhdanov.graphql.types.Pagination;
import d.zhdanov.graphql.types.WorkScheduleUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WorkScheduleService {
  private final WorkScheduleMapper     workScheduleMapper;
  private final WorkScheduleRepository workScheduleRepository;
  
  public WorkScheduleService(
    @Autowired WorkScheduleRepository workScheduleRepository,
    @Autowired WorkScheduleMapper workScheduleMapper
  ) {
    this.workScheduleRepository = workScheduleRepository;
    this.workScheduleMapper     = workScheduleMapper;
  }
  
  public WorkScheduleUnit findWorkScheduleById(final UUID id) {
    final var ret = workScheduleRepository.findById(id).orElseThrow(
      WorkScheduleUnitAbsent::new);
    return workScheduleMapper.toWorkScheduleUnit(ret);
  }
  
  public List<WorkScheduleUnit> findWorkScheduleByBrigade(
    final UUID brigadeId,
    final Pagination pagination
  ) {
    final var paged = Utils.getPageable(
      pagination,
      WorkScheduleRepository.defSort
    );
    final var ret = workScheduleRepository.findAllByBrigadeId(brigadeId, paged);
    return workScheduleMapper.toWorkScheduleUnitList(ret);
  }
  
  public List<WorkScheduleUnit> findWorkScheduleByProject(
    final UUID projectId,
    final Pagination pagination
  ) {
    final var paged = Utils.getPageable(
      pagination,
      WorkScheduleRepository.defSort
    );
    final var ret = workScheduleRepository.findAllByContractId(
      projectId,
      paged
    );
    return workScheduleMapper.toWorkScheduleUnitList(ret);
  }
}
