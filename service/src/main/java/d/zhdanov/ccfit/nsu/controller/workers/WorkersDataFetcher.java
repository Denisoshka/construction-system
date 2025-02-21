package d.zhdanov.ccfit.nsu.controller.workers;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import d.zhdanov.ccfit.nsu.service.workers.WorkersService;
import d.zhdanov.graphql.types.Employee;

@DgsComponent
public class WorkersDataFetcher {
    private final WorkersService workersService;

    public WorkersDataFetcher(WorkersService workersService) {
        this.workersService = workersService;
    }

    @DgsQuery
    public Employee systemId(@InputArgument String systemId) {
        final var emp = workersService.getEmployee(systemId);

    }
}
