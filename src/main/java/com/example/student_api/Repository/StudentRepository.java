package com.example.student_api.Repository;

import com.example.student_api.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository  extends JpaRepository<Student,Long> {
}
