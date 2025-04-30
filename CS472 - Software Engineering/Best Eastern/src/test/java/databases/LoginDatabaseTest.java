package databases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import objects.Login;

public class LoginDatabaseTest {
    @Test
    public void testAddLogin() {
        LoginDatabase database = new LoginDatabase();
        Login login = new Login(1, "username", "password");
        database.addLogin(login);
        List<Login> logins = database.getLogins();
        assertTrue(logins.contains(login));
    }

    @Test
    public void testGetLogins() {
        LoginDatabase database = new LoginDatabase();
        database.addLogin(new Login(1, "user1", "pass1"));
        database.addLogin(new Login(2, "user2", "pass2"));
        List<Login> logins = database.getLogins();
        assertEquals(2, logins.size());
    }

    @Test
    public void testRemoveLogin() {
        LoginDatabase database = new LoginDatabase();
        Login login1 = new Login(1, "user1", "pass1");
        Login login2 = new Login(2, "user2", "pass2");
        database.addLogin(login1);
        database.addLogin(login2);
        database.removeLogin(1);
        assertNull(database.getLogin(1));
        assertNotNull(database.getLogin(2));
    }

    @Test
    public void testRemoveLoginDNE() {
        LoginDatabase database = new LoginDatabase();
        Login login1 = new Login(1, "user1", "pass1");
        database.addLogin(login1);
        database.removeLogin(2);
        assertNotNull(database.getLogin(1));
        assertNull(database.getLogin(2));
    }

    @Test
    public void testGetLogin() {
        LoginDatabase database = new LoginDatabase();
        Login login1 = new Login(1, "user1", "pass1");
        Login login2 = new Login(2, "user2", "pass2");
        database.addLogin(login1);
        database.addLogin(login2);
        assertEquals(login1, database.getLogin(1));
        assertEquals(login2, database.getLogin(2));
    }
}
