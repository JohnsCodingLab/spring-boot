package com.johnscodinglab.book;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {
    public List<Book> selectAllBooks() {
        return List.of(
                new Book("Harry Potter", "JFK")
        );
    }
}