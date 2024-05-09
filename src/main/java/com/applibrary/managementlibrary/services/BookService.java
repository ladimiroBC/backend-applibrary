package com.applibrary.managementlibrary.services;

import com.applibrary.managementlibrary.models.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    public List<Book> findAll();
    public Book save(Book book);
    public void delete(Integer id);
    public Book findById(Integer id);
    public Book findByCode(String bookCode);
}
