package edu.tcu.cs.superfrogscheduler.system.exception;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String objectName, String id) {
        super("Could not find " + objectName + " with id " + id + ".");
    }

    public ObjectNotFoundException(String objectName, Integer id) {
        super("Could not find " + objectName + " with id " + id + ".");
    }

    public ObjectNotFoundException(String objectName, Long id) {
        super("Could not find " + objectName + " with id " + id + ".");
    }
}
