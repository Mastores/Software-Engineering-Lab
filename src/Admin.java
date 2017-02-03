import java.util.ArrayList;

/* Η κλάση αποτελεί σύνολο στατικών μεθόδων, καθώς η διαφοροποίηση μεταξύ Χρήστη και Διαχειριστή θα γίνεται μέσω του GUI και όχι μέσω κώδικα */

public class Admin extends User{
	
	
	public Admin(int id, String firstName, String lastName, boolean isAdmin) {
	    super(id, firstName, lastName, isAdmin);
		//this. = ;
	  }
	
	public Admin(User user) {
		super(user);
		
	}
	
	public static boolean addUser(User user) {
		if (user != null) {
		return AboutApp.MySQL_Connector.addUser(user);
		} else {
			return false;
		}
	}
	
	public static boolean deleteUser(User user) {
		if (user != null) {
			return AboutApp.MySQL_Connector.deleteUser(user);
		} else {
			return false;
		}
	}
	
	public static boolean updateUser(User newUser, User oldUser) {
				 if (newUser != null && oldUser != null) {		 
					 return AboutApp.MySQL_Connector.updateUser(newUser, oldUser);
				 } else {
					return false;
				 }							 
	}
		
	public static ArrayList<User> searchUser(User user) {
		if (user != null) {
			return AboutApp.MySQL_Connector.searchUser(user);
		} else {
			return null;
		}
	}
	
	public static ArrayList<User> getUsers() {
			return AboutApp.MySQL_Connector.getUsers();
	}
	

}
