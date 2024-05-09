package com.applibrary.managementlibrary.controllers;

import com.applibrary.managementlibrary.models.Book;
import com.applibrary.managementlibrary.models.Student;
import com.applibrary.managementlibrary.services.LoanService;
import com.applibrary.managementlibrary.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("student/")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private LoanService loanService;

    @PostMapping(value = "/")
    public ResponseEntity<Student> add(@RequestBody Student student) {
        Student object = studentService.save(student);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Student> delete(@PathVariable Integer id) {
        Student object = studentService.findById(id);

        if(object != null)
            studentService.delete(id);
        else
            return new ResponseEntity<>(object, HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @PutMapping(value = "/")
    public ResponseEntity<Student> edit(@RequestBody Student student) {
        Student object = studentService.findById(student.getIdStudent());

        if (object != null) {
            object.setName(student.getName());
            object.setCode(student.getCode());
            object.setEmail(student.getEmail());
            object.setCollegeCareer(student.getCollegeCareer());
            object.setStateStudent(student.isStateStudent());
            studentService.save(object);
        }
        else {
            return new ResponseEntity<>(object, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @GetMapping("/list")
    public List<Student> read() {
        return studentService.findAll();
    }

    @GetMapping("/list/{id}")
    public Student readById(@PathVariable Integer id) {
        return studentService.findById(id);
    }
}
