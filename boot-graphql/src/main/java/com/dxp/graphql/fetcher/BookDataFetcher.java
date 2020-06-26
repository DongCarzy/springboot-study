package com.dxp.graphql.fetcher;

import com.dxp.graphql.domain.Book;
import com.dxp.graphql.repository.BookRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookDataFetcher implements DataFetcher<Book> {

  private final BookRepository bookRepository;

  public BookDataFetcher(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public Book get(DataFetchingEnvironment dataFetchingEnvironment) {
    String isn = dataFetchingEnvironment.getArgument("id");
    return bookRepository.findById(isn).orElse(null);
  }
}