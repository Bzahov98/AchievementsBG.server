package com.bg.bzahov.achievementsBG.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @GetMapping("/greet")
    public GreetResponse greet() {
        return new GreetResponse("Hello");
    }

    record GreetResponse(String greet) {
    }


    @GetMapping("/greetNew")
    public GreetResponseWithPerson greetNew() {
        return new GreetResponseWithPerson("Hello", List.of("Kaa", "asas", "sas"), new Person("Bozhod", 25,40000));
    }

    record GreetResponseWithPerson(String hello, List<String> kaa, Person bozhod) {
    }


    record Person(String person, Integer age, double savings) {
    }
}


