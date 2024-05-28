package com.applibrary.managementlibrary.services.implement;

import com.applibrary.managementlibrary.dao.LoanDao;
import com.applibrary.managementlibrary.dao.StudentDao;
import com.applibrary.managementlibrary.models.Book;
import com.applibrary.managementlibrary.models.Loan;
import com.applibrary.managementlibrary.models.Student;
import com.applibrary.managementlibrary.services.LoanService;
import com.applibrary.managementlibrary.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class LoanServiceImplement implements LoanService {

    @Autowired
    private LoanDao loanDao;
    @Autowired
    private StudentService studentService;

    @Transactional(readOnly = false)
    public List<Loan> findAll() {
        return (List<Loan>) loanDao.findAll();
    }

    @Transactional(readOnly = false)
    public Loan save(Loan loan) {
        return loanDao.save(loan);
    }

    @Transactional(readOnly = false)
    public void delete(Integer id) {
        loanDao.deleteById(id);
    }

    @Transactional(readOnly = false)
    public Loan findById(Integer id) {
        return loanDao.findById(id).orElse(null);
    }

    @Override
    public int countLoansByStudentCode(String studentCode) {
        Student student = studentService.findByCode(studentCode);

        if (student == null) {
            return 0;
        }

        return loanDao.countByStudentId(student.getIdStudent());
    }

    @Override
    public boolean isBookBorrowed(Integer bookId) {
        return loanDao.existsByBookIdAndStateLoanTrue(bookId);
    }
}
