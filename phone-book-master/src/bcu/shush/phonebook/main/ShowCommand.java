package bcu.shush.phonebook.main;

import bcu.shush.phonebook.model.*;

import java.io.Serializable;  // Import Serializable for handling serialisation

public class ShowCommand implements Command, Serializable {
    private static final long serialVersionUID = 1L;

    private final String name;

    public ShowCommand(String[] parts) throws InvalidCommandException {
        if (parts.length != 2) {
            throw new InvalidCommandException();
        }
        this.name = parts[1];
    }

    @Override
    public void execute(PhoneBook phoneBook) {
        try {
            PhoneBookEntry entry = phoneBook.getEntry(name.toLowerCase());
            // Print or handle the entry, for example:
            System.out.println(entry);
        } catch (NotPresentException e) {
            // Handle the case where the entry is not present, for example:
            System.out.println("Entry for " + name + " not found.");
        }
    }
}
