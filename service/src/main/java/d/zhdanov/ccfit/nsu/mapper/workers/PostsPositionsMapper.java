package d.zhdanov.ccfit.nsu.mapper.workers;

import d.zhdanov.ccfit.nsu.persistence.workers.dto.EngineerPositionDTO;
import d.zhdanov.ccfit.nsu.persistence.workers.dto.WorkerPositionDTO;
import d.zhdanov.ccfit.nsu.service.workers.dto.EmployeeInfoDTO;
import d.zhdanov.graphql.types.EngineerPosition;
import d.zhdanov.graphql.types.EngineerPositionInput;
import d.zhdanov.graphql.types.WorkerPosition;
import d.zhdanov.graphql.types.WorkerPositionInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PostsPositionsMapper {
  WorkerPosition fromWorkerPositionDTO(WorkerPositionDTO dto);
  
  WorkerPositionDTO toWorkerPositionDTO(WorkerPositionInput dto);
  
  EngineerPosition fromEngineerPositionDTO(EngineerPositionDTO dto);
  
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
