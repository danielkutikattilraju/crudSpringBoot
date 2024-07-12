package com.crud.crud.Repo;

import com.crud.crud.Model.Student;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface StudentRepo extends MongoRepository<Student,String> {
}
