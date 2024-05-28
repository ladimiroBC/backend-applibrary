package com.applibrary.managementlibrary.controllers;

import com.applibrary.managementlibrary.DTO.StudentDTO;
import com.applibrary.managementlibrary.common.constants.ErrorMessagesConstants;
import com.applibrary.managementlibrary.common.constants.SuccessfullyMessagesConstants;
import com.applibrary.managementlibrary.models.Book;
import com.applibrary.managementlibrary.models.Loan;
import com.applibrary.managementlibrary.models.Student;
import com.applibrary.managementlibrary.services.BookService;
import com.applibrary.managementlibrary.services.LoanService;
import com.applibrary.managementlibrary.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("loan/")
public class LoanController {

    @Autowired
    private LoanService loanService;
    @Autowired
    private StudentService studentService;
    @Autowired
    private BookService bookService;

    @PostMapping(value = "/")
    public ResponseEntity<Loan> add(@RequestBody Loan loan) {
        Loan object = loanService.save(loan);
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Loan> delete(@PathVariable Integer id) {
        Loan object = loanService.findById(id);

        if(object != null)
            loanService.delete(id);
        else
            return new ResponseEntity<>(object, HttpStatus.INTERNAL_SERVER_ERROR);

        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @PutMapping(value = "/")
    public ResponseEntity<Loan> edit(@RequestBody Loan loan) {
        Loan object = loanService.findById(loan.getIdLoan());

        if (object != null) {
            object.setStudent(loan.getStudent());
            object.setBook(loan.getBook());
            object.setStateLoan(loan.isStateLoan());
            loanService.save(object);
        }
        else {
            return new ResponseEntity<>(object, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(object, HttpStatus.OK);
    }

    @GetMapping("/list")
    public List<Loan> read() {
        return loanService.findAll();
    }

    @GetMapping("/list/{id}")
    public Loan readById(@PathVariable Integer id) {
        return loanService.findById(id);
    }

    @GetMapping("/check-student")
    public ResponseEntity<?> checkStudent(@RequestParam("code") String studentCode) {
        Student student = studentService.findByCode(studentCode);

        if(student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorMessagesConstants.STUDENT_NOT_EXIST);
        }

        Integer studentId = student.getIdStudent();

        int numberOfLoans = studentService.countLoansByStudentId(studentId);

        if(numberOfLoans > 3) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorMessagesConstants.MAX_LOANS_EXCEEDED);
        }

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudent(student);
        studentDTO.setNumberOfLoans(numberOfLoans);

        return ResponseEntity.ok(studentDTO);
    }

    @PostMapping("/borrow-book")
    public ResponseEntity<?> borrowBook(@RequestParam("code") String studentCode,
                                        @RequestParam("registerCode") String bookCode) {
        Student student = studentService.findByCode(studentCode);
        Book book = bookService.findByCode(bookCode);

        Loan loan = new Loan();
        loan.setStateLoan(true);
        loan.setStudent(student);
        loan.setBook(book);
        loanService.save(loan);

        return ResponseEntity.ok(SuccessfullyMessagesConstants.BORROW_BOOK_SUCCESSFUL);
    }
}
