package d.zhdanov.ccfit.nsu.activity.service;

import d.zhdanov.ccfit.nsu.activity.persistence.ProjectContractRepository;
import d.zhdanov.ccfit.nsu.activity.persistence.SchoolRepository;
import d.zhdanov.ccfit.nsu.activity.persistence.dto.ProjectContractEntity;
import d.zhdanov.ccfit.nsu.activity.persistence.dto.SchoolEntity;
import d.zhdanov.graphql.types.ProjectContractInput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ActivityService {
  private final ProjectContractRepository projectContractRepository;
  private final SchoolRepository          schoolRepository;
  private final Bridge
  
  public ActivityService(
    @Autowired ProjectContractRepository projectContractRepository
  ) {
    this.projectContractRepository = projectContractRepository;
  }
  
  @Transactional
  public ProjectContractEntity createSchoolProjectContract(final UUID contractUUID,
                                                           final ProjectContractInput ProjectContractInput,
                                                           final SchoolEntity schoolInfo
  ) {
  
  }
}
