package com.applibrary.managementlibrary.services;

import com.applibrary.managementlibrary.models.Student;

import java.util.List;

public interface StudentService {
    public List<Student> findAll();
    public Student save(Student student);
    public void delete(Integer id);
    public Student findById(Integer id);
    public Student findByCode(String studentCode);
    public int countLoansByStudentId(Integer id);
}
