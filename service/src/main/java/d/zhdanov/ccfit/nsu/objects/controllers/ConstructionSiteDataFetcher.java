package d.zhdanov.ccfit.nsu.objects.controllers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import d.zhdanov.ccfit.nsu.objects.service.ConstructionSiteService;
import d.zhdanov.graphql.types.ConstructionSite;
import d.zhdanov.graphql.types.ConstructionSiteInput;
import d.zhdanov.graphql.types.Pagination;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@DgsComponent
public class ConstructionSiteDataFetcher {
  private final ConstructionSiteService constructionSiteService;
  
  public ConstructionSiteDataFetcher(ConstructionSiteService constructionSiteService) {
    this.constructionSiteService = constructionSiteService;
  }
  
  @DgsQuery
  public ConstructionSite constructionSite(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    return constructionSiteService.findConstructionSite(uuid);
  }
  
  @DgsQuery
  public List<ConstructionSite> constructionSites(
    @InputArgument Pagination pagination
  ) {
    return constructionSiteService.findAllConstructionSites(pagination);
  }
  
  @DgsMutation
  @Transactional
  public ConstructionSite createConstructionSite(
    @InputArgument ConstructionSiteInput input
  ) {
    return constructionSiteService.createConstructionSite(input);
  }
  
  @DgsMutation
  public ConstructionSite updateConstructionSite(
    @InputArgument String id,
    @InputArgument
    ConstructionSiteInput input
  ) {
    final var uuid = UUID.fromString(id);
    return constructionSiteService.updateConstructionSite(uuid, input);
  }
  
  @DgsMutation
  public Boolean deleteConstructionSite(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    return constructionSiteService.deleteConstructionSite(uuid);
  }
}
