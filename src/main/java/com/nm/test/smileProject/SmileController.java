package com.nm.test.smileProject;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class SmileController {

    private final AtomicLong counter = new AtomicLong();
    private static final Logger log = LoggerFactory.getLogger(SmileController.class);

    @GetMapping("/api/jokes")
    public JokeList getAllJokes(@RequestParam(value = "term", defaultValue = " ") String term)
    {
        JokeList newJokeList = new JokeList();

        RestTemplate restTemplate = new RestTemplate();
        JokeList jokes = restTemplate.getForObject(
                "https://icanhazdadjoke.com/search?term="+term, JokeList.class);

        List<Joke> selectedJokes = jokes.getResults();
        Collections.sort(selectedJokes);

        for (Joke joke : jokes.getResults())
        {
            //Adding self link joke 'singular' resource
            Link link = ControllerLinkBuilder
                    .linkTo(SmileController.class)
                    .slash(joke.getJokeId())
                    .withSelfRel();

            //Add link to singular resource
            joke.add(link);

            newJokeList.getResults().add(joke);
        }

        return newJokeList;
    }
}
