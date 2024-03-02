package bcu.shush.phonebook.main;

import bcu.shush.phonebook.model.AlreadyPresentException;
import bcu.shush.phonebook.model.NotPresentException;
import bcu.shush.phonebook.model.PhoneBook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static final String HELP_MESSAGE = "Commands:\n" +
            "    add [name] [phoneNumber]        add a new entry\n" +
            "    show [name]                     show an entry\n" +
            "    update [name] [phoneNumber]     update an entry\n" +
            "    remove [name]                   remove an entry\n" +
            "    list                            show all names\n" +
            "    help                            show this help message\n" +
            "    exit                            exit the program";

    public static void main(String[] args) throws IOException {
        new Main().run();
    }

    private final PhoneBook phoneBook;

    public Main() {
        this.phoneBook = new PhoneBook();
    }

    public Main(PhoneBook phoneBook) {
        this.phoneBook = phoneBook;
    }

    public void run() throws IOException {
        BufferedReader keyboard = new BufferedReader(
                new InputStreamReader(System.in)
        );

        System.out.println("Address book");
        while (true) {
            System.out.print("> ");
            String command = keyboard.readLine();
            if ("exit".equalsIgnoreCase(command)) {
                break;
            }

            try {
                parseAndExecute(command);
            } catch (InvalidCommandException ex) {
                System.out.println("Invalid command (enter 'help' to see the valid commands).");
            } catch (AlreadyPresentException ex) {
                System.out.println("Error: The entry for " + ex.getName() + " already exists.");
            } catch (NotPresentException ex) {
                System.out.println("Error: The entry for " + ex.getName() + " does not exist.");
            } catch (Exception ex) {
                System.out.println("An unexpected error occurred.");
                ex.printStackTrace(); // Print the stack trace for debugging
            }
        }
    }

    public Command parse(String command) throws AlreadyPresentException, NotPresentException, InvalidCommandException {
        String[] parts = command.split(" ");
        if (parts.length < 1) {
            throw new InvalidCommandException();
        }

        String firstPart = parts[0];
        if ("add".equalsIgnoreCase(firstPart)) {
            return new AddCommand(parts);
        } else if ("show".equalsIgnoreCase(firstPart)) {
            return new ShowCommand(parts);
        } else if ("update".equalsIgnoreCase(firstPart)) {
            return new UpdateCommand(parts);
        } else if ("remove".equalsIgnoreCase(firstPart)) {
            return new RemoveCommand(parts);
        } else if ("list".equalsIgnoreCase(firstPart)) {
            return new ListCommand();
        } else if ("help".equalsIgnoreCase(firstPart)) {
            System.out.println(HELP_MESSAGE);
            return new HelpCommand();
        } else {
            throw new InvalidCommandException();
        }
    }

    public void parseAndExecute(String command) throws AlreadyPresentException, NotPresentException, InvalidCommandException {
        try {
            Command parsedCommand = parse(command);
            parsedCommand.execute(phoneBook);
            System.out.println("Command executed successfully.");
        } catch (InvalidCommandException ex) {
            System.out.println("Invalid command (enter 'help' to see the valid commands).");
        } catch (AlreadyPresentException ex) {
            System.out.println("Error: The entry for " + ex.getName() + " already exists.");
        } catch (NotPresentException ex) {
            System.out.println("Error: The entry for " + ex.getName() + " does not exist.");
        } catch (Exception ex) {
            System.out.println("An unexpected error occurred.");
            ex.printStackTrace(); // Print the stack trace for debugging
        }
    }
}
