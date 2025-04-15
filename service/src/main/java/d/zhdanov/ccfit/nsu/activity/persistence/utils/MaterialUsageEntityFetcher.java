package d.zhdanov.ccfit.nsu.activity.persistence.utils;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.MaterialUsageEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class MaterialUsageEntityFetcher {
  static MaterialUsageEntity of(ResultSet rs) throws SQLException {
    MaterialUsageEntity usage = new MaterialUsageEntity();
    
    usage.setId(rs.getObject("mu_id", UUID.class));
    usage.setWorkUnitId(rs.getObject("mu_work_unit_id", UUID.class));
    usage.setMaterialId(rs.getObject("mu_material_id", UUID.class));
    usage.setPlanQuantity(rs.getInt("mu_plan_quantity"));
//    usage.setFactQuantity(rs.getInt("fact_quantity"));
    return usage;
  }
}
