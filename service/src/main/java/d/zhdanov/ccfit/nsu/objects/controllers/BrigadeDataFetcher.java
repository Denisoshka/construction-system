package d.zhdanov.ccfit.nsu.objects.controllers;

import com.netflix.graphql.dgs.*;
import d.zhdanov.ccfit.nsu.objects.dto.BrigadeDTO;
import d.zhdanov.ccfit.nsu.objects.service.BrigadeService;
import d.zhdanov.graphql.DgsConstants;
import d.zhdanov.graphql.types.Pagination;
import d.zhdanov.graphql.types.WorkScheduleUnit;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

@DgsComponent
public class BrigadeDataFetcher {
  private final BrigadeService brigadeService;
  
  public BrigadeDataFetcher(
    @Autowired BrigadeService brigadeService
  ) {
    this.brigadeService = brigadeService;
  }
  
  @DgsQuery
  public List<BrigadeDTO> brigades(@InputArgument Pagination pagination) {
    return brigadeService.findAllBrigades(pagination);
  }
  
  @DgsQuery
  public BrigadeDTO brigade(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    return brigadeService.findBrigade(uuid);
  }
  
  @DgsData(
    parentType = DgsConstants.WORKSCHEDULEUNIT.TYPE_NAME, field =
    DgsConstants.WORKSCHEDULEUNIT.Brigade
  )
  public BrigadeDTO workScheduleUnitBrigadeFetcher(
    DgsDataFetchingEnvironment dfe
  ){
    final WorkScheduleUnit workScheduleUnit = dfe.getSource();
    return brigade(workScheduleUnit.getBrigadeId());
  }
  
  @DgsQuery
  public List<BrigadeDTO> brigadesByConstructionSite(
    @InputArgument String id, @InputArgument Pagination pagination
  ) {
    final var uuid = UUID.fromString(id);
    return brigadeService.findAllBrigadesBySite(uuid, pagination);
  }
  
  @DgsQuery
  public BrigadeDTO brigadeByWorker(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    return brigadeService.findAllBrigadesByWorker(uuid);
  }
}
