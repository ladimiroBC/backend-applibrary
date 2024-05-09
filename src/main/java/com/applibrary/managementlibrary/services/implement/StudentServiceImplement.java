package com.applibrary.managementlibrary.services.implement;

import com.applibrary.managementlibrary.dao.LoanDao;
import com.applibrary.managementlibrary.dao.StudentDao;
import com.applibrary.managementlibrary.models.Student;
import com.applibrary.managementlibrary.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImplement implements StudentService {

    @Autowired
    private StudentDao studentDao;
    @Autowired
    private LoanDao loanDao;

    @Transactional(readOnly = false)
    public List<Student> findAll() {
        return (List<Student>) studentDao.findAll();
    }

    @Transactional(readOnly = false)
    public Student save(Student student) {
        return studentDao.save(student);
    }

    @Transactional(readOnly = false)
    public void delete(Integer id) {
        studentDao.deleteById(id);
    }

    @Transactional(readOnly = false)
    public Student findById(Integer id) {
        return studentDao.findById(id).orElse(null);
    }

    @Override
    public Student findByCode(String studentCode) {
        return studentDao.findByCode(studentCode);
    }

    @Override
    public int countLoansByStudentId(Integer id) {
        return loanDao.countByStudentId(id);
    }
}
