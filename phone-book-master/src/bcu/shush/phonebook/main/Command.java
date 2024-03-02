package bcu.shush.phonebook.main;
import bcu.shush.phonebook.model.*;

/**
 * @author id124659
 *
 */ 
public interface Command {
	public void execute (PhoneBook phoneBook) 
			throws AlreadyPresentException, NotPresentException;
	
}
