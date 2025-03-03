package d.zhdanov.ccfit.nsu.controller.activity;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import d.zhdanov.ccfit.nsu.service.objects.ObjectsService;
import d.zhdanov.graphql.types.ProjectContractInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@DgsComponent
public class ActivityDataFetcher {
  private final ObjectsService objectsService;
  
  public ActivityDataFetcher(@Autowired ObjectsService objectsService) {
    this.objectsService = objectsService;
  }
  
  @DgsMutation
  @Transactional
  public void addSchoolProjectContract(@InputArgument ProjectContractInput input
  ) {
    UUID siteIdUUID = UUID.fromString(input.getSiteId());
    objectsService.prepareNewProjectForSite(siteIdUUID);
    
  }
  
}
