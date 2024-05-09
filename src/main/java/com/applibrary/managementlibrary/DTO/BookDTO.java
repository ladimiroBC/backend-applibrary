package com.applibrary.managementlibrary.DTO;

import com.applibrary.managementlibrary.models.Book;

public class BookDTO {
    private Book book;
    public BookDTO() { };

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
