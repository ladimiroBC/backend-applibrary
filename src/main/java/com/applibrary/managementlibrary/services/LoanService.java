package com.applibrary.managementlibrary.services;

import com.applibrary.managementlibrary.models.Book;
import com.applibrary.managementlibrary.models.Loan;
import com.applibrary.managementlibrary.models.Student;

import java.util.List;

public interface LoanService {
    public List<Loan> findAll();
    public Loan save(Loan loan);
    public void delete(Integer id);
    public Loan findById(Integer id);
    public int countLoansByStudentCode(String studentCode);
}
