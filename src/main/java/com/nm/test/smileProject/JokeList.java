package com.nm.test.smileProject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JokeList extends ResourceSupport {

    private List<Joke> results;

    public JokeList() {
        results = new ArrayList<>();
    }

    public List<Joke> getResults() {
        return results;
    }

    public void setResults(List<Joke> results) {
        this.results = results;
    }
}
