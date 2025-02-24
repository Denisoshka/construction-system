package d.zhdanov.ccfit.nsu.service.workers;

import d.zhdanov.ccfit.nsu.exceptions.workers.EmployeeNotFoundException;
import d.zhdanov.ccfit.nsu.exceptions.workers.PostPositionNotFoundException;
import d.zhdanov.ccfit.nsu.exceptions.workers.UnknownPostException;
import d.zhdanov.ccfit.nsu.mapper.workers.WorkersMapper;
import d.zhdanov.ccfit.nsu.persistence.workers.EmployeeRepository;
import d.zhdanov.ccfit.nsu.persistence.workers.EngineersPositionRepository;
import d.zhdanov.ccfit.nsu.persistence.workers.WorkersPositionRepository;
import d.zhdanov.ccfit.nsu.persistence.workers.dto.EmployeeDTO;
import graphql.com.google.common.base.Preconditions;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class WorkersService {

  private final EmployeeRepository employeeRepository;
  private final WorkersMapper workersMapper;
  private final WorkersPositionRepository workersPositionRepository;
  private final EngineersPositionRepository engineersPositionRepository;

  public WorkersService(@Autowired EmployeeRepository employeeRepository,
                        @Autowired
                        WorkersPositionRepository workersPositionRepository,
                        @Autowired
                        EngineersPositionRepository engineersPositionRepository,
                        @Autowired WorkersMapper workersMapper) {
    this.engineersPositionRepository = engineersPositionRepository;
    this.workersPositionRepository = workersPositionRepository;
    this.employeeRepository = employeeRepository;
    this.workersMapper = workersMapper;
  }

  private void onEngineerPosition(final @NotNull String post,
                                  final String position)
      throws PostPositionNotFoundException {
    Preconditions.checkArgument(EmployeeRepository.WORKERS_POST.equals(post));
    final var id = engineersPositionRepository.findByName(position);
    if (id == null) {
      throw new PostPositionNotFoundException();
    }
  }

  private void onWorkersPosition(final @NotNull String post,
                                 final String position)
      throws PostPositionNotFoundException {
    Preconditions.checkArgument(EmployeeRepository.ENGINEER_POST.equals(post));
    final var id = workersPositionRepository.findByName(position);
    if (id == null) {
      throw new PostPositionNotFoundException();
    }
  }

  @Transactional
  public EmployeeDTO create(@NotNull EmployeeDTO empIput) {
    if (empIput.getPost() == null) {
      empIput.setPost(EmployeeRepository.UNDEFINED_POST);
      empIput.setPosition(null);
    } else if (empIput.getPosition() == null) {
      throw new PostPositionNotFoundException();
    }

    var ret = employeeRepository.save(empIput);
    if (EmployeeRepository.ENGINEER_POST.equals(empIput.getPost())) {
      insertEngineerPosition(empIput, ret);
    } else if (EmployeeRepository.WORKERS_POST.equals(empIput.getPost())) {
      insertWorkerPosition(empIput, ret);
    } else if (!EmployeeRepository.UNDEFINED_POST.equals(empIput.getPost())) {
      throw new PostPositionNotFoundException();
    }

    return ret;
  }

  @Transactional
  public EmployeeDTO update(@NotNull final String systemId,
                            @NotNull final EmployeeDTO input) {
    final var cur = employeeRepository.findBySystemId(systemId);
    if (cur == null) {
      throw new EmployeeNotFoundException();
    }
    final var post = input.getPost();
    final var curPost = cur.getPost();
    if (input.getPost() != null && !curPost.equals(post)) {
      updatePostInfo(input, cur);
    }
  }

  private void updatePostInfo(final @NotNull EmployeeDTO input,
                              final @NotNull EmployeeDTO cur) {
    if (input.getPost() != null && !cur.getPost().equals(input.getPost())) {
      erasePreviousPost(cur);
    }

    if (true) {

    } else if (EmployeeRepository.WORKERS_POST.equals(cur.getPost())) {
      final var posInfo =
          workersPositionRepository.findByName(input.getPosition());
      if (posInfo == null) {
        throw new PostPositionNotFoundException();
      }
      final var id = posInfo.getId();
      workersPositionRepository.updatePosition(cur.getId(), id);
    } else if (!EmployeeRepository.UNDEFINED_POST.equals(cur.getPost())) {
      throw new UnknownPostException();
    }
  }

  private void insertWorkerPosition(@NotNull EmployeeDTO input,
                                    @NotNull EmployeeDTO current) {
    final var position =
        workersPositionRepository.findByName(input.getPosition());
    if (position == null) {
      throw new PostPositionNotFoundException();
    }
    workersPositionRepository.insertWorker(current.getId(), position.getId());
  }

  private void insertEngineerPosition(@NotNull EmployeeDTO input,
                                      @NotNull EmployeeDTO current) {
    final var position =
        engineersPositionRepository.findByName(input.getPosition());
    if (position == null) {
      throw new PostPositionNotFoundException();
    }
    engineersPositionRepository.insertEngineer(current.getId(),
        position.getId());
  }

  private void erasePreviousPost(final @NotNull EmployeeDTO cur) {
    if (EmployeeRepository.ENGINEER_POST.equals(cur.getPost())) {
      engineersPositionRepository.deleteById((long) cur.getId());
    } else if (EmployeeRepository.WORKERS_POST.equals(cur.getPost())) {
      workersPositionRepository.deleteById((long) cur.getId());
    }
    cur.setPosition(EmployeeRepository.UNDEFINED_POSITION);
    cur.setPost(EmployeeRepository.UNDEFINED_POST);
  }

  private void updateEngineerPosition(final @NotNull EmployeeDTO input,
                                      final @NotNull EmployeeDTO current) {
    final var posInfo =
        engineersPositionRepository.findByName(input.getPosition());
    if (posInfo == null) {
      throw new PostPositionNotFoundException();
    }
    engineersPositionRepository.updatePosition(current.getId(),
        posInfo.getId());
  }

  private void updateWorkerPosition(final @NotNull EmployeeDTO input,
                                    final @NotNull EmployeeDTO current) {
    final var posInfo =
        workersPositionRepository.findByName(input.getPosition());
    if (posInfo == null) {
      throw new PostPositionNotFoundException();
    }
    workersPositionRepository.updatePosition(current.getId(), posInfo.getId());
  }

  @Transactional
  public void createSideHeadTeam() {

  }

  @Transactional
  public void updateSideHeadTeam() {

  }
}
