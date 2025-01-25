package com.project.LearningNavigator.service;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class EasterEggService {
    
    public String getNumberFact(int number)
    {
        final String url = "http://numbersapi.com/"+String.valueOf(number);
        RestTemplate restTemplate = new RestTemplate();
        try{
            String fact= restTemplate.getForObject(url,String.class);
            return "🎉 Easter Egg! Here's a fun fact: " + fact;
        }
        catch(Exception e)
        {
            return "Oops! Couldn't fetch a random number fact. Try again later.";
        }

    }
}
