package d.zhdanov.ccfit.nsu.objects.controllers;

import com.netflix.graphql.dgs.*;
import d.zhdanov.ccfit.nsu.objects.dto.BrigadeDTO;
import d.zhdanov.ccfit.nsu.objects.dto.ConstructionSiteDTO;
import d.zhdanov.ccfit.nsu.objects.service.ConstructionSiteService;
import d.zhdanov.graphql.DgsConstants;
import d.zhdanov.graphql.types.Pagination;

import java.util.List;
import java.util.UUID;

@DgsComponent
public class ConstructionSiteDataFetcher {
  private final ConstructionSiteService constructionSiteService;
  
  public ConstructionSiteDataFetcher(
    ConstructionSiteService constructionSiteService
  ) {
    this.constructionSiteService = constructionSiteService;
  }
  
  @DgsQuery
  public ConstructionSiteDTO constructionSite(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    return constructionSiteService.findConstructionSite(uuid);
  }
  
  @DgsQuery
  public List<ConstructionSiteDTO> constructionSites(
    @InputArgument Pagination pagination
  ) {
    return constructionSiteService.findConstructionSites(pagination);
  }
  
  @DgsQuery
  public ConstructionSiteDTO constructionSiteBySiteManager(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    return constructionSiteService.findConstructionSiteBySiteManager(uuid);
  }
  
  @DgsQuery
  public ConstructionSiteDTO constructionsSiteByEngineer(@InputArgument String id) {
    final var uuid = UUID.fromString(id);
    return constructionSiteService.findConstructionSiteByEngineer(
      uuid
    );
  }
  
  @DgsData(
    parentType = DgsConstants.BRIGADE.TYPE_NAME, field = DgsConstants.BRIGADE.SiteInfo
  )
  public ConstructionSiteDTO BrigadeSiteInfo(
    DgsDataFetchingEnvironment dfe
  ) {
    final BrigadeDTO brigadeDTO = dfe.getSource();
    return constructionSite(brigadeDTO.getSiteId().toString());
  }
}
