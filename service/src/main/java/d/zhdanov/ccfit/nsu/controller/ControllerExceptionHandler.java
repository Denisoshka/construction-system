package d.zhdanov.ccfit.nsu.controller;

import d.zhdanov.ccfit.nsu.exceptions.workers.EmployeeNotFoundException;
import graphql.GraphQLError;
import org.springframework.graphql.data.method.annotation.GraphQlExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;

import static com.netflix.graphql.types.errors.ErrorType.NOT_FOUND;

@ControllerAdvice
public class ControllerExceptionHandler {
    @GraphQlExceptionHandler
    public GraphQLError handle(EmployeeNotFoundException e) {
        return GraphQLError.newError().errorType(NOT_FOUND).build();
    }

}
