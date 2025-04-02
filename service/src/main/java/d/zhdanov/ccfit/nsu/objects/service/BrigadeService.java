package d.zhdanov.ccfit.nsu.objects.service;

import d.zhdanov.ccfit.nsu.objects.dto.BrigadeDTO;
import d.zhdanov.ccfit.nsu.objects.exceptions.BrigadeAbsent;
import d.zhdanov.ccfit.nsu.objects.mappers.BrigadeMapper;
import d.zhdanov.ccfit.nsu.objects.persistence.BrigadeRepository;
import d.zhdanov.ccfit.nsu.utils.Utils;
import d.zhdanov.graphql.types.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BrigadeService {
  private final BrigadeRepository brigadeRepository;
  private final BrigadeMapper     brigadeMapper;
  
  public BrigadeService(
    @Autowired BrigadeRepository brigadeRepository,
    @Autowired BrigadeMapper brigadeMapper
  ) {
    this.brigadeRepository = brigadeRepository;
    this.brigadeMapper     = brigadeMapper;
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  public BrigadeDTO findBrigade(final UUID id) {
    final var ret =
      brigadeRepository.findById(id).orElseThrow(BrigadeAbsent::new);
    return brigadeMapper.toBrigadeDTO(ret);
  }
  
  @PreAuthorize("hasRole('EMPLOYEE')")
  public List<BrigadeDTO> findAllBrigades(final Pagination pagination) {
    final var paged = Utils.getPageable(pagination);
    final var ret   = brigadeRepository.findAll(paged).toList();
    return brigadeMapper.toBrigadeDTOList(ret);
  }
}
