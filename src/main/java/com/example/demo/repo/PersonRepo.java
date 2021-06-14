package com.example.demo.repo;


import com.example.demo.Model.Person;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Configuration
public interface PersonRepo extends JpaRepository<Person,Long> {

    @Query(nativeQuery = true,value = "SELECT * FROM Person  WHERE person.id = 1")
    List<Person> getPersonBy();



}
