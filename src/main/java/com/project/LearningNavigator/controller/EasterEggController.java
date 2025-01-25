package com.project.LearningNavigator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.project.LearningNavigator.service.EasterEggService;

@RestController
public class EasterEggController {
    
    @Autowired
    public EasterEggService numberService;
    @GetMapping("/hidden-feature/{number}")
    public ResponseEntity<?> getRandomNumberFact(@PathVariable int number){
        String numberFact=numberService.getNumberFact(number);
        return ResponseEntity.ok(numberFact);
    }
}
