package d.zhdanov.ccfit.nsu.objects.mappers;

import d.zhdanov.ccfit.nsu.objects.persistence.entities.ConstructionManagementEntity;
import d.zhdanov.ccfit.nsu.objects.persistence.entities.ConstructionSiteEntity;
import d.zhdanov.graphql.types.ConstructionManagement;
import d.zhdanov.graphql.types.ConstructionManagementInput;
import d.zhdanov.graphql.types.ConstructionSite;
import d.zhdanov.graphql.types.ConstructionSiteInput;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING
)
public interface ConstructionObjectsMapper {
  ConstructionSite fromConstructionSiteEntity(ConstructionSiteEntity constructionSiteEntity);
  
  ConstructionManagement fromConstructionManagementEntity(
    ConstructionManagementEntity constructionManagementEntity
  );
  
  void updateConstructionSiteEntity(
    @MappingTarget ConstructionSiteEntity entity,
    ConstructionSiteInput input
  );
  
  void updateConstructionManagementEntity(
    @MappingTarget ConstructionManagementEntity entity,
    ConstructionManagementInput input
  );
}
