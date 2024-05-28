package com.applibrary.managementlibrary.dao;

import com.applibrary.managementlibrary.models.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LoanDao extends JpaRepository<Loan, Integer> {
    @Query("SELECT COUNT(l) FROM Loan l WHERE l.student.idStudent = :idStudent")
    int countByStudentId(@Param("idStudent") Integer id);

    @Query("SELECT CASE WHEN COUNT(l) > 0 THEN TRUE ELSE FALSE END FROM Loan l WHERE l.book.idBook = :bookId AND l.stateLoan = TRUE")
    boolean existsByBookIdAndStateLoanTrue(@Param("bookId") Integer bookId);
}
