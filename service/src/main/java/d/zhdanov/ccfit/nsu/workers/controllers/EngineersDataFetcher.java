package d.zhdanov.ccfit.nsu.workers.controllers;

import com.netflix.graphql.dgs.*;
import d.zhdanov.ccfit.nsu.objects.dto.ConstructionSiteDTO;
import d.zhdanov.ccfit.nsu.workers.mapper.EngineersMapper;
import d.zhdanov.ccfit.nsu.workers.service.EngineersService;
import d.zhdanov.graphql.DgsConstants;
import d.zhdanov.graphql.types.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@DgsComponent
public class EngineersDataFetcher {
  private final EngineersService engineersService;
  
  public EngineersDataFetcher(
    @Autowired EngineersService engineersService,
    @Autowired EngineersMapper engineersMapper
  ) {
    this.engineersService = engineersService;
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  @DgsEntityFetcher(name = DgsConstants.ENGINEERPOSITION.TYPE_NAME)
  public EngineerPosition fetchEngineerPosition(
    @NotNull final Map<String, Object> values
  ) {
    final var id = (Integer) values.get(DgsConstants.ENGINEERPOSITION.Id);
    return engineersService.engineerPosition(id);
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  @DgsQuery
  public List<EngineerInfo> engineers(
    @InputArgument Pagination pagination,
    @InputArgument EngineerFilter engineerFilter
  ) {
    return engineersService.findAllEngineers(pagination, engineerFilter);
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  @DgsQuery
  public EngineerInfo engineer(@InputArgument String id) {
    return engineersService.findEngineer(UUID.fromString(id));
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  @DgsQuery
  public List<EngineerPosition> engineersPositions(
    @InputArgument Pagination pagination
  ) {
    return engineersService.engineersPositions(pagination);
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  @DgsQuery
  public EngineerPosition engineerPositionByName(@InputArgument String name) {
    return engineersService.engineerPosition(name);
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  @DgsQuery
  public EngineerPosition engineerPosition(@InputArgument Integer id) {
    return engineersService.engineerPosition(id);
  }
  
  @PreAuthorize("hasRole('SITE_MANAGER')")
  @DgsMutation
  public EngineerPosition createEngineerPosition(final @InputArgument EngineerPositionInput input) {
    return engineersService.createEngineerPosition(input);
  }
  
  @PreAuthorize("hasRole('SITE_MANAGER')")
  @DgsMutation
  public EngineerPosition updateEngineerPosition(
    @InputArgument Integer id,
    @InputArgument
    EngineerPositionInput input
  ) {
    return engineersService.updateEngineerPosition(id, input);
  }
  
  @DgsMutation
  public void deleteEngineerPosition(@InputArgument Integer id) {
    engineersService.deleteEngineerPosition(id);
  }
  
  @DgsData(
    parentType = DgsConstants.CONSTRUCTIONSITE.TYPE_NAME, field = DgsConstants.CONSTRUCTIONSITE.SiteManager
  )
  public EngineerInfo getConstructionSiteEmployeeInfo(
    DgsDataFetchingEnvironment dfe
  ) {
    ConstructionSiteDTO site = dfe.getSource();
    return engineer(site.getSiteManagerId().toString());
  }
}
