package d.zhdanov.ccfit.nsu.objects.controllers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import d.zhdanov.ccfit.nsu.objects.dto.BrigadeDTO;
import d.zhdanov.ccfit.nsu.objects.service.BrigadeService;
import d.zhdanov.ccfit.nsu.workers.service.WorkersService;
import d.zhdanov.graphql.types.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

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
}
