package d.zhdanov.ccfit.nsu.activity.mapper;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.ProjectContractEntity;
import d.zhdanov.graphql.types.ProjectContract;
import d.zhdanov.graphql.types.ProjectContractInput;
import org.mapstruct.Mapper;

@Mapper
public interface ActivityMapper {
  ProjectContractEntity toProjectContractDTO(final ProjectContractInput input);
  
  ProjectContract toProjectContract(final ProjectContractEntity entity);
}
