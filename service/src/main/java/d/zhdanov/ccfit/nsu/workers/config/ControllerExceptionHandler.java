package d.zhdanov.ccfit.nsu.workers.config;

import com.netflix.graphql.types.errors.ErrorType;
import d.zhdanov.ccfit.nsu.workers.exceptions.EmployeeNotFoundException;
import graphql.GraphQLError;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ControllerExceptionHandler {
  @GraphQlExceptionHandler
  public GraphQLError handle(EmployeeNotFoundException e) {
    return GraphQLError.newError().errorType(ErrorType.NOT_FOUND).build();
  }
  
  @GraphQlExceptionHandler
  public GraphQLError handle(RuntimeException e) {
    return GraphQLError.newError().errorType(ErrorType.INTERNAL).build();
  }
}
