package ajbc.doodle.calendar.entities;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class UserTest {
	
	private final Integer USER_ID = null;
	private final String FIRST_NAME = "Shelly";
	private final String LAST_NAME = "Foran";
	private final String EMAIL = "foranshelly@gmail.com";
	private final LocalDateTime BIRTH_DATE = LocalDateTime.of(1991, 11, 04, 00, 00);
	private final LocalDateTime JOIN_DATE = LocalDateTime.now();
	private final String END_POINT = null;
	private final String P256DH = null;
	private final String AUTH = null;
	private final boolean IS_DELETED = false;
	private final boolean IS_LOGGED_IN = false;

	private User user;

	public UserTest() {
		user = new User( FIRST_NAME, LAST_NAME, EMAIL, BIRTH_DATE, JOIN_DATE);
	}
	
//test getters
	@Test
	void testGetUserId() {
		assertEquals(USER_ID, user.getUserId());
	}
	
	@Test
	void testGetFirstName() {
		assertEquals(FIRST_NAME, user.getFirstName());
	}
	
	@Test
	void testGetLastName() {
		assertEquals(LAST_NAME, user.getLastName());
	}
	
	@Test
	void testGetEmail() {
		assertEquals(EMAIL, user.getEmail());
	}
	
	@Test
	void testGetBirthDate() {
		assertEquals(BIRTH_DATE, user.getBirthDate());
	}
	
	@Test
	void testGetJoinDate() {
		assertEquals(JOIN_DATE, user.getJoinDate());
	}
	
	@Test
	void testIsDeleted() {
		assertEquals(IS_DELETED, user.isDeleted());
	}
	
	@Test
	void testIsLoggedIn() {
		assertEquals(IS_LOGGED_IN, user.isLoggedin());
	}
	
	@Test
	void testGetEndPoint() {
		assertEquals(END_POINT, user.getEndPoint());
	}

	@Test
	void testGetP256dh() {
		assertEquals(P256DH, user.getP256dh());
	}
	
	@Test
	void testGetAuth() {
		assertEquals(AUTH, user.getAuth());
	}

//test setters
	

}
