package d.zhdanov.ccfit.nsu.mapper.workers;

import d.zhdanov.ccfit.nsu.persistence.workers.dto.EmployeeDTO;
import d.zhdanov.ccfit.nsu.service.workers.dto.EmployeeInfoDTO;
import d.zhdanov.graphql.types.EmployeeInfo;
import d.zhdanov.graphql.types.EmployeeInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING, uses = {
  EmployeeMapperUtils.class
}
)
public interface EmployeeMapper {
  EmployeeInfo toEmployeeResponse(final EmployeeInfoDTO dto);
  
  @Mapping(target = "positionId", ignore = true)
  EmployeeInfo toEmployeeResponse(final EmployeeDTO dto);
  
  //  List<EmployeeInfo> toEmployeeResponseList(final List<EmployeeInfoDTO> employeesDTO);
  List<EmployeeInfo> toEmployeeResponseListFromEmployeeInfoDTO(final List<EmployeeInfoDTO> employeesInfoDTO);
  
  List<EmployeeInfo> toEmployeeResponseList(final List<EmployeeDTO> employeesDTO);
  
  EmployeeInfoDTO toEmployeeInfoDTO(final EmployeeInput employee);
  
  @Mapping(target = "positionId", ignore = true)
  EmployeeInfoDTO toEmployeeInfoDTO(final EmployeeDTO employee);
  
  @Mapping(target = "positionId", ignore = true)
  EmployeeDTO toEmployeeDTO(final EmployeeInfoDTO employee);
  
  @Mapping(target = "positionId", ignore = true)
  void updateEmployeeDTO(final EmployeeInput employee,
                         final @MappingTarget EmployeeDTO employeeDTO
  );
  
  @Mapping(target = "positionId", ignore = true)
  void exchangeEmployeeDTO(final EmployeeDTO employee,
                           final @MappingTarget EmployeeInfoDTO employeeDTO
  );
  
  List<EmployeeDTO> toEmployeeDTOList(final List<EmployeeInfo> employeesDTO);
}
