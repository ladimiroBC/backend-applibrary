package com.applibrary.managementlibrary.DTO;

import com.applibrary.managementlibrary.models.Student;

public class StudentDTO {
    private Student student;
    private int numberOfLoans;
    public StudentDTO() { }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public int getNumberOfLoans() {
        return numberOfLoans;
    }

    public void setNumberOfLoans(int numberOfLoans) {
        this.numberOfLoans = numberOfLoans;
    }
}
