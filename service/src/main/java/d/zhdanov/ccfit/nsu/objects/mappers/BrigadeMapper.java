package d.zhdanov.ccfit.nsu.objects.mappers;

import d.zhdanov.ccfit.nsu.objects.dto.BrigadeDTO;
import d.zhdanov.ccfit.nsu.objects.persistence.entities.BrigadeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(
  componentModel = MappingConstants.ComponentModel.SPRING
)
public interface BrigadeMapper {
  BrigadeDTO toBrigadeDTO(BrigadeEntity entity);
  
  List<BrigadeDTO> toBrigadeDTOList(List<BrigadeEntity> entities);
}
