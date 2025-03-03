package d.zhdanov.ccfit.nsu.persistence.activity;

import d.zhdanov.ccfit.nsu.persistence.activity.dto.SchoolDTO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface SchoolRepository extends
                                  PagingAndSortingRepository<SchoolDTO, UUID>,
                                  CrudRepository<SchoolDTO, UUID> {}
