package d.zhdanov.ccfit.nsu.config;

import com.netflix.graphql.types.errors.ErrorType;
import com.netflix.graphql.types.errors.TypedGraphQLError;
import d.zhdanov.ccfit.nsu.workers.exceptions.EmployeeNotFoundException;
import graphql.GraphQLError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
@Slf4j
public class ControllerExceptionHandler {
  @GraphQlExceptionHandler
  public GraphQLError handle(EmployeeNotFoundException e) {
    return GraphQLError.newError().errorType(ErrorType.NOT_FOUND)
      .message("employee not found").build();
  }
  
  @GraphQlExceptionHandler
  public GraphQLError handle(RuntimeException e) {
    return GraphQLError.newError().errorType(ErrorType.INTERNAL)
      .message("unexpected error").build();
  }
  
  @GraphQlExceptionHandler
  public GraphQLError handle(AccessDeniedException e) {
    return GraphQLError.newError().errorType(ErrorType.PERMISSION_DENIED)
      .message("access denied").build();
  }
}
