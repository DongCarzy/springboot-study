package com.dxp.graphql.service;

import com.dxp.graphql.fetcher.AllBooksDataFetcher;
import com.dxp.graphql.fetcher.BookDataFetcher;
import com.dxp.graphql.fetcher.PageBookFetcher;
import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Service
public class GraphQLService {

    private final AllBooksDataFetcher allBooksDataFetcher;
    private final BookDataFetcher bookDataFetcher;
    private final PageBookFetcher pageBookFetcher;

    private GraphQL graphQL;

    @Value("classpath:book.graphql")
    private Resource resource;

    public GraphQLService(AllBooksDataFetcher allBooksDataFetcher, BookDataFetcher bookDataFetcher, PageBookFetcher pageBookFetcher) {
        this.allBooksDataFetcher = allBooksDataFetcher;
        this.bookDataFetcher = bookDataFetcher;
        this.pageBookFetcher = pageBookFetcher;
    }

    @PostConstruct
    private void loadSchema() throws IOException {
        // 解析 Schema 文件
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(resource.getFile());

        RuntimeWiring wiring = buildRuntimeWiring();

        SchemaGenerator schemaGenerator = new SchemaGenerator();
        GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, wiring);

        graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }

    public ExecutionResult execute(String query) {
        return this.graphQL.execute(query);
    }

    private RuntimeWiring buildRuntimeWiring() {
        return RuntimeWiring.newRuntimeWiring()
                .type("Query", typeWiring -> typeWiring
                        .dataFetcher("allBooks", allBooksDataFetcher)
                        .dataFetcher("book", bookDataFetcher)
                        .dataFetcher("page", pageBookFetcher)
                ).build();
    }
}
