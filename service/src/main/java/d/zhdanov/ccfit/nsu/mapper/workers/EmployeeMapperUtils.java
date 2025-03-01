package d.zhdanov.ccfit.nsu.mapper.workers;

import d.zhdanov.ccfit.nsu.persistence.workers.EmployeeRepository;
import io.micrometer.common.util.StringUtils;
import org.mapstruct.Named;

import java.util.UUID;

@Named("EmployeeMapperUtils")
public class EmployeeMapperUtils {
  @Named("preExecuteEmployeePostField")
  public static String preExecuteEmployeePostField(String employeePostField) {
    return StringUtils.isBlank(employeePostField)
           ? EmployeeRepository.UNDEFINED_POST : employeePostField;
  }
  
  @Named("preExecutePositionField")
  public static String preExecutePositionField(String positionField) {
    return StringUtils.isBlank(positionField)
           ? EmployeeRepository.UNDEFINED_POSITION : positionField;
  }
  
  @Named("preExecuteStringUUIDField")
  public static UUID preExecuteStringUUIDField(String uuidField) {
    return UUID.fromString(uuidField);
  }
}
