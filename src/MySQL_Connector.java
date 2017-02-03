import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Currency;

import com.mysql.jdbc.StringUtils;


public class MySQL_Connector {

	private Connection conn;
	
	public MySQL_Connector(String server, String dbName, String username, String password) throws Exception {
		this.conn = connectToDB(server, dbName, username, password);
	}
	
	public Connection connectToDB(String server, String dbName, String username, String password) throws Exception{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();					//xekina ton jdbc driver
			String connectionURL="jdbc:mysql://"+ server +":3306/" + dbName;		//3306 default port, protokollo jdbc gia MySQL					
			return DriverManager.getConnection(connectionURL,username,password);		//Import java.sql -> import java.sql.DriverManager;
		} catch (Exception e) {
			throw new Exception(e);
			//e.printStackTrace();
		}
		//return null;		
	}
	
	public User logIn(int ID, String password) throws Exception{		
		User resUser = null;
		try{		
			Statement stmt = this.conn.createStatement();		
			//There is only one admin/user with the provided ID, so we will use if (not while) when checking the returned result (rs.next())
			String temp = "select * from Users where id=" + ID + " and password='" + password + "'";
			ResultSet rs = stmt.executeQuery(temp);
			
			if (rs.next()){					//if the user exists, this block of code will be executed								
				int idx = rs.getInt("id");					
				String namex = rs.getString("firstName");			
				String surnamex = rs.getString("lastName");
				boolean isAdmin = rs.getBoolean("isAdmin");
				
				//create user/admin????
				
				resUser = new User(idx, namex, surnamex, isAdmin);
				//return true;
			} else {
				throw new Exception("Ο χρήστης δεν βρέθηκε. Παρακαλώ ελέγξτε τα στοιχεία και ξαναπροσπαθήστε.");
			}
		} catch (Exception e) {
			throw new Exception(e);
		}
		return resUser;	
	}
	
	
	
	
	
	//Client methods
	public ArrayList<Client> getClients() {
		ArrayList<Client> clientsList = new ArrayList<Client>();
		try{		
			Statement stmt = this.conn.createStatement();		
			String query = "select * from Clients";
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()){							
				int id = rs.getInt("id");					
				String firstName = rs.getString("firstName");			
				String lastName = rs.getString("lastName");
				String phoneNumber = rs.getString("phoneNumber");
				String email = rs.getString("email");
		
				//create new client and add them to the ArrayList
				clientsList.add(new Client(id, firstName, lastName, phoneNumber, email));
			}
			
			return clientsList;
		} catch (Exception e) {
			
		}
		return null;
	}
	
	public boolean addClient(String firstName, String lastName, String phoneNumber, String email) {
		try{		
			Statement stmt = this.conn.createStatement();		
			String query = "insert into Clients (firstName, lastName, phoneNumber, email) values ('" + firstName + "','" + lastName + "','" + phoneNumber + "','" + email + "')";
			int rs = stmt.executeUpdate(query);
			return AboutApp.convIntToBoolean(rs);
		} catch (Exception e) {
			
		}
		return false;
	}

	public boolean deleteClient(int id) {
		try{		
			Statement stmt = this.conn.createStatement();		
			String query = "delete from  Clients where id=" + id;
			int rs = stmt.executeUpdate(query);
			return AboutApp.convIntToBoolean(rs);
		} catch (Exception e) {
			
		}	
		return false;
	}

	public ArrayList<Client> searchClient(int id, String firstName, String lastName, String phoneNumber, String email) {
		ArrayList<Client> clientResults = new ArrayList<Client>();
			try {
				Statement stmt = this.conn.createStatement();		
				String query = "select * from Clients where";
				
				String[] queryConditions = new String[]{"", "", "", "", ""};
				if (id != -1) {
					queryConditions[0] = " id=" + id;
				}
				if (!firstName.isEmpty()) {
					queryConditions[1] = " firstName='" + firstName + "'";
				}
				if (!lastName.isEmpty()) {
					queryConditions[2] = " lastName='" + lastName + "'";
				}
				if (!phoneNumber.isEmpty()) {
					queryConditions[3] = " phoneNumber='" + phoneNumber + "'";
				}
				if (!email.isEmpty()) {
					queryConditions[4] = " email='" + email + "'";
				}
				
				for (int i = 0; i < queryConditions.length ;i++) {
					if (!queryConditions[i].isEmpty()) {
						query += queryConditions[i] + " AND ";
					}
					
					if (i == queryConditions.length - 1) {
						query = query.substring(0, query.length() - 5);			//remove last AND
					}
				}
				
				ResultSet rs = stmt.executeQuery(query);
				
				while (rs.next()){							
					int rs_id = rs.getInt("id");					
					String rs_firstName = rs.getString("firstName");			
					String rs_lastName = rs.getString("lastName");
					String rs_phoneNumber = rs.getString("phoneNumber");
					String rs_email = rs.getString("email");
			
					clientResults.add(new Client(rs_id, rs_firstName, rs_lastName, rs_phoneNumber, rs_email));
				}
			}catch (Exception e) {
			
		}
		return clientResults;
	}

	public boolean updateClient(int id, String firstName, String lastName, String phoneNumber, String email) {
			try {
				Statement stmt = this.conn.createStatement();		
				String query = "update Clients set firstName='" + firstName + "', lastName='" + lastName + "', phoneNumber='" + phoneNumber + "', email='" + email + "' where id=" + id;
				int rs = stmt.executeUpdate(query);
				
				return AboutApp.convIntToBoolean(rs);
			} catch (Exception e) {
				
			}
		return false;
	}
	
	
	
	//Users
	
	public ArrayList<User> getUsers() {
		ArrayList<User> usersList = new ArrayList<User>();
		try{		
			Statement stmt = conn.createStatement();		
			String query = "select * from Users";
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()){							
				int id = rs.getInt("id");					
				String firstName = rs.getString("firstName");			
				String lastName = rs.getString("lastName");
				String password = rs.getString("password");
				boolean isAdmin = User.getAdminStatus(rs.getInt("isAdmin"));
		
				usersList.add(new User(id, firstName, lastName, password, isAdmin));
			}
			
			return usersList;
		} catch (Exception e) {
	
		}
		return null;
	}
	
	public boolean addUser(User user) {
		try {
			Statement stmt = conn.createStatement();		
			String query = "insert into Users (firstName, lastName, password, isAdmin) values ('" + user.getFirstName() + "','" + user.getLastName() + "','" + user.getPassword() + "'," + user.getIsAdmin() + ")";
			int rs = stmt.executeUpdate(query);		
			
			return AboutApp.convIntToBoolean(rs);
		} catch (Exception e) {
			
		}
		return false;
	}

	public boolean deleteUser(User user) {
		try{		
			Statement stmt = conn.createStatement();		
			String query = "delete from Users where id=" + user.getId();
			int rs = stmt.executeUpdate(query);		
			
			return AboutApp.convIntToBoolean(rs);
		} catch (Exception e) {
			//throw new Exception(e);
			MsgBox.infoBox(e.getMessage(), "");
		}	
		return false;
	}
	
	public boolean updateUser(User newUser, User oldUser) {
		if (oldUser.getId() != -1) {
			String firstName = newUser.getFirstName();
			String lastName = newUser.getLastName();
			String password = newUser.getPassword();
			int isAdmin = (newUser.getIsAdmin()) ? 1 : 0;			// if (isAdmin) { return 1; } else { return 0; }
		
			try {
				Statement stmt = conn.createStatement();		
				String query = "update Users set firstName='" + firstName + "', lastName='" + lastName + "', password='" + password + "', isAdmin=" + isAdmin + " where id=" + oldUser.getId();
				int rs = stmt.executeUpdate(query);
				
				return AboutApp.convIntToBoolean(rs);
			} catch (Exception e) {
			
			}
		}
		return false;
	}
	
	public ArrayList<User> searchUser(User user) {
		ArrayList<User> userResults = new ArrayList<User>();
		if (user != null) {
			try {
				Statement stmt = this.conn.createStatement();		
				String query = "select * from Users where";
				
				int id = user.getId();
				String firstName = user.getFirstName();
				String lastName = user.getLastName();
				String password = user.getPassword();
				int isAdmin = (user.getIsAdmin()) ? 1 : 0;
				
				String[] queryConditions = new String[]{"", "", "", ""};
				if (id != -1) {
					queryConditions[0] = " id=" + id;
				}
				if (!firstName.isEmpty()) {
					queryConditions[1] = " firstName='" + firstName + "'";
				}
				if (!lastName.isEmpty()) {
					queryConditions[2] = " lastName='" + lastName + "'";
				}				
				if (isAdmin == 1) {				//show the admins only (shows admins and users or admins only)
					queryConditions[3] = " isAdmin=1";
				}
				
				boolean andFlag = false;
				for (int i = 0; i < queryConditions.length ;i++) {
					if (!queryConditions[i].isEmpty()) {
						query += queryConditions[i] + " AND ";
						andFlag = true;
					}
					
					if (andFlag) {
						if (i == queryConditions.length - 1) {
							query = query.substring(0, query.length() - 5);			//remove last AND
						}
					}
				}
				
				ResultSet rs = stmt.executeQuery(query);
				
				while (rs.next()){							
					int rs_id = rs.getInt("id");					
					String rs_firstName = rs.getString("firstName");			
					String rs_lastName = rs.getString("lastName");
					String rs_password = rs.getString("password");
					boolean rs_isAdmin = User.getAdminStatus(rs.getInt("isAdmin"));
			
					userResults.add(new User(rs_id, rs_firstName, rs_lastName, rs_password, rs_isAdmin));
				}
			}catch (Exception e) {
					return null;
			}
		
		}
		return userResults;
	}



	//Rooms
	public boolean addRoom(Room room) {
		try {
			Statement stmt = conn.createStatement();
			String query = "insert into Rooms (number, roomType_id) values (" + room.getNumber() + "," + room.getRoomTypeId() + ")";
			int rs = stmt.executeUpdate(query);		
			return AboutApp.convIntToBoolean(rs);
		} catch (SQLException e) {
			
		}		
		return false;
	}

	public ArrayList<Room> getRooms() {
		ArrayList<Room> roomsList = new ArrayList<Room>();
		try{		
			Statement stmt = conn.createStatement();		
			String query = "select * from Rooms inner join RoomTypes on Rooms.roomType_id = RoomTypes.id";
			ResultSet rs = stmt.executeQuery(query);
			
			while (rs.next()){							
				int id = rs.getInt("id");					
				String name = rs.getString("name");	
				int number = rs.getInt("number");
				int hotelId = Integer.parseInt(rs.getString("hotel_id"));
				int roomTypeId = Integer.parseInt(rs.getString("roomType_id"));
		
				roomsList.add(new Room(id, number, name, hotelId, roomTypeId));
			}
			
			return roomsList;
		} catch (Exception e) {
	
		}
		return null;
	}

	public boolean deleteRoom(Room room) {
		try{		
			Statement stmt = conn.createStatement();		
			String query = "delete from Rooms where id=" + room.getId();
			int rs = stmt.executeUpdate(query);		
			return AboutApp.convIntToBoolean(rs);
		} catch (Exception e) {
			
		}	
		return false;
	}

	public boolean updateRoom(Room oldRoom, Room newRoom) {
		int Id = oldRoom.getId();
		
		String name = newRoom.getName();
		int number = newRoom.getNumber();
		int hotelId = newRoom.getHotelId();
		int roomTypeId = newRoom.getRoomTypeId();
		
		try {
			Statement stmt = conn.createStatement();		
			String query = "update Rooms set number=" + number + ", roomType_id=" + roomTypeId + " where id=" + Id;
			int rs = stmt.executeUpdate(query);
			
			return AboutApp.convIntToBoolean(rs);
		} catch (Exception e) {
		
		}
		return false;
	}
	
	
}
