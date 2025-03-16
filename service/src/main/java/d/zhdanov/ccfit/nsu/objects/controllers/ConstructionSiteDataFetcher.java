package d.zhdanov.ccfit.nsu.objects.controllers;

import com.netflix.graphql.dgs.*;
import d.zhdanov.ccfit.nsu.objects.dto.BrigadeDTO;
import d.zhdanov.ccfit.nsu.objects.service.ConstructionSiteService;
import d.zhdanov.graphql.DgsConstants;
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
    throw new UnsupportedOperationException();
//    return constructionSiteService.createConstructionSite(input);
  }
  
  @DgsMutation
  public ConstructionSite updateConstructionSite(
    @InputArgument String id,
    @InputArgument
    ConstructionSiteInput input
  ) {
    throw new UnsupportedOperationException();
//    final var uuid = UUID.fromString(id);
//    return constructionSiteService.updateConstructionSite(uuid, input);
  }
  
  @DgsMutation
  public Boolean deleteConstructionSite(@InputArgument String id) {
    throw new UnsupportedOperationException();
//    final var uuid = UUID.fromString(id);
//    return constructionSiteService.deleteConstructionSite(uuid);
  }
  
  @DgsData(
    parentType = DgsConstants.BRIGADE.TYPE_NAME, field = DgsConstants.BRIGADE.SiteInfo
  )
  public BrigadeDTO BrigadeSiteInfo(
    DgsDataFetchingEnvironment dfe
  ) {
    final BrigadeDTO brigadeDTO = dfe.getSource();
    
    final var siteInfo = constructionSite(brigadeDTO.getSiteId().toString());
    brigadeDTO.setSiteInfo(siteInfo);
    
    return brigadeDTO;
  }
}
