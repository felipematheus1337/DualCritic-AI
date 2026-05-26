package dualcritic.ai.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FightClubController {

    @GetMapping
    public String index()  {
        return "Welcome to the Fight Club! This is the home of film critiques. " +
                "To get reviews, send a GET request to /reviews with the film name.";
    }

}
