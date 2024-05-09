package com.applibrary.managementlibrary.dao;

import com.applibrary.managementlibrary.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentDao extends JpaRepository<Student, Integer> {
    @Query("SELECT s FROM Student s WHERE s.code = :code")
    Student findByCode(@Param("code") String studentCode);
}
