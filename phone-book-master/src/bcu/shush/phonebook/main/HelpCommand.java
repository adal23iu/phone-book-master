package bcu.shush.phonebook.main;

import bcu.shush.phonebook.model.*;

public class HelpCommand implements Command {
    @Override
    public void execute(PhoneBook phoneBook) {
        System.out.println(Main.HELP_MESSAGE);
    }
}
