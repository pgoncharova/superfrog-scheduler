package edu.tcu.cs.superfrogscheduler.request;

public class RequestNotFoundException extends RuntimeException {
    public RequestNotFoundException(String id) {
        super("Could not find request with id " + id + ".");
    }
}
