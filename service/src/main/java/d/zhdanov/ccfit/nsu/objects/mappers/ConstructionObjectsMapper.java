package d.zhdanov.ccfit.nsu.objects.mappers;

import d.zhdanov.ccfit.nsu.objects.dto.ConstructionSiteDTO;
import d.zhdanov.ccfit.nsu.objects.persistence.entities.ConstructionSiteEntity;
import d.zhdanov.graphql.types.ConstructionSite;
import d.zhdanov.graphql.types.ConstructionSiteInput;
import org.mapstruct.*;

import java.util.List;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING,
  builder = @Builder(disableBuilder = true)
)
public interface ConstructionObjectsMapper {
  ConstructionSite toConstructionSite(ConstructionSiteEntity constructionSiteEntity);
  
  List<ConstructionSite> toConstructionSiteList(
    List<ConstructionSiteEntity> constructionSiteEntities
  );
  
  ConstructionSiteDTO toConstructionSiteDTO(ConstructionSiteEntity constructionSiteEntity);
  List<ConstructionSiteDTO> toConstructionSiteDTOList(
    List<ConstructionSiteEntity> constructionSiteEntities
  );
  
  @Mapping(target = "id", ignore = true)
  void updateConstructionSiteEntity(
    @MappingTarget ConstructionSiteEntity entity,
    ConstructionSiteInput input
  );
}
