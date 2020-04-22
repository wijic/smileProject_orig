package com.nm.test.smileProject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Joke extends ResourceSupport implements Comparable < Joke > {

    @JsonProperty("id")
    private String jokeId;
    private String joke;

    public Joke(@JsonProperty("id") String id, @JsonProperty("joke") String joke) {
        this.jokeId = id;
        this.joke = joke;
    }

    public String getJokeId() {
        return jokeId;
    }

    public void setJokeId(String jokeId) {
        this.jokeId = jokeId;
    }

    public String getJoke() {
        return joke;
    }

    public void setJoke(String joke) {
        this.joke = joke;
    }

    @Override
    public int compareTo(Joke j) {
        return this.getJoke().compareToIgnoreCase(j.getJoke());
    }
}
