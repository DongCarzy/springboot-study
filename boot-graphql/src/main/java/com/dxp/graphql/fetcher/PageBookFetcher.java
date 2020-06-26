package com.dxp.graphql.fetcher;

import com.dxp.graphql.domain.Book;
import com.dxp.graphql.repository.BookRepository;
import com.google.common.collect.Lists;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * @author dxp
 */
@Component
public class PageBookFetcher implements DataFetcher<List<Book>> {

    private final BookRepository bookRepository;

    public PageBookFetcher(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> get(DataFetchingEnvironment dataFetchingEnvironment) {
        int page = dataFetchingEnvironment.getArgument("page");
        int size = dataFetchingEnvironment.getArgument("size");

        PageRequest pageable = PageRequest.of(page, size);
        String sortString = dataFetchingEnvironment.getArgument("sort");
        if (sortString == null || "".equals(sortString)){
            pageable = PageRequest.of(page, size);
        }else {
            String[] split = sortString.split(",");
            Sort.Order order = new Sort.Order(Sort.Direction.valueOf(split[1]), split[0]);
            pageable = PageRequest.of(page, size, Sort.by(order));
        }
        return this.bookRepository.findAll(pageable).getContent();
    }
}