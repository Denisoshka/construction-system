package d.zhdanov.ccfit.nsu.activity.persistence;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.WorkScheduleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface WorkScheduleRepository
  extends PagingAndSortingRepository<WorkScheduleEntity, UUID>,
          CrudRepository<WorkScheduleEntity, UUID> {}
