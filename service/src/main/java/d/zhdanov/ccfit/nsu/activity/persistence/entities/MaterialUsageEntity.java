package d.zhdanov.ccfit.nsu.activity.persistence.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@Data
@Table("material_usage")
public class MaterialUsageEntity {
  @Id
  private UUID    id;
  private UUID    workUnitId;
  private UUID    materialId;
  private Integer planQuantity;
  private Integer factQuantity;
  
  @Transient
  private MaterialTypeEntity materialType;
  
  public static MaterialUsageEntity of(ResultSet rs) throws SQLException {
    MaterialUsageEntity usage = new MaterialUsageEntity();
    
    usage.setId(rs.getObject("mu_id", UUID.class));
    usage.setWorkUnitId(rs.getObject("mu_work_unit_id", UUID.class));
    usage.setMaterialId(rs.getObject("mu_material_id", UUID.class));
    usage.setPlanQuantity(rs.getInt("mu_plan_quantity"));
    usage.setFactQuantity(rs.getInt("mu_fact_quantity"));
    return usage;
  }
}
