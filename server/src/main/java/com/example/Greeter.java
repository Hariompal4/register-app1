package com.example;

/**
 * The Greeter class represents a simple greeting utility.
 */
public class Greeter {

    /**
     * Constructs a new Greeter object.
     */
    public Greeter() {
        // Default constructor
    }

    /**
     * Generates a greeting message.
     *
     * @param someone The name of the person to greet
     * @return A formatted greeting message
     */
    public String greet(String someone) {
        return String.format("Hello, %s!", someone);
    }
}

