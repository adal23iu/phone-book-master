package bcu.shush.phonebook.model;

import java.io.Serializable;

/*
 * This exception is thrown when trying to get, update, or remove an entry from
 * the phone book, but no entry of that name exists.
 */
public class NotPresentException extends Exception implements Serializable {
    private static final long serialVersionUID = 1L; // Example value, you can choose any long value

    private final String name;

    public NotPresentException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

