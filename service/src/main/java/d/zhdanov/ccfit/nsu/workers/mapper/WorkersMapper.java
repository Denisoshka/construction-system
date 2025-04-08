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
//  @Mapping(target = "positionInfo", ignore = true)
  WorkerInfo fromWorkerEntityWithAdditionalData(
    final WorkerEntity workerEntity
  );
  
  List<WorkerInfo> toWorkersInfo(List<WorkerEntity> workerEntities);
  
  WorkerPosition fromWorkerPositionEntity(WorkerPositionEntity entity);
  
  List<WorkerPosition> fromWorkerPositionEntityList(
    List<WorkerPositionEntity> workerPositionEntities
  );
  
  @Mapping(target = "id", ignore = true)
  void updateWorkerPositionEntity(
    @MappingTarget WorkerPositionEntity entity, WorkerPositionInput input
  );
  
  List<WorkerInfo> fromWorkerEntityList(List<WorkerEntity> ret);
}
