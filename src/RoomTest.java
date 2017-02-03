import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;


public class RoomTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		try {
			AboutApp.MySQL_Connector = new MySQL_Connector("", "", "", "");		//please add credentials: host, databaseName, user, password
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetNumber() {
			Room room = new Room(0,101,1);	//create room to test getNumber()
			assertEquals("Result", 101, room.getNumber());
	}
	
	@Test
	public void testDelete() {
		Room room = new Room(100,1001,1);	//create not existent room to test delete()
		boolean result = room.delete();	//connection with DB required
		assertFalse(result);			//must return false
	}
	
	@Test
	public void testAdd() {
			Room room = new Room(0,101,1);	//create room to add
			boolean result = room.add();	//connection with DB required
			assertTrue(result);				//must return true
	}
	
	@Test
	public void testGetRooms() {
			ArrayList<Room> results = Room.getRooms();	//connection with DB required
			assertNotNull(results);						//must return all rooms
	}
	

}
