package d.zhdanov.ccfit.nsu.objects.mappers;

import d.zhdanov.ccfit.nsu.objects.persistence.entities.ConstructionSiteEntity;
import d.zhdanov.graphql.types.ConstructionSite;
import d.zhdanov.graphql.types.ConstructionSiteInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING
)
public interface ConstructionObjectsMapper {
  ConstructionSite toConstructionSite(ConstructionSiteEntity constructionSiteEntity);
  
  List<ConstructionSite> toConstructionSiteList(
    List<ConstructionSiteEntity> constructionSiteEntities
  );
  
  @Mapping(target = "id", ignore = true)
  void updateConstructionSiteEntity(
    @MappingTarget ConstructionSiteEntity entity,
    ConstructionSiteInput input
  );
}
