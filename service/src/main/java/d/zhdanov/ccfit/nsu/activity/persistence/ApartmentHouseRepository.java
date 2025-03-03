package d.zhdanov.ccfit.nsu.activity.persistence;

import d.zhdanov.ccfit.nsu.activity.persistence.dto.ProjectContractEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ApartmentHouseRepository
  extends PagingAndSortingRepository<ProjectContractEntity, UUID>,
          CrudRepository<ProjectContractEntity, UUID> {}
