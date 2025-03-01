package d.zhdanov.ccfit.nsu.persistence.workers;

import d.zhdanov.ccfit.nsu.persistence.workers.dto.WorkerPositionDTO;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorkersPositionRepository
  extends PagingAndSortingRepository<WorkerPositionDTO, Integer>,
          CrudRepository<WorkerPositionDTO, Integer> {
  Optional<WorkerPositionDTO> findByName(String name);
  
  @Modifying
  void deleteByName(String name);
}
