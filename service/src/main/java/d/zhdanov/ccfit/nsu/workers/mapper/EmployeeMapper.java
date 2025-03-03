package d.zhdanov.ccfit.nsu.workers.mapper;

import d.zhdanov.ccfit.nsu.workers.persistence.dto.EmployeeEntity;
import d.zhdanov.ccfit.nsu.workers.service.dto.EmployeeInfoDTO;
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
  EmployeeInfo toEmployeeResponse(final EmployeeEntity dto);
  
  //  List<EmployeeInfo> toEmployeeResponseList(final List<EmployeeInfoDTO> employeesDTO);
  List<EmployeeInfo> toEmployeeResponseListFromEmployeeInfoDTO(final List<EmployeeInfoDTO> employeesInfoDTO);
  
  List<EmployeeInfo> toEmployeeResponseList(final List<EmployeeEntity> employeesDTO);
  
  EmployeeInfoDTO toEmployeeInfoDTO(final EmployeeInput employee);
  
  @Mapping(target = "positionId", ignore = true)
  EmployeeInfoDTO toEmployeeInfoDTO(final EmployeeEntity employee);
  
  @Mapping(target ="positionId", ignore = true)
  EmployeeEntity toEmployeeDTO(final EmployeeInfoDTO employee);
  
  @Mapping(target = "positionId", ignore = true)
  void updateEmployeeDTO(final EmployeeInput employee,
                         final @MappingTarget EmployeeEntity employeeEntity
  );
  
  @Mapping(target = "positionId", ignore = true)
  void exchangeEmployeeDTO(final EmployeeEntity employee,
                           final @MappingTarget EmployeeInfoDTO employeeDTO
  );
  
  List<EmployeeEntity> toEmployeeDTOList(final List<EmployeeInfo> employeesDTO);
}
