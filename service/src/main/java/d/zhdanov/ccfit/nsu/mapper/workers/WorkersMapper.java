package d.zhdanov.ccfit.nsu.mapper.workers;

import d.zhdanov.ccfit.nsu.persistence.workers.dto.EmployeeDTO;
import d.zhdanov.ccfit.nsu.service.workers.dto.EmployeeInfoDTO;
import d.zhdanov.graphql.types.Employee;
import d.zhdanov.graphql.types.EmployeeInput;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface WorkersMapper {
  Employee toEmployeeResponse(final EmployeeInfoDTO dto);
  
  Employee toEmployeeResponse(final EmployeeDTO dto);
  
  List<Employee> toEmployeeResponseList(final List<EmployeeDTO> employeesDTO);
  
  EmployeeInfoDTO toEmployeeInfoDTO(final EmployeeInput employee);
  
  EmployeeInfoDTO toEmployeeInfoDTO(final EmployeeDTO employee);
  
  EmployeeDTO toEmployeeDTO(final EmployeeInfoDTO employee);
  
  void updateEmployeeDTO(final EmployeeInput employee,
                         final @MappingTarget EmployeeDTO employeeDTO
  );
  
  void exchangeEmployeeDTO(final EmployeeDTO employee,
                           final @MappingTarget EmployeeInfoDTO employeeDTO
  );
  
  List<EmployeeDTO> toEmployeeDTOList(final List<Employee> employeesDTO);
}
