package com.example;

import java.util.Random;

public class JokeMaker {

    private static final String[] jokes = {"I am Batman!", "Knock knock. Who's there? Nobody. Nobody who?"};

    public String make() {
        Random r = new Random();
        int p = r.nextInt(jokes.length);
        return jokes[p];
    }

}
