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
  EmployeeInfo toEmployeeInfo(final EmployeeEntity dto);
  
  @Mapping(target = "id", ignore = true)
  EmployeeInfo toEmployeeInfo(final EmployeeInput employee);
  //  List<EmployeeInfo> toEmployeeResponseList(final List<EmployeeInfoDTO> employeesDTO);
  
  List<EmployeeInfo> toEmployeeResponseListFromEmployeeInfoDTO(
    final List<EmployeeInfo> employeesInfoDTO
  );
  
  @Mapping(target = "employmentDate", ignore = true)
  @Mapping(target = "post", ignore = true)
  List<EmployeeInfo> toEmployeeResponseList(
    final List<EmployeeEntity> employeesDTO
  );
  
  @Mapping(target = "id", ignore = true)
  @Mapping(target = "employmentDate", ignore = true)
  @Mapping(target = "post", ignore = true)
  EmployeeEntity toEmployeeEntity(final EmployeeInput employee);
  
  EmployeeEntity toEmployeeEntity(final EmployeeInfo employee);
  
  void updateEmployeeEntity(
    final @MappingTarget EmployeeEntity employee,
    final EmployeeInfo info
  );
  
  @Mapping(target = "id", ignore = true)
  void updateEmployeeEntity(
    final @MappingTarget EmployeeEntity employee,
    final EmployeeInput info
  );
  
  List<EmployeeEntity> toEmployeeDTOList(final List<EmployeeInfo> employeesDTO);
}
