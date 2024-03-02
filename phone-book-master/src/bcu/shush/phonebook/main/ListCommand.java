package bcu.shush.phonebook.main;

import bcu.shush.phonebook.model.*;

public class ListCommand implements Command {
    @Override
    public void execute(PhoneBook phoneBook) {
        phoneBook.listEntries();
    }
}
