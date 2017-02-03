
public class User {
	
	private int id;
	private String firstName, lastName;
	public boolean isAdmin;
	private String password;
	
	public User(int id, String firstName, String lastName, boolean isAdmin) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.isAdmin = isAdmin;
	}
	
	public User(int id, String firstName, String lastName, String password, boolean isAdmin) {		//constructor for retrieving all users information, including password
		if (id > 0 && !firstName.isEmpty() && !lastName.isEmpty() && !password.isEmpty()) {
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.password = password;
			this.isAdmin = isAdmin;
		}
	}
	
	public User(String firstName, String lastName, String password, boolean isAdmin) {		//constructor for retrieving all users information, including password
		if (!firstName.isEmpty() && !lastName.isEmpty() && !password.isEmpty()) {
			this.firstName = firstName;
			this.lastName = lastName;
			this.password = password;
			this.isAdmin = isAdmin;
		}
	}
	
	
	public User(User user) {
		this.id = user.id;
		this.firstName = user.firstName;
		this.lastName = user.lastName;
		this.isAdmin = user.isAdmin;
	}
	
	public void searchUser(int id, String firstName, String lastName, String password, boolean isAdmin) {	
			this.id = id;
			this.firstName = firstName;
			this.lastName = lastName;
			this.password = password;
			this.isAdmin = isAdmin;
	}

	
	
	
	public int getId() {
		return this.id;
	}
	
	public String getFirstName() {
		return this.firstName;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public boolean getIsAdmin() {
		return this.isAdmin;
	}
	
	
	public static boolean getAdminStatus(int value) {
		return AboutApp.convIntToBoolean(value);
	}
}
