package com.example.demo.Api;



import com.example.demo.Model.Person;
import com.example.demo.repo.PersonRepo;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class PersonApi {

    private PersonRepo personRepo;



    public PersonApi(PersonRepo personRepo) {


        this.personRepo = personRepo;
    }

    @GetMapping("/api")
    public List <Person> getPersons(){

        return personRepo.findAll() ;


    }
    @GetMapping("/api_test")
    public List <Person> getPersonBy(){

        return personRepo.getPersonBy() ;


    }

    @PostMapping ("/api")
   public Person addPerson (@RequestBody Person person) {
        System.out.println("JUPII");
        return personRepo.save(person);
    }


    public boolean deletePerson (@RequestParam Long index){

          personRepo.deleteById(index);
        return true;
        }





    @EventListener(ApplicationReadyEvent.class)
    public void addFakePerson(){
        Person person1 = new Person(1L,"Piotr","Mis");
        Person person2 = new Person(2L,"Piotr1","Mis1");

        personRepo.save(person1);
        personRepo.save(person2);




    }





}


