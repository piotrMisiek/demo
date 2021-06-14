package com.example.demo;

import com.example.demo.Model.Person;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class start {
    public static void main(String[] args) {


    }

    List<Person> personList = new ArrayList<>();

    Random random = new Random();



    @EventListener(ApplicationReadyEvent.class)
    public void addFakePerson(){
        personList.add(new Person(1L,"Piotr","Mis"));
        personList.add(new Person(2L, "Piotr1", "Mis1"));



        }
}
