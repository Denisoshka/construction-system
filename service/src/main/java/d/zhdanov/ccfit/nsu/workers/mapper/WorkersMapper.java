package d.zhdanov.ccfit.nsu.workers.mapper;

import d.zhdanov.ccfit.nsu.workers.persistence.dto.WorkerPositionEntity;
import d.zhdanov.graphql.types.WorkerPosition;
import d.zhdanov.graphql.types.WorkerPositionInput;
import org.mapstruct.Mapping;

public interface WorkersMapper {
  WorkerPosition fromWorkerPositionEntity(final WorkerPositionEntity entity);
  
  @Mapping(target = "id", ignore = true)
  WorkerPositionEntity toWorkerPositionEntity(final WorkerPositionInput input);
  
  @Mapping(target = "id", expression = "java(Id)")
  WorkerPositionEntity toWorkerPositionDTOWithID(
    final WorkerPositionInput dto,
    final int Id
  );
}
