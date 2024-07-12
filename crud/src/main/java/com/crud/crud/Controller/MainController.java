package com.crud.crud.Controller;

import com.crud.crud.Model.Student;
import com.crud.crud.Repo.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class MainController {

    @Autowired
    StudentRepo studentRepo;

//    @CrossOrigin(origins = "http://localhost:4200")

    @PostMapping("/add")
    public Student addStudent(@RequestBody Student student){
        studentRepo.save(student);
        return student;
    }


    @GetMapping("/records")
    public List<Student> records(){
        return studentRepo.findAll();
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/record/{id}")
    public ResponseEntity<Student> getStudentById(@PathVariable("id") String id) {
        Optional<Student> student = studentRepo.findById(id);
        return ResponseEntity.ok(student.get());

    }

//    @CrossOrigin(origins = "http://localhost:4200")
    @DeleteMapping("/delete/record/{id}")
    public ResponseEntity<?> deleteStudentById(@PathVariable("id") String id) {
        studentRepo.deleteById(id);
        return ResponseEntity.ok().build();

    }

    @PutMapping("/record/update/{id}")
    public ResponseEntity<Student> updateStudentById(@PathVariable("id") String id, @RequestBody Student updatedStudent) {
        Optional<Student> studentOptional = studentRepo.findById(id);

        if (studentOptional.isPresent()) {
            Student student = studentOptional.get();
            // Update the student's properties with the new values
            student.setSName(updatedStudent.getSName());
            student.setSClass(updatedStudent.getSClass());
            // Update other properties as needed

            // Save the updated student back to the repository
            studentRepo.save(student);

            return ResponseEntity.ok(student);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
