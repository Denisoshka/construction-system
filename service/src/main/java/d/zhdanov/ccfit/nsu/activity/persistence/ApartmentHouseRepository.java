package d.zhdanov.ccfit.nsu.activity.persistence;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.ApartmentHouseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ApartmentHouseRepository
  extends PagingAndSortingRepository<ApartmentHouseEntity, UUID>,
          CrudRepository<ApartmentHouseEntity, UUID> {}
