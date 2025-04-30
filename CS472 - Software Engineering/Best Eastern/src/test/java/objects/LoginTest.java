package objects;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LoginTest {
    @Test
    public void testGetUserID() {
        Login login = new Login(1, "username", "password");
        assertEquals(1, login.getUserID());
    }

    @Test
    public void testSetUserID() {
        Login login = new Login(1, "username", "password");
        login.setUserID(2);
        assertEquals(2, login.getUserID());
    }

    @Test
    public void testGetUsername() {
        Login login = new Login(1, "username", "password");
        assertEquals("username", login.getUsername());
    }

    @Test
    public void testSetUsername() {
        Login login = new Login(1, "username", "password");
        login.setUsername("newUsername");
        assertEquals("newUsername", login.getUsername());
    }

    @Test
    public void testGetPassword() {
        Login login = new Login(1, "username", "password");
        assertEquals("password", login.getPassword());
    }

    @Test
    public void testSetPassword() {
        Login login = new Login(1, "username", "password");
        login.setPassword("newPassword");
        assertEquals("newPassword", login.getPassword());
    }
}
