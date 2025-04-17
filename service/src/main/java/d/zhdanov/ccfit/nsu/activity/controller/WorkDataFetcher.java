package d.zhdanov.ccfit.nsu.activity.controller;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import d.zhdanov.ccfit.nsu.activity.service.WorkScheduleService;
import d.zhdanov.graphql.types.Pagination;
import d.zhdanov.graphql.types.WorkScheduleUnit;
import d.zhdanov.graphql.types.WorkScheduleUnitInput;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@DgsComponent
public class WorkDataFetcher {
  final private WorkScheduleService workScheduleService;
  
  public WorkDataFetcher(
    @Autowired WorkScheduleService workScheduleService
  ) {
    this.workScheduleService = workScheduleService;
  }
  
  @DgsQuery
  public List<WorkScheduleUnit> projectWorkSchedule(
    @InputArgument String projectId,
    @InputArgument Pagination pagination
  ) {
    final var uuid = UUID.fromString(projectId);
    return workScheduleService.findWorkScheduleByProject(uuid, pagination);
  }
  
  @DgsQuery
  public List<WorkScheduleUnit> brigadeWorkSchedule(
    @InputArgument String brigadeId, @InputArgument Pagination pagination) {
    final var uuid = UUID.fromString(brigadeId);
    return workScheduleService.findWorkScheduleByBrigade(uuid, pagination);
  }
  
  @DgsQuery
  public WorkScheduleUnit workScheduleUnit(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    return workScheduleService.findWorkScheduleById(uuid);
  }
  
  @DgsMutation
  public WorkScheduleUnit addWorkScheduleUnit(
    @InputArgument WorkScheduleUnitInput input
  ) {
    return workScheduleService.createWorkScheduleUnit(input);
  }
  
  @DgsMutation
  public WorkScheduleUnit updateWorkScheduleUnit(
    @InputArgument String id, @InputArgument WorkScheduleUnitInput input) {
    final var uuid = UUID.fromString(id);
    return workScheduleService.updateWorkScheduleUnit(uuid, input);
  }
  
  @DgsMutation
  public Boolean deleteWorkScheduleUnit(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    return workScheduleService.deleteWorkSchedule(uuid);
  }
  
  @DgsMutation
  public Boolean deleteWorkScheduleUnits(@InputArgument List<String> units){
    final var uuids = units.stream().map(UUID::fromString).toList();
    return workScheduleService.deleteWorkSchedule(uuids);
  }
}
