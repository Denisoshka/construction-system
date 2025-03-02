package d.zhdanov.ccfit.nsu.mapper.workers;

import d.zhdanov.ccfit.nsu.persistence.workers.dto.EngineerPositionDTO;
import d.zhdanov.ccfit.nsu.persistence.workers.dto.WorkerPositionDTO;
import d.zhdanov.ccfit.nsu.service.workers.dto.EmployeeInfoDTO;
import d.zhdanov.graphql.types.EngineerPositionInfo;
import d.zhdanov.graphql.types.EngineerPositionInput;
import d.zhdanov.graphql.types.WorkerPositionInfo;
import d.zhdanov.graphql.types.WorkerPositionInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING, uses = {
  EmployeeMapperUtils.class
}
)
public interface PostsPositionsMapper {
  WorkerPositionInfo fromWorkerPositionDTO(WorkerPositionDTO dto);
  
  @Mapping(target = "Id", ignore = true)
  WorkerPositionDTO toWorkerPositionDTO(WorkerPositionInput dto);
  
  EngineerPositionInfo fromEngineerPositionDTO(EngineerPositionDTO dto);
  
  @Mapping(target = "Id", ignore = true)
  EngineerPositionDTO toEngineerPositionDTO(EngineerPositionInput dto);
  
  @Mapping(target = "id", expression = "java(Id)")
  EngineerPositionDTO toEngineerPositionDTOWithID(EngineerPositionDTO dto,
                                                  final int Id
  );
  
  @Mapping(target = "id", expression = "java(Id)")
  WorkerPositionDTO toWorkerPositionDTOWithID(WorkerPositionInput dto,
                                              final int Id
  );
  
  @Mapping(
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
  );
}
