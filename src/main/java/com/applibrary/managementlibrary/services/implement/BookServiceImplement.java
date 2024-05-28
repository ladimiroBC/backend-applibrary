package com.applibrary.managementlibrary.services.implement;

import com.applibrary.managementlibrary.dao.BookDao;
import com.applibrary.managementlibrary.dao.LoanDao;
import com.applibrary.managementlibrary.models.Book;
import com.applibrary.managementlibrary.models.Loan;
import com.applibrary.managementlibrary.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class BookServiceImplement implements BookService {

    @Autowired
    private BookDao bookDao;

    @Transactional(readOnly = false)
    public List<Book> findAll() {
        return (List<Book>) bookDao.findAll();
    }

    @Transactional(readOnly = false)
    public Book save(Book book) {
        return bookDao.save(book);
    }

    @Transactional(readOnly = false)
    public void delete(Integer id) {
        bookDao.deleteById(id);
    }

    @Transactional(readOnly = false)
    public Book findById(Integer id) {
        return bookDao.findById(id).orElse(null);
    }

    @Override
    public Book findByCode(String bookCode) {
        return bookDao.findByCode(bookCode);
    }
}
