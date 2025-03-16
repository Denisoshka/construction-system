package d.zhdanov.ccfit.nsu.utils.persistence.activity;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.ConstructionProjectContractEntity;
import org.jetbrains.annotations.NotNull;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ConstructionProjectContractWithTypeRowMapper
  implements RowMapper<ConstructionProjectContractEntity> {
  @Override
  public ConstructionProjectContractEntity mapRow(
    @NotNull ResultSet rs,
    int rowNum
  )
  throws SQLException {
    final var entity = ConstructionProjectContractEntityFetcher.of(rs);
    final var objectTypeEntity = ObjectTypeEntityFetcher.of(rs);
    entity.setObjectTypeEntity(objectTypeEntity);
    return entity;
  }
}
