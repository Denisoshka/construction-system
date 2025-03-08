package d.zhdanov.ccfit.nsu.workers.persistence;

import d.zhdanov.ccfit.nsu.workers.persistence.entities.EmployeeEntity;
import d.zhdanov.ccfit.nsu.workers.persistence.entities.JobPost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface EmployeeRepository
  extends PagingAndSortingRepository<EmployeeEntity, UUID>,
          CrudRepository<EmployeeEntity, UUID> {
  @Transactional
  void deleteBySystemId(@Param("system_id") String systemId);
  
  List<EmployeeEntity> findAllByPost(JobPost post, Pageable pageable);
  
  Optional<EmployeeEntity> findBySystemId(@Param("system_id") String systemId);
}
