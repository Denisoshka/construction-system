package d.zhdanov.ccfit.nsu.workers.service;

import d.zhdanov.ccfit.nsu.utils.Utils;
import d.zhdanov.ccfit.nsu.workers.exceptions.EmployeeNotFoundException;
import d.zhdanov.ccfit.nsu.workers.exceptions.WorkerPositionAlreadyExistsException;
import d.zhdanov.ccfit.nsu.workers.exceptions.WorkerPositionNotFoundException;
import d.zhdanov.ccfit.nsu.workers.mapper.WorkersMapper;
import d.zhdanov.ccfit.nsu.workers.persistence.WorkersPositionRepository;
import d.zhdanov.ccfit.nsu.workers.persistence.WorkersRepository;
import d.zhdanov.ccfit.nsu.workers.persistence.entities.WorkerEntity;
import d.zhdanov.ccfit.nsu.workers.persistence.entities.WorkerPositionEntity;
import d.zhdanov.graphql.types.*;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class WorkersService {
  private final WorkersPositionRepository workersPositionRepository;
  private final WorkersRepository         workersRepository;
  private final WorkersMapper             workersMapper;
  
  public WorkersService(
    @Autowired WorkersPositionRepository workersPositionRepository,
    @Autowired WorkersRepository workersRepository,
    @Autowired WorkersMapper workersMapper
  ) {
    this.workersPositionRepository = workersPositionRepository;
    this.workersRepository         = workersRepository;
    this.workersMapper             = workersMapper;
  }
  
  @PreAuthorize("hasRole('SITE_MANAGER')")
  @Transactional
  public @NotNull WorkerPosition createWorkerPosition(
    final @NotNull WorkerPositionInput workerPosition
  ) throws WorkerPositionAlreadyExistsException {
    try {
      final var ret = workersPositionRepository.save(
        new WorkerPositionEntity(null, workerPosition.getName()));
      return workersMapper.fromWorkerPositionEntity(ret);
    } catch(DataIntegrityViolationException _) {
      throw new WorkerPositionAlreadyExistsException();
    }
  }
  
  @PreAuthorize("hasRole('SITE_MANAGER')")
  @Transactional
  public WorkerPosition updateWorkerPosition(
    final Integer id,
    final @NotNull WorkerPositionInput input
  ) {
    final var saved = workersPositionRepository.findById(id)
      .orElseThrow(WorkerPositionNotFoundException::new);
    workersMapper.updateWorkerPositionEntity(saved, input);
    final var ret = workersPositionRepository.save(saved);
    return workersMapper.fromWorkerPositionEntity(ret);
  }
  
  @PreAuthorize("hasRole('SITE_MANAGER')")
  @Transactional
  public void deleteWorkerPosition(final int id) {
    workersPositionRepository.deleteById(id);
  }
  
  public WorkerPosition workerPosition(final Integer id) {
    final var ret = workersPositionRepository.findById(id)
      .orElseThrow(WorkerPositionNotFoundException::new);
    return workersMapper.fromWorkerPositionEntity(ret);
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  public WorkerPosition workerPosition(final String name) {
    final var ret = workersPositionRepository.findByName(name)
      .orElseThrow(WorkerPositionNotFoundException::new);
    return workersMapper.fromWorkerPositionEntity(ret);
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  public WorkerEntity workerEmployeePosition(final UUID id) {
    return workersRepository.findById(id).orElseThrow();
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  public List<WorkerInfo> findAllWorkers(
    Pagination pagination,
    WorkerFilter workerFilter
  ) {
    final var paged  = Utils.getPageable(pagination);
    final var filter = Utils.getRepositoryWorkerFilter(workerFilter);
    final var ret = workersRepository.findAllWorkersWithInfo(
      paged.getOffset(),
      paged.getPageSize()
    );
    return workersMapper.toWorkersInfo(ret);
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  public WorkerInfo findWorker(UUID uuid) {
    final var ret = workersRepository.findWorkerWithInfo(uuid)
      .orElseThrow(EmployeeNotFoundException::new);
    return workersMapper.fromWorkerEntityWithAdditionalData(ret);
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  public List<WorkerPosition> workersPositions(Pagination pagination) {
    final var paged = Utils.getPageable(pagination);
    final var ret   = workersPositionRepository.findAll(paged).toList();
    return workersMapper.fromWorkerPositionEntityList(ret);
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  public List<WorkerInfo> findAllWorkersInBrigade(
    final UUID brigadeId,
    final Pagination pagination
  ) {
    final var paged = Utils.getPageable(pagination);
    final var ret = workersRepository.findWorkersWithInfoByBrigadeId(
      brigadeId,
      paged.getOffset(),
      paged.getPageSize()
    );
    return workersMapper.toWorkersInfo(ret);
  }

  @PreAuthorize("hasRole('EMPLOYEE')")
  public List<WorkerInfo> findAllWorkersByConstructionSite(
    UUID siteId, Pagination pagination) {
    final var paged = Utils.getPageable(pagination);
    final var ret =
      workersRepository.findAllWorkersBySiteWithPositionEntity(
        siteId,
        paged.getOffset(),
        paged.getPageSize()
      );
    return workersMapper.fromWorkerEntityList(ret);
  }
}
