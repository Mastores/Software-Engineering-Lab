import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class App {	
	static MainWindow mainWindow;
	static JPanel initPanel;
	static JTextField txtID;
	static JTextField txtPassword;
	
	
	public static void main(String[] args){
		mainWindow = new MainWindow();
		mainWindow.setSize(1280, 720);
		mainWindow.setLocationRelativeTo(null);			//show frame in the center of the screen
		mainWindow.setTitle(AboutApp.getAppName());
		//mainWindow.pack();								//sizes the frame so that all its contents are at or above their preferred sizes. Alternative setSize()
		
		mainWindow.setVisible(true);
		mainWindow.logScreen();

		
		//Connect To DB, throws exception if failed
		try {
			AboutApp.MySQL_Connector = new MySQL_Connector("", "", "", "");		//please add credentials: host, databaseName, user, password
		} catch (Exception e) {
			MsgBox.infoBox("Αποτυχία σύνδεσης με τη βάση δεδομένων. Βεβαιωθείτε ότι τα διαπηστευτήρια σύνδεσης με τη βάση δεδομένων είναι σωστά, " +
							"επικοινωνώντας με τον διαχειριστή του συστήματος\n\n" +
							"Μήνυμα σφάλματος:\n" + e.getMessage(),
							"Σφάλμα βάσης δεδομένων");
		
		}
		
		
		
		
	}
	
}
