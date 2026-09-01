package com.example.student_api.Service;

import com.example.student_api.Repository.StudentRepository;
import com.example.student_api.Student;
import org.hibernate.integrator.spi.IntegratorService;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class StudentService {
    private StudentRepository repo;
    public StudentService(StudentRepository repo){
        this.repo=repo;
    }
    public Student savestudent(Student student ) {
        return repo.save(student);
    }
    public List<Student> Getallstudents(){
        return repo.findAll();
    }
}
