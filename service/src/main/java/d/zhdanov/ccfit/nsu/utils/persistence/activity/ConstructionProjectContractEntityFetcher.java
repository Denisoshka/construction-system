package d.zhdanov.ccfit.nsu.utils.persistence.activity;

import d.zhdanov.ccfit.nsu.activity.persistence.entities.ConstructionProjectContractEntity;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;

public class ConstructionProjectContractEntityFetcher {
  protected ConstructionProjectContractEntityFetcher() {
  }
  
  public static ConstructionProjectContractEntity of(final ResultSet rs)
  throws SQLException {
    ConstructionProjectContractEntity contract =
      new ConstructionProjectContractEntity();
    
    contract.setId(rs.getObject("id", UUID.class));
    contract.setSiteId(rs.getObject("site_id", UUID.class));
    contract.setCustomerOrganizationId(rs.getObject(
      "customer_organization_id",
      UUID.class
    ));
    contract.setObjectType(rs.getInt("type_id"));
    contract.setDateOfCreation(rs.getObject(
      "date_of_creation",
      LocalDate.class
    ));
    contract.setSigningDate(rs.getObject("signing_date", LocalDate.class));
    contract.setAcceptanceDate(rs.getObject(
      "acceptance_date",
      LocalDate.class
    ));
    contract.setPlanStartDate(rs.getObject("plan_start_date", LocalDate.class));
    contract.setPlanEndDate(rs.getObject("plan_end_date", LocalDate.class));
    contract.setFactStartDate(rs.getObject("fact_start_date", LocalDate.class));
    contract.setFactEndDate(rs.getObject("fact_end_date", LocalDate.class));
    
    return contract;
  }
}
