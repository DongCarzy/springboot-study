package com.dxp.graphql.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table
@Setter
@Getter
public class Book {

    @Id
    private String id;

    private String title;

    private String publisher;

    private String authors;

    private String publishedDate;

    public Book(){}

    public Book(String id, String title, String publisher, String authors, String publishedDate) {
        this.id = id;
        this.title = title;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.authors = authors;
    }
}
