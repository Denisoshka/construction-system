package d.zhdanov.ccfit.nsu.utils;

import d.zhdanov.ccfit.nsu.workers.persistence.entities.JobPost;
import d.zhdanov.graphql.types.EmployeeFilter;
import d.zhdanov.graphql.types.EngineerFilter;
import d.zhdanov.graphql.types.Pagination;
import d.zhdanov.graphql.types.WorkerFilter;
import lombok.Data;
import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@UtilityClass
public class Utils {
  
  @NotNull
  public static Pageable getPageable(Pagination pagination) {
    if(pagination == null) {
      return Pageable.unpaged();
    }
    return PageRequest.of(pagination.getPage(), pagination.getPageSize());
  }
  
  @NotNull
  public static Pageable getPageable(Pagination pagination, Sort sort) {
    if(pagination == null) {
      return Pageable.unpaged(sort);
    }
    return PageRequest.of(pagination.getPage(), pagination.getPageSize(), sort);
  }
  
  public static EngineerRepositoryFilter getRepositoryEngineerFilter(
    EngineerFilter engineerFilter
  ) {
    return EngineerRepositoryFilter.of(engineerFilter);
  }
  
  public static EmployeeRepositoryFilter getRepositoryEmployeeFilter(
    EmployeeFilter employeeFilter
  ) {
    return EmployeeRepositoryFilter.of(employeeFilter);
  }
  
  public static WorkerRepositoryFilter getRepositoryWorkerFilter(
    WorkerFilter workerFilter
  ) {
    return WorkerRepositoryFilter.of(workerFilter);
  }
  
  @Data
  public static class EngineerRepositoryFilter {
    private static final EngineerRepositoryFilter emptyFilter =
      new EngineerRepositoryFilter(null);
    
    final Integer position;
    
    protected EngineerRepositoryFilter(Integer position) {
      this.position = position;
    }
    
    public static EngineerRepositoryFilter of(EngineerFilter engineerFilter) {
      if(engineerFilter == null) {
        return emptyFilter;
      }
      return new EngineerRepositoryFilter(engineerFilter.getPosId());
    }
    
    public Integer getPosition() {
      return position;
    }
  }
  
  @Data
  public static class EmployeeRepositoryFilter {
    private static final EmployeeRepositoryFilter emptyFilter =
      new EmployeeRepositoryFilter(null);
    
    final JobPost post;
    
    protected EmployeeRepositoryFilter(JobPost post) {
      this.post = post;
    }
    
    public static EmployeeRepositoryFilter of(EmployeeFilter filter) {
      if(filter == null) {
        return new EmployeeRepositoryFilter(null);
      }
      return new EmployeeRepositoryFilter(JobPost.valueOf(filter.getPost()));
    }
  }
  
  @Data
  public static class WorkerRepositoryFilter {
    private static final WorkerRepositoryFilter repositoryFilter =
      new WorkerRepositoryFilter(null);
    
    final Integer position;
    
    protected WorkerRepositoryFilter(Integer position) {
      this.position = position;
    }
    
    public static WorkerRepositoryFilter of(WorkerFilter filter) {
      if(filter == null) {
        return repositoryFilter;
      }
      return new WorkerRepositoryFilter(filter.getPosId());
    }
  }
}
