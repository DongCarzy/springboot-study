package com.dxp.graphql.db;

import com.dxp.graphql.domain.Book;
import com.dxp.graphql.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class H2Init implements CommandLineRunner {

    private final BookRepository bookRepository;

    public H2Init(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadDataIntoHSQL();
    }

    /**
     * 采用的是内存数据库 h2.  这里初始化数据
     */
    private void loadDataIntoHSQL() {
        Stream.of(
                new Book("9787111213826", "Java 编程思想（第4版）", "机械工业出版社",
                        "Bruce Eckel", "2007-06-01"),
                new Book("9787115221704", "重构 改善既有代码的设计（第2版）", "人民邮电出版社",
                        "Martin Fowler", "2019-05-01"),
                new Book("9787302392644", "人月神话（40周年中文纪念版）", "清华大学出版社",
                        "Brooks", "2015-04-01"),
                new Book("9787111421900", "深入理解 Java 虚拟机：JVM 高级特性与最佳实践（第2版）", "机械工业出版社",
                        "周志明", "2013-05-01"),
                new Book("9787121362132", "高可用可伸缩微服务架构：基于 Dubbo、Spring Cloud 和 Service Mesh", "电子工业出版社",
                        "程超", "2019-05-01")
        ).forEach(book -> bookRepository.save(book));
    }

}
