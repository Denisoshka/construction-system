package d.zhdanov.ccfit.nsu.activity.persistence;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.WorkScheduleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WorkScheduleRepository
  extends PagingAndSortingRepository<WorkScheduleEntity, UUID>,
          CrudRepository<WorkScheduleEntity, UUID> {}
