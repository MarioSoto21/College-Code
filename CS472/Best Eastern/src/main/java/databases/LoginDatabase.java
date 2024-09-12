package databases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import objects.Login;

public class LoginDatabase {
    private List<Login> database;

    public LoginDatabase() {
        database = new ArrayList<>();
    }

    public void addLogin(Login login) {
        database.add(login);
    }

    public List<Login> getLogins() {
        return database;
    }

    public void removeLogin(int userID) {
        Iterator<Login> iterator = database.iterator();
        while (iterator.hasNext()) {
            Login login = iterator.next();
            if (login.getUserID() == userID) {
                iterator.remove();
                return;
            }
        }
    }

    public Login getLogin(int userID) {
        for (Login login : database) {
            if (login.getUserID() == userID) {
                return login;
            }
        }
        return null;
    }
}
