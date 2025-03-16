package d.zhdanov.ccfit.nsu.utils.persistence.activity;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.ObjectTypeEntity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ObjectTypeEntityFetcher {
  protected ObjectTypeEntityFetcher() {
  }
  
  public static ObjectTypeEntity of(final ResultSet rs)
  throws SQLException {
    return new ObjectTypeEntity(
      rs.getInt("object_type_type_id"),
      rs.getString("object_type_type_name")
    );
  }
}
