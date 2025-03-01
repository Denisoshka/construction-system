package d.zhdanov.ccfit.nsu.persistence.workers;

import d.zhdanov.ccfit.nsu.persistence.workers.dto.EngineerPositionDTO;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EngineersPositionRepository
  extends PagingAndSortingRepository<EngineerPositionDTO, Integer>,
          CrudRepository<EngineerPositionDTO, Integer> {
  Optional<EngineerPositionDTO> findByName(String name);
  
  @Modifying
  void deleteByName(String name);
}

