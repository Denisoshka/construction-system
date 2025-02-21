package d.zhdanov.ccfit.nsu.service.workers;

import d.zhdanov.ccfit.nsu.exceptions.workers.EmployeeNotFoundException;
import d.zhdanov.ccfit.nsu.persistence.workers.WorkersRepository;
import d.zhdanov.ccfit.nsu.persistence.workers.dto.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WorkersService {
    private final WorkersRepository workersRepository;

    public WorkersService(@Autowired WorkersRepository employeeRepository) {
        this.workersRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee) {
        return workersRepository.save(employee);
    }

    public Employee getEmployee(String systemId) {
        var ret = workersRepository.findByPositionId(systemId);
        if (ret == null) {
            throw new EmployeeNotFoundException();
        }
        return ret;
    }
}