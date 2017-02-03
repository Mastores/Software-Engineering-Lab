import java.util.ArrayList;


public class Room { // NOPMD by kleon on 30/1/2017 8:42 μμ
	private int roomId, hotelId, roomTypeId, number;
	/**
	 * Holds the real part of this complex number. 
	 */
	private String name;

	public Room(int number, String name, int hotelId, int roomTypeId) {
		final String strID = String.valueOf(hotelId) + String.valueOf(roomTypeId);
		this.roomId = Integer.parseInt(strID);
		this.name = name;
		this.number = number;
		this.hotelId = hotelId;
		this.roomTypeId = roomTypeId;
	}
	
	public Room(int roomId, int number, int roomTypeId) {
		this.roomId = roomId;
		this.number = number;
		this.roomTypeId = roomTypeId;
	}
	
	public Room(int roomId, int number, String name, int hotelId, int roomTypeId) {
		this.roomId = roomId;
		this.name = name;
		this.number = number;
		this.hotelId = hotelId;
		this.roomTypeId = roomTypeId;
	}
	
	
	public int getId() {
		return this.roomId;
	}
	
	public int getNumber() {
		return this.number;
	}
	
	public String getName(){
		return this.name;
	}
	
	public int getHotelId() {
		return this.hotelId;
	}
	
	public int getRoomTypeId() {
		return this.roomTypeId;
	}

	public boolean add() {
		return AboutApp.MySQL_Connector.addRoom(this);
	}

	public static ArrayList<Room> getRooms() {
		return AboutApp.MySQL_Connector.getRooms();
	}

	public boolean delete() {
		return AboutApp.MySQL_Connector.deleteRoom(this);
	}

	public boolean update(Room newRoom) {
		return AboutApp.MySQL_Connector.updateRoom(this, newRoom);
	}
	
	
}
