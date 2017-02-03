import java.util.ArrayList;


public class Client {

	private int id;		//auto-increment
	private String firstName, lastName, phoneNumber, email;
	
	
	public Client(int id, String firstName, String lastName, String phoneNumber, String email) {		//creates Client objects, for return purposes
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}
	
	
	public static ArrayList<Client> getClients() {
		return AboutApp.MySQL_Connector.getClients();
	}
	
	public static boolean addClient(String firstName, String lastName, String phoneNumber, String email) {
		if (!firstName.isEmpty() && !lastName.isEmpty() && !phoneNumber.isEmpty() && !email.isEmpty()) {	
			return AboutApp.MySQL_Connector.addClient(firstName, lastName, phoneNumber, email);			//try handles duplicate records	!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! action
		} else {
			return false;
		}
	}
	
	public static boolean deleteClient(int id) {
		if (id >= 0) {
			return AboutApp.MySQL_Connector.deleteClient(id);
		} else {
			return false;
		}
	}
	
	public static boolean updateClient(int id, String firstName, String lastName, String phoneNumber, String email) {
		if (id >= 0 && !firstName.isEmpty() && !lastName.isEmpty() && !phoneNumber.isEmpty() && !email.isEmpty()) {	 
			return AboutApp.MySQL_Connector.updateClient(id, firstName, lastName, phoneNumber, email);
		} else {
			return false;
		}				
	}
	
	public static ArrayList<Client> searchClient(int id, String firstName, String lastName, String phoneNumber, String email) {
		if (id > -1 || !firstName.isEmpty() || !lastName.isEmpty() || !phoneNumber.isEmpty() || !email.isEmpty()) {
			return AboutApp.MySQL_Connector.searchClient(id, firstName, lastName, phoneNumber, email);	
		} else {
			return null;
		}
	}
	
	//all getters
	public int getID() {
		return this.id;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getPhoneNumber() {
		return this.phoneNumber;
	}
	
	public String getEmail() {
		return this.email;
	}
}
