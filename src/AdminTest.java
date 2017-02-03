import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.BeforeClass;
import org.junit.Test;


public class AdminTest {

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
	public void testAddUser() {
		User user = new User("fn","ln","ps",false);		//create user to add
		boolean result = Admin.addUser(user);			//connection with DB required
		assertTrue(result);								//must return true
		
		
		//check getters
		assertEquals("Result_fn", "fn", user.getFirstName());		//firstName
		assertEquals("Result_ln", "ln", user.getLastName());		//lastName
		assertEquals("Result_ps", "ps", user.getPassword());		//password
		assertFalse(user.getIsAdmin());		//isAdmin
	}

	@Test
	public void testSearchUserUser() {
		User searchUser = new User(1, "void", "void", "void", false);			//search user 
		searchUser.searchUser(-1, "fn", "ln", "ps", false);						//change search parameters	
		ArrayList<User> results = Admin.searchUser(searchUser);
		assertEquals("Result_fn", "fn", results.get(0).getFirstName());			//firstName check
		assertNotNull(results);								//must return true, user exists
	}
	
	@Test
	public void testUpdateUser() {
		User searchUser = new User(1, "void", "void", "void", false);			//search user 
		searchUser.searchUser(-1, "fn", "ln", "ps", false);						//change search parameters	
		ArrayList<User> results = Admin.searchUser(searchUser);
		if (results != null) {							//delete first result
			User oldUser = new User(results.get(0));
			int oldId = oldUser.getId();				
			User newUser = new User(oldId, "ff", "ll", "pp", true);
			boolean result = Admin.updateUser(newUser, oldUser);
			assertTrue(result);
		}
	}
	
	@Test
	public void testDeleteUser() {												//search user must exists before running this test
		User searchUser = new User(1, "void", "void", "void", false);			//search user 
		searchUser.searchUser(-1, "ff", "ll", "pp", true);						//change search parameters	
		ArrayList<User> results = Admin.searchUser(searchUser);
		if (results != null) {							//delete first result
			boolean result = Admin.deleteUser(results.get(0));
			assertTrue(result);
		}
	}


	@Test
	public void testGetUsers() {
		ArrayList<User> results = Admin.getUsers();
		assertNotNull(results);		
	}

}
