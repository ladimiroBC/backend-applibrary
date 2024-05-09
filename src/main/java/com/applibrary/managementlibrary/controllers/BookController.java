package com.applibrary.managementlibrary.controllers;

import com.applibrary.managementlibrary.DTO.BookDTO;
import com.applibrary.managementlibrary.common.constants.ErrorMessagesConstants;
import com.applibrary.managementlibrary.models.Book;
import com.applibrary.managementlibrary.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("book/")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping(value = "/")
    public ResponseEntity<Book> add(@RequestBody Book book) {
        Book object = bookService.save(book);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Book> delete(@PathVariable Integer id) {
        Book object = bookService.findById(id);

        if(object != null)
            bookService.delete(id);
        else
            return new ResponseEntity<>(object, HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @PutMapping(value = "/")
    public ResponseEntity<Book> edit(@RequestBody Book book) {
        Book object = bookService.findById(book.getIdBook());

        if (object != null) {
            object.setName(book.getName());
            object.setEditorial(book.getEditorial());
            object.setAutor(book.getAutor());
            object.setStateBook(book.isStateBook());
            object.setImg((book.getImg()));
            object.setRegisterCode((book.getRegisterCode()));
            bookService.save(object);
        }
        else {
            return new ResponseEntity<>(object, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @GetMapping("/list")
    public List<Book> read() {
        return bookService.findAll();
    }

    @GetMapping("/list/{id}")
    public Book readById(@PathVariable Integer id) {
        return bookService.findById(id);
    }
}
