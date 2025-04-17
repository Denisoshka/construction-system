package d.zhdanov.ccfit.nsu.activity.persistence.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("manufacturer_table")
public class ManufacturerEntity {
  @Id
  private UUID   id;
  @NotNull
  private String manufacturer;
  
  public static ManufacturerEntity of(ResultSet rs) throws SQLException {
    return new ManufacturerEntity(
      rs.getObject("mr_id", UUID.class),
      rs.getString("mr_manufacturer")
    );
  }
}
