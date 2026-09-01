package com.example.student_api.Controller;

import com.example.student_api.Service.StudentService;
import com.example.student_api.Student;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.example.student_api.Service.StudentService;
import java.util.List;
@Controller
@RestController
@RequestMapping("/Students")
public class StudentController {
    private final StudentService service;
   public  StudentController(StudentService service){
       this.service=service;
   }
   @GetMapping
    public List<Student> getallstudents(){
       return service.Getallstudents();
   }
    @PostMapping
    public Student savestudent(@RequestBody Student student) {
        return service.savestudent(student);
    }
}
