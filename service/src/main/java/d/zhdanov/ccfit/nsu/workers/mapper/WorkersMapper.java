package d.zhdanov.ccfit.nsu.workers.mapper;

import d.zhdanov.ccfit.nsu.workers.persistence.entities.WorkerEntity;
import d.zhdanov.ccfit.nsu.workers.persistence.entities.WorkerPositionEntity;
import d.zhdanov.graphql.types.WorkerInfo;
import d.zhdanov.graphql.types.WorkerPosition;
import d.zhdanov.graphql.types.WorkerPositionInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING, uses = EmployeeMapper.class
)
public interface WorkersMapper {
  @Mapping(target = "employee", source = "employee")
  WorkerInfo fromWorkerEntityWithAdditionalData(final WorkerEntity workerEntity);
  
  List<WorkerInfo> toWorkersInfo(final List<WorkerEntity> workerEntities);
  
  WorkerPosition fromWorkerPositionEntity(final WorkerPositionEntity entity);
  
  List<WorkerPosition> fromWorkerPositionEntityList(
    final List<WorkerPositionEntity> workerPositionEntities
  );
  
  void updateWorkerPositionEntity(
    @MappingTarget final WorkerPositionEntity entity,
    final WorkerPositionInput input
  );
}
