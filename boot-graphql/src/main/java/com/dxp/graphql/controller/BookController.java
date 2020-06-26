package com.dxp.graphql.controller;

import com.dxp.graphql.controller.query.Query;
import com.dxp.graphql.service.GraphQLService;
import graphql.ExecutionResult;
import graphql.GraphQLError;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.util.List;


@RestController
@RequestMapping(path = "/v1/books")
public class BookController {


    public BookController(GraphQLService graphQLService) {
        this.graphQLService = graphQLService;
    }

    private final GraphQLService graphQLService;

    @PostMapping
    public Mono<Object> getAllBooks(@RequestBody Query query) {
            ExecutionResult execute = graphQLService.execute(query.getQuery());
        List<GraphQLError> errors = execute.getErrors();
        if (errors != null && !errors.isEmpty()) {
            return Mono.error(new Exception(errors.get(0).getMessage()));
        } else {
            return Mono.just(execute.getData());
        }
    }
}