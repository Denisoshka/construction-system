package d.zhdanov.ccfit.nsu.activity.mapper;

import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING,
  builder = @Builder(disableBuilder = true)
)
public interface UUIDConverter {
  @Named("stringToUuid")
  default UUID stringToUuid(String id) {
    return id != null ? UUID.fromString(id) : null;
  }
}
