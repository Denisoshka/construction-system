package d.zhdanov.ccfit.nsu.workers.controllers;

import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import d.zhdanov.ccfit.nsu.util.Utils;
import d.zhdanov.graphql.types.EmployeeInfo;
import d.zhdanov.graphql.types.Pagination;

import java.util.List;

public class EngineersDataFetcher {
  @DgsQuery
  public List<EmployeeInfo> engineers(
    @InputArgument Pagination pagination,
    @InputArgument Integer positionID
  ) {
    final var paged = Utils.getPageable(pagination);
    final var ret   = employeeService.engineers(paged, positionID);
    return
  }
}
