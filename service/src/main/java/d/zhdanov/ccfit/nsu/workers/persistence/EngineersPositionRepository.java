package d.zhdanov.ccfit.nsu.workers.persistence;

import d.zhdanov.ccfit.nsu.workers.persistence.dto.EngineerPositionEntity;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EngineersPositionRepository
  extends PagingAndSortingRepository<EngineerPositionEntity, Integer>,
          CrudRepository<EngineerPositionEntity, Integer> {
  Optional<EngineerPositionEntity> findByName(String name);
  
  @Modifying
  void deleteByName(String name);
}

