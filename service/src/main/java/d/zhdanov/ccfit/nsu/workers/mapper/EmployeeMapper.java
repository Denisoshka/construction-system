package d.zhdanov.ccfit.nsu.workers.mapper;

import d.zhdanov.ccfit.nsu.workers.persistence.entities.EmployeeEntity;
import d.zhdanov.graphql.types.EmployeeInfo;
import d.zhdanov.graphql.types.EmployeeInput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING
)
public interface EmployeeMapper {
  @Mapping(target = "positionId", ignore = true)
  EmployeeInfo toEmployeeInfo(final EmployeeEntity dto);
  
  //  List<EmployeeInfo> toEmployeeResponseList(final List<EmployeeInfoDTO> employeesDTO);
  List<EmployeeInfo> toEmployeeResponseListFromEmployeeInfoDTO(final List<EmployeeInfo> employeesInfoDTO);
  
  List<EmployeeInfo> toEmployeeResponseList(final List<EmployeeEntity> employeesDTO);
  
  EmployeeInfo toEmployeeInfoDTO(final EmployeeInput employee);
  
  @Mapping(target = "post", defaultValue = "UNKNOWN")
  EmployeeEntity toEmployeeEntity(final EmployeeInput employee);
  
  @Mapping(target = "positionId", ignore = true)
  EmployeeInfo toEmployeeInfoDTO(final EmployeeEntity employee);
  
  @Mapping(target = "positionId", ignore = true)
  EmployeeEntity toEmployeeEntity(final EmployeeInfo employee);
  
  void updateEmployeeEntity(
    final @MappingTarget EmployeeEntity employee,
    final EmployeeInfo info
  );
  
  void updateEmployeeEntity(
    final @MappingTarget EmployeeEntity employee,
    final EmployeeInput info
  );
  
  List<EmployeeEntity> toEmployeeDTOList(final List<EmployeeInfo> employeesDTO);
}
