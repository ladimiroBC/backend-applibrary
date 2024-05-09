package com.applibrary.managementlibrary.dao;

import com.applibrary.managementlibrary.models.Book;
import com.applibrary.managementlibrary.models.Loan;
import com.applibrary.managementlibrary.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface BookDao extends JpaRepository<Book, Integer> {
    @Query("SELECT b FROM Book b WHERE b.registerCode = :registerCode")
    Book findByCode(@Param("registerCode") String bookCode);
}
