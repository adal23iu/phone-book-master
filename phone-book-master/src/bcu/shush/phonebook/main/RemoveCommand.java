package bcu.shush.phonebook.main;

import bcu.shush.phonebook.model.NotPresentException;
import bcu.shush.phonebook.model.PhoneBook;
import bcu.shush.phonebook.model.AlreadyPresentException;

public class RemoveCommand implements Command {
    private final String name;

    public RemoveCommand(String[] parts) throws InvalidCommandException {
        if (parts.length != 2) {
            throw new InvalidCommandException();
        }
        this.name = parts[1];
    }

    @Override
    public void execute(PhoneBook phoneBook) throws NotPresentException, AlreadyPresentException {
        phoneBook.removeEntry(name.toLowerCase());
		System.out.println("Entry removed.");
    }
}
