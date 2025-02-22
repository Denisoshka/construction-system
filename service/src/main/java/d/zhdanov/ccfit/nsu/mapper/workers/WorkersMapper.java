package d.zhdanov.ccfit.nsu.mapper.workers;

import d.zhdanov.ccfit.nsu.persistence.workers.dto.EmployeeDTO;
import d.zhdanov.graphql.types.Employee;
import d.zhdanov.graphql.types.EmployeeInput;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WorkersMapper {
  Employee toEmployeeResponse(EmployeeDTO employeeDTO);

  List<Employee> toEmployeeResponseList(List<EmployeeDTO> employeesDTO);

  EmployeeDTO toEmployeeDTO(Employee employeeDTO);

  void updateEmployeeDTO(EmployeeInput employee,
                         @MappingTarget EmployeeDTO employeeDTO);

  List<EmployeeDTO> toEmployeeDTOList(List<Employee> employeesDTO);
}
