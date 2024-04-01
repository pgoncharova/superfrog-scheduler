package edu.tcu.cs.superfrogscheduler.superfrog;

public class SuperfrogNotFoundException extends RuntimeException {
    public SuperfrogNotFoundException(String email) {
        super("Could not find Superfrog Student with email " + email + ".");
    }
}
