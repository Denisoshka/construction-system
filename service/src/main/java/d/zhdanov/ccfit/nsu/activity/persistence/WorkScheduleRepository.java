package d.zhdanov.ccfit.nsu.activity.persistence;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.WorkScheduleEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface WorkScheduleRepository
  extends PagingAndSortingRepository<WorkScheduleEntity, UUID>,
          CrudRepository<WorkScheduleEntity, UUID> {
  Sort defSort = Sort.by(
    Sort.Direction.ASC,
    "plan_start_date",
    "plan_end_date"
  );
  
  List<WorkScheduleEntity> findAllByBrigadeId(
    UUID brigadeId,
    Pageable pageable
  );
  
  List<WorkScheduleEntity> findAllByContractId(
    UUID contractId,
    Pageable pageable
  );
  
}
