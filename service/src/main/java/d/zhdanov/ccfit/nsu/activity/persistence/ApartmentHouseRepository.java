package d.zhdanov.ccfit.nsu.activity.persistence;

import d.zhdanov.ccfit.nsu.activity.persistence.dto.ApartmentHouseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.UUID;

public interface ApartmentHouseRepository
  extends PagingAndSortingRepository<ApartmentHouseEntity, UUID>,
          CrudRepository<ApartmentHouseEntity, UUID> {}
