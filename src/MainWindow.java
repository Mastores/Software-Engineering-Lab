import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


import javax.swing.*;
import javax.swing.table.*;
import javax.swing.border.*;
/*import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;*/

import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.JTable;


public class MainWindow extends JFrame {

	private JPanel contentPane;
	private JPanel initPanel;
	private JPanel pnl_Employee;
	
	//Admin
	private JTextField txt_userId;
	private JTextField txt_userFirstName;
	private JTextField txt_userLastName;
	private JTextField txt_userPassword;
	private JCheckBox chb_userIsAdmin;
	
	Object[] userColumns = {"ID", "Όνομα", "Επώνυμο", "Κωδικός πρόσβασης", "Διαχειριστής"};
	DefaultTableModel usersModel = new DefaultTableModel();
	private JTable tbl_users;
	int userSelectredRow = -1;

	
	JTextField txt_roomId = new JTextField();
	JTextField txt_roomName = new JTextField();
	JTextField txt_roomHotelId = new JTextField();
	JTextField txt_roomNumber = new JTextField();
	JTextField txt_roomTypeId = new JTextField();
	Object[] roomColumns = {"ID", "Νούμερο", "Όνομα", "ID ξενοδοχείου", "ID τύπου δωματίου"};
	DefaultTableModel roomsModel = new DefaultTableModel();
	JTable tbl_rooms = new JTable();
	int roomSelectedRow = -1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1280, 720);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
	}
	
	public void appScreen() {
		removeLogScreen();
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		contentPane.add(tabbedPane, BorderLayout.CENTER);
		
		pnl_Employee = new JPanel();
		tabbedPane.addTab("Υπάλληλος", null, pnl_Employee, null);
				
		drawUserInterface(pnl_Employee);
		
		

	
		
		
	
		

	
		
		
		
		if (AboutApp.loggedUser.isAdmin) {
			JPanel pnl_Administrator = new JPanel();
			tabbedPane.addTab("Διαχειριστής", null, pnl_Administrator, null);
			
			pnl_Administrator.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow]", "[grow][][][grow][][][][][][][][][][][][][]"));			//grow keywords for JTable
			
			JPanel pnl_Users = new JPanel();
			pnl_Users.setBorder(BorderFactory.createTitledBorder("Users"));
			//pnl_Users.setPreferredSize(new Dimension(10, 10));
			pnl_Administrator.add(pnl_Users, "cell 0 0 2 3, height ::450, grow");			//min:preferred:max	+ span get 4x4 cells
			
			pnl_Users.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow]", "[][][][grow][][][][]"));
			
			
			JLabel lbl_id = new JLabel("ID");
			JLabel lbl_firstName = new JLabel("Όνομα");
			JLabel lbl_lastName = new JLabel("Επώνυμο");
			JLabel lbl_password = new JLabel("Κωδικός πρόσβασης");
			JLabel lbl_isAdmin = new JLabel("Διαχειριστής");		
			pnl_Users.add(lbl_id, "cell 0 0");
			pnl_Users.add(lbl_firstName, "cell 1 0");
			pnl_Users.add(lbl_lastName, "cell 2 0");
			pnl_Users.add(lbl_password, "cell 3 0");
			pnl_Users.add(lbl_isAdmin, "cell 4 0");
			
		
			txt_userId = new JTextField();
			pnl_Users.add(txt_userId, "cell 0 1,growx");
			txt_userId.setColumns(10);
			
			txt_userFirstName = new JTextField();
			pnl_Users.add(txt_userFirstName, "cell 1 1,growx");
			txt_userFirstName.setColumns(10);
			
			txt_userLastName = new JTextField();
			pnl_Users.add(txt_userLastName, "cell 2 1,growx");
			txt_userLastName.setColumns(10);
			
			txt_userPassword = new JTextField();
			pnl_Users.add(txt_userPassword, "cell 3 1,growx");
			txt_userPassword.setColumns(10);
			
			chb_userIsAdmin = new JCheckBox();
			pnl_Users.add(chb_userIsAdmin, "cell 4 1");
			
			tbl_users = new JTable();
			usersModel.setColumnIdentifiers(userColumns);
			JTableHeader header = tbl_users.getTableHeader();
			pnl_Users.add(header, "cell 0 2, span, growx");		//merge columns with span	
			pnl_Users.add(tbl_users, "cell 0 3 5 2, grow");			
			tbl_users.setModel(usersModel);
			
			
			//JLabel dummyLabel_1 = new JLabel();
			//pnl_Administrator.add(dummyLabel_1, "cell 4 1");
			
			
			
			
			
			tbl_users.addMouseListener(new java.awt.event.MouseAdapter() {
			    @Override
			    public void mouseClicked(java.awt.event.MouseEvent evt) {
			    	userSelectredRow = tbl_users.rowAtPoint(evt.getPoint());
			        //int col = table_2.columnAtPoint(evt.getPoint());
			        
			         if (userSelectredRow >= 0) {
			           txt_userId.setText(usersModel.getValueAt(userSelectredRow,0).toString());
			           txt_userFirstName.setText(usersModel.getValueAt(userSelectredRow,1).toString());
			           txt_userLastName.setText(usersModel.getValueAt(userSelectredRow,2).toString());
			           txt_userPassword.setText(usersModel.getValueAt(userSelectredRow,3).toString());
			           chb_userIsAdmin.setSelected(AboutApp.convStringToBoolean((usersModel.getValueAt(userSelectredRow,4).toString())));
			        }
			    }
			});
			
			JButton btn_add = new JButton("Προσθήκη");
			pnl_Users.add(btn_add, "flowx,cell 0 6,growx");
			btn_add.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					String firstName = txt_userFirstName.getText();
					String lastName = txt_userLastName.getText();
					String password = txt_userPassword.getText();
					boolean isAdmin = chb_userIsAdmin.isSelected();
					
					User user = new User(firstName, lastName, password, isAdmin);		//constructor checks for parameter errors, returns null if any error is found
					if (user != null) {
						if (Admin.addUser(user)) {
							showUsers(Admin.getUsers());		//refresh table
						} else {
							MsgBox.infoBox("Η πρόσθεση χρήστη απέτυχε", "Αποτυχία δημιουργίας χρήστη");
						}	
					}
				}
			});
			
			JButton btn_delete = new JButton("Διαγραφή");
			pnl_Users.add(btn_delete, "flowx,cell 1 6,growx");
			btn_delete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					User selectedUser = getSelectedUser();
					if (selectedUser != null){
						if (Admin.deleteUser(selectedUser)) {
							showUsers(Admin.getUsers());
						} else {
							MsgBox.infoBox("Δεν ήταν δυνατή η διαγραφή του χρήστη", "Αδυναμία διαγραφής χρήστη");
						}
					}
				}
			});
			
			JButton btn_search = new JButton("Αναζήτηση");
			pnl_Users.add(btn_search, "flowx,cell 2 6,growx");
			btn_search.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {			
					User searchUser = getUserFromFields(true);
					ArrayList<User> userResults = Admin.searchUser(searchUser);
					showUsers(userResults);				//if results are null, nothing will be displayed to the admin
				}
			});
			
			JButton btn_update = new JButton("Ανανέωση χρήστη");
			pnl_Users.add(btn_update, "flowx,cell 3 6,growx");
			btn_update.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {					
					User selectedUser = getSelectedUser();
					if (selectedUser != null){
						//int oldUserID = selectedUser.getId();
						if (Admin.updateUser(getUserFromFields(false), selectedUser)) {		//if provided User is null then the error will be shown
							showUsers(Admin.getUsers());
						} else {
							MsgBox.infoBox("Δεν ήταν δυνατή η ανανέωση του χρήστη", "Αδυναμία ανανέωσης χρήστη");
						}
					}
				}
			});
			
			JButton btn_showUsers= new JButton("Ανανέωση φόρμας");
			pnl_Users.add(btn_showUsers, "flowx,cell 4 6,growx");
			btn_showUsers.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					showUsers(Admin.getUsers());
				}
			});
			
			
			
			//JLabel dummyLabel_1 = new JLabel();
			//pnl_Administrator.add(dummyLabel_1, "cell 4 1");
			
			
			JButton btnClients = new JButton("Πελάτες");
			pnl_Administrator.add(btnClients);
			btnClients.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ClientsWindow frame = new ClientsWindow(true);
				frame.setVisible(true);
			}
		});
		
		/*
		JButton testButton1 = new JButton();
		pnl_Administrator.add(testButton1);
		testButton1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				ClientsWindow frame = new ClientsWindow(false);
				frame.setVisible(true);
			}
		});
		*/
		
			
		}		//end if user isAdmin
		
	}
	
	
	public void logScreen() {
		initPanel = initLogScreen();
		contentPane.add(initPanel);		
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	public void removeLogScreen() {
		contentPane.remove(initPanel);
		contentPane.revalidate();
		contentPane.repaint();
	}
	
	private JPanel initLogScreen() {
			initPanel = new JPanel(new MigLayout("", "[grow][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow]", "[][][][][][][][][][][][][]"));
			JLabel lblID = new JLabel("ID: ");
			JLabel lblPassword = new JLabel("Password: ");
			final JTextField txtID = new JTextField(10);				//10 digits only
			final JPasswordField txtPassword = new JPasswordField(15);
			JButton btnLogin = new JButton("Login");
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					try {
						
						//DEBUG - DEBUG - DEBUG - DEBUG - DEBUG - DEBUG - DEBUG - DEBUG - DEBUG - DEBUG - DEBUG - DEBUG - DEBUG - DEBUG - DEBUG - DEBUG 
						//txtID.setText("2346");
						//txtPassword.setText("a");
						//DEBUG - DEBUG - DEBUG - DEBUG - DEBUG - DEBUG - DEBUG - DEBUG - DEBUG - DEBUG - DEBUG - DEBUG - DEBUG - DEBUG - DEBUG - DEBUG
							
						int ID = 0;
						String strID = txtID.getText();
						String password = new String(txtPassword.getPassword());
						
						if (!strID.isEmpty() && !password.isEmpty()) {
							try {	//easy way to check for numeric values
								ID = Integer.parseInt(strID.trim());
							} catch (Exception en) {
								MsgBox.infoBox("To id πρέπει να περιέχει μόνο αριμθμητικούς χαρακτήρες", "Σφάλμα εισαγωγής στοιχείων");
							}
							
							User user = AboutApp.MySQL_Connector.logIn(ID, password);
							AboutApp.createSession(user);
							appScreen();
						} else {
							MsgBox.infoBox("Πρέπει να συμπληρώσετε όλα τα πεδία", "Σφάλμα εισαγωγής στοιχείων");
						}
																															
					} catch (Exception ex) {
						// TODO Auto-generated catch block
						MsgBox.infoBox(ex.getCause().getMessage(),
								"Σφάλμα σύνδεσης χρήστη");
					}
				}
			});
			
			initPanel.add(lblID, "cell 0 0, al 100%");
			initPanel.add(lblPassword, "cell 0 1, al 100%");	//align center <- code
			initPanel.add(txtID, "cell 1 0, growx");
			initPanel.add(txtPassword, "cell 1 1, growx");
			initPanel.add(btnLogin, "cell 1 2, right");			
			return initPanel;
		}
	
	//Admin
	private void showUsers(ArrayList<User> usersList) {	
		usersModel.setRowCount(0);			//clear JTable
			
			if (usersList != null) {
				for (int i = 0; i < usersList.size(); i++){
					   int id = usersList.get(i).getId();
					   String firstName = usersList.get(i).getFirstName();
					   String lastName = usersList.get(i).getLastName();
					   String password = usersList.get(i).getPassword();
					   boolean isAdmin = usersList.get(i).getIsAdmin();
					   
					   
					   Object[] data = {id, firstName, lastName, password, isAdmin};
		
					   usersModel.addRow(data);
				}
			}
	}
	
	private User getSelectedUser() {
		if (userSelectredRow >= 0) {
			int ID = Integer.parseInt(usersModel.getValueAt(userSelectredRow,0).toString());
			String firstName = usersModel.getValueAt(userSelectredRow,1).toString();	//txt_userFirstName.getText();
			String lastName = usersModel.getValueAt(userSelectredRow,2).toString();
			String password = usersModel.getValueAt(userSelectredRow,3).toString();
			boolean isAdmin = AboutApp.convStringToBoolean(usersModel.getValueAt(userSelectredRow,3).toString());		//chb_userIsAdmin.isSelected();
			User user = user = new User(ID, firstName, lastName, password, isAdmin);
			return user;			//could be null
		} else {
			MsgBox.infoBox("Δεν έχετε επιλέξει χρήστη", "Δεν επιλέχθηκε χρήστης");
			return null;
		}
	}
	
	private User getUserFromFields(boolean isSearch) {
		int ID = -1;
		try {
			String strId = txt_userId.getText();
			if (!strId.isEmpty()) {
				ID = Integer.parseInt(txt_userId.getText());
			}
		} catch (Exception e) {
			//error at parsing integer, stop the process
			return null;
		}
		
		String firstName = txt_userFirstName.getText();
		String lastName = txt_userLastName.getText();
		String password = txt_userPassword.getText();
		boolean isAdmin = chb_userIsAdmin.isSelected();
		if (!isSearch) {
			
			User user = new User(ID, firstName, lastName, password, isAdmin);
			return user;
		} else {
			User searchUser = new User(1, "void", "void", "void", false);			//creation of temp user, because the constructor does not allow empty fields, whereas the search function does
			searchUser.searchUser(ID, firstName, lastName, password, isAdmin);
			return searchUser;
		}
	}


	private void drawUserInterface(JPanel pnl_Employee) {
		pnl_Employee.setLayout(new MigLayout("", "[grow][grow][grow][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][][grow]", "[][][][][][][][][][][][][][][][][]"));
	
		JPanel pnl_Rooms = new JPanel();
		pnl_Rooms.setBorder(BorderFactory.createTitledBorder("Rooms"));
		pnl_Employee.add(pnl_Rooms, "cell 0 0 2 3, height 300::450, grow");			//min:preferred:max	+ span get 4x4 cells
		
		pnl_Rooms.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow]", "[][][][grow][][][][]"));
		
		
		
		JLabel lbl_id = new JLabel("ID");
		JLabel lbl_number = new JLabel("Νούμερο");
		JLabel lbl_name = new JLabel("Όνομα");
		JLabel lbl_hotelId = new JLabel("ID ξενοδοχείου");
		JLabel lbl_roomTypeId = new JLabel("ID τύπου δωματίου");		
		pnl_Rooms.add(lbl_id, "cell 0 0");
		pnl_Rooms.add(lbl_number, "cell 1 0");
		pnl_Rooms.add(lbl_name, "cell 2 0");
		pnl_Rooms.add(lbl_hotelId, "cell 3 0");
		pnl_Rooms.add(lbl_roomTypeId, "cell 4 0");
		
		txt_roomId = new JTextField();
		pnl_Rooms.add(txt_roomId, "cell 0 1,growx");
		txt_roomId.setColumns(10);
		
		txt_roomNumber = new JTextField();
		pnl_Rooms.add(txt_roomNumber, "cell 1 1,growx");
		txt_roomNumber.setColumns(10);
		
		txt_roomName = new JTextField();
		pnl_Rooms.add(txt_roomName, "cell 2 1,growx");
		txt_roomName.setColumns(10);
		
		txt_roomHotelId = new JTextField();
		pnl_Rooms.add(txt_roomHotelId, "cell 3 1,growx");
		txt_roomHotelId.setColumns(10);
		
		txt_roomTypeId = new JTextField();
		pnl_Rooms.add(txt_roomTypeId, "cell 4 1,growx");
		txt_roomTypeId.setColumns(10);
		
		roomsModel.setColumnIdentifiers(roomColumns);
		JTableHeader header = tbl_rooms.getTableHeader();
		pnl_Rooms.add(header, "cell 0 2, span, growx");		//merge columns with span	
		pnl_Rooms.add(tbl_rooms, "cell 0 3 5 2, grow, height 150:200:300");			
		tbl_rooms.setModel(roomsModel);
		
		
		//JLabel dummyLabel_1 = new JLabel();
		//pnl_Administrator.add(dummyLabel_1, "cell 4 1");
		tbl_rooms.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
		    public void mouseClicked(java.awt.event.MouseEvent evt) {
		         roomSelectedRow = tbl_rooms.rowAtPoint(evt.getPoint());
		         if (roomSelectedRow >= 0) {
		           txt_roomId.setText(roomsModel.getValueAt(roomSelectedRow,0).toString());
		           txt_roomNumber.setText(roomsModel.getValueAt(roomSelectedRow,1).toString());
		           txt_roomName.setText(roomsModel.getValueAt(roomSelectedRow,2).toString());
		           txt_roomHotelId.setText(roomsModel.getValueAt(roomSelectedRow,3).toString());
		           txt_roomTypeId.setText(roomsModel.getValueAt(roomSelectedRow,4).toString());
		        }
		    }
		});
		
		JButton btn_add = new JButton("Προσθήκη");
		pnl_Rooms.add(btn_add, "flowx,cell 0 6,growx");
		btn_add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Room room = getRoomFromFields(true);
				if (room != null) {
					if (room.add()) {
						showRooms(Room.getRooms());		//refresh table
					} else {
						MsgBox.infoBox("Η πρόσθεση δωματίου απέτυχε", "Αποτυχία δημιουργίας δωματίου");
					}	
				}	
			}
		});
		
		JButton btn_delete = new JButton("Διαγραφή");
		pnl_Rooms.add(btn_delete, "flowx,cell 1 6,growx");
		btn_delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Room selectedRoom = getSelectedRoom();
				if (selectedRoom != null){
					if (selectedRoom.delete()) {
						showRooms(Room.getRooms());
					} else {
						MsgBox.infoBox("Δεν ήταν δυνατή η διαγραφή του δωματίου", "Αδυναμία διαγραφής δωματίου");
					}
				}
			}
		});
		
		JButton btn_update = new JButton("Ανανέωση δωματίου");
		pnl_Rooms.add(btn_update, "flowx,cell 2 6,growx");
		btn_update.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {					
				Room selectedRoom = getSelectedRoom();
				if (selectedRoom != null){
					if (selectedRoom.update(getRoomFromFields(false))) {		//if provided User is null then the error will be shown
						showRooms(Room.getRooms());
					} else {
						MsgBox.infoBox("Δεν ήταν δυνατή η ανανέωση του δωματίου", "Αδυναμία ανανέωσης δωματίου");
					}
				}
			}
		});
		
		
		JButton btn_showRooms= new JButton("Ανανέωση φόρμας");
		pnl_Rooms.add(btn_showRooms, "flowx,cell 3 6,growx");
		btn_showRooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showRooms(Room.getRooms());
			}
		});
	
	}
	
	private void showRooms(ArrayList<Room> roomsList) {	
		roomsModel.setRowCount(0);			//clear JTable
			
			if (roomsList != null) {
				for (int i = 0; i < roomsList.size(); i++){
					   int id = roomsList.get(i).getId();
					   int number = roomsList.get(i).getNumber();
					   String name = roomsList.get(i).getName();
					   int hotelId = roomsList.get(i).getHotelId();
					   int roomTypeId = roomsList.get(i).getRoomTypeId();
					   
					   Object[] data = {id, number, name, hotelId, roomTypeId};
					   roomsModel.addRow(data);
				}
			}
	}
	
	private Room getSelectedRoom() {
		if (roomSelectedRow >= 0) {
			int ID, hotelId, roomTypeId, number;
			String name;
			try {
				ID = Integer.parseInt(roomsModel.getValueAt(roomSelectedRow,0).toString());
				number = Integer.parseInt(roomsModel.getValueAt(roomSelectedRow,1).toString());
				//name = roomsModel.getValueAt(roomSelectedRow,2).toString();
				//hotelId = Integer.parseInt(roomsModel.getValueAt(roomSelectedRow,3).toString());
				roomTypeId = Integer.parseInt(roomsModel.getValueAt(roomSelectedRow,4).toString());
			} catch (Exception e) {
				MsgBox.infoBox("Ξαναελέγξτε τα πεδία", "Σφάλμα στα πεδία");
				return null;
			}
			Room room = new Room(ID, number, roomTypeId);
			return room;			//could be null
		} else {
			MsgBox.infoBox("Δεν έχετε επιλέξει δωμάτιο", "Δεν επιλέχθηκε δωμάτιο");
			return null;
		}
	}
	
	private Room getRoomFromFields(boolean addRoom) {
		//auto id
		int hotelId, roomTypeId, number; 
		String name, strHotelId, strRoomTypeId, strNumber;
		
		//name = txt_roomName.getText();
		//strHotelId = txt_roomHotelId.getText();
		strNumber = txt_roomNumber.getText();
		strRoomTypeId = txt_roomTypeId.getText();
		
		try {
			if (!strNumber.isEmpty() && !strRoomTypeId.isEmpty()) {
				if (!addRoom){
					hotelId = Integer.parseInt(txt_roomHotelId.getText()); 
				} else {
					hotelId = 0;
				}
				roomTypeId = Integer.parseInt(txt_roomTypeId.getText());
				number = Integer.parseInt(txt_roomNumber.getText());
				
				Room room = new Room(hotelId, number, roomTypeId);		//constructor checks for parameter errors, returns null if any error is found
				return room;
			} else {
				return null;
			}
		} catch (Exception e) {
			MsgBox.infoBox(e.getMessage(), "");
		}
		return null;
	}

}
