
public final class AboutApp {						//this class contains read only information about the application. No data will be changed during execution
													//Thus, all methods will be static
	public static String getAppName(){
		return "Mastoria Deluxe";
	}
	
	public static User loggedUser;
	public static MySQL_Connector MySQL_Connector;
	
	
	public static void createSession(User user) {
		if (user.isAdmin) {
			loggedUser = new Admin (user);
		} else {
			loggedUser = user;
		}
		
		//User workingUser = new Admin(user);
		//Admin m = (Admin) a;
		//MsgBox.infoBox(loggedUser, titleBar)
	}
	
	
	public static boolean convIntToBoolean(int value) {
		if ( value == 1 ) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean convStringToBoolean(String value) {
		if ( value == "true" ) {
			return true;
		} else {
			return false;
		}
	}
	
	public static int stringToInteger (String value) {
		try {
			return Integer.parseInt(value);
		} catch (Exception e) {
			return -1;
		}
	}
	
	
	
	
	
	
}
