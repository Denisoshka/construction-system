package d.zhdanov.ccfit.nsu.workers.mapper;

import d.zhdanov.ccfit.nsu.workers.persistence.dto.EngineerPositionEntity;
import d.zhdanov.ccfit.nsu.workers.persistence.dto.WorkerPositionEntity;
import d.zhdanov.graphql.types.EngineerPositionInfo;
import d.zhdanov.graphql.types.EngineerPositionInput;
import d.zhdanov.graphql.types.WorkerPositionInfo;
import d.zhdanov.graphql.types.WorkerPositionInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING, uses = {
  EmployeeMapperUtils.class
}
)
public interface PostsPositionsMapper {
  WorkerPositionInfo fromWorkerPositionDTO(WorkerPositionEntity dto);
  
  @Mapping(target = "id", ignore = true)
  WorkerPositionEntity toWorkerPositionDTO(WorkerPositionInput dto);
  
  EngineerPositionInfo fromEngineerPositionDTO(EngineerPositionEntity dto);
  
  @Mapping(target = "id", ignore = true)
  EngineerPositionEntity toEngineerPositionDTO(EngineerPositionInput dto);
  
  @Mapping(target = "id", expression = "java(Id)")
  EngineerPositionEntity toEngineerPositionDTOWithID(EngineerPositionEntity dto,
                                                     final int Id
  );
  
  @Mapping(target = "id", expression = "java(Id)")
  WorkerPositionEntity toWorkerPositionDTOWithID(WorkerPositionInput dto,
                                                 final int Id
  );
  
  /*@Mapping(
    target = "post", qualifiedByName = {
    "EmployeeMapperUtils", "preExecuteEmployeePostField"
  }, source = "post"
  )
  @Mapping(
    target = "position", qualifiedByName = {
    "EmployeeMapperUtils", "preExecutePositionField"
  }, source = "position"
  )
  void fixPostPositionFields(final EmployeeInfoDTO from,
                             final @MappingTarget EmployeeInfoDTO to
  );*/
}
