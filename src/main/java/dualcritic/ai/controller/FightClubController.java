package dualcritic.ai.controller;

import dualcritic.ai.domain.FinalReviewResult;
import dualcritic.ai.service.FightClubPromptService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FightClubController {

    private final FightClubPromptService service;

    @GetMapping
    public String index()  {
        return "Welcome to the Fight Club! This is the home of film critiques. " +
                "To get reviews, send a GET request to /reviews with the film name.";
    }

    @GetMapping("/reviews")
    @ResponseStatus(HttpStatus.CREATED)
    public FinalReviewResult generateReview(@RequestParam(name = "film", required = false,
            defaultValue = "Fight Club") String film) {

        return service.generateFinalReview(film);

    }

}
