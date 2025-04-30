package controllers;

import java.util.List;

import databases.LoginDatabase;
import objects.Login;

public class LoginController {
    private LoginDatabase loginDatabase;

    public LoginController(LoginDatabase loginDatabase) {
        this.loginDatabase = loginDatabase;
    }

    public LoginDatabase getLoginDatabase() {
        return loginDatabase;
    }

    public void addLogin(Login login) {
        loginDatabase.addLogin(login);
    }

    public void removeLogin(String username) {
        List<Login> logins = loginDatabase.getLogins();
        for (Login login : logins) {
            if (login.getUsername().equals(username)) {
                logins.remove(login);
                break;
            }
        }
    }

    public void removeLoginById(int userId) {
        List<Login> logins = loginDatabase.getLogins();
        for (Login login : logins) {
            if (login.getUserID() == userId) {
                logins.remove(login);
                break;
            }
        }
    }

    public void editLoginPassword(String username, String newPassword) {
        List<Login> logins = loginDatabase.getLogins();
        for (Login login : logins) {
            if (login.getUsername().equals(username)) {
                login.setPassword(newPassword);
                break;
            }
        }
    }

    public void editLogin(int userId, String username, String newPassword) {
        List<Login> logins = loginDatabase.getLogins();
        for (Login login : logins) {
            if (login.getUserID() == userId) {
                login.setUsername(username);
                login.setPassword(newPassword);
                break;
            }
        }
    }

    public int login(String username, String password) {
        List<Login> logins = loginDatabase.getLogins();
        for (Login login : logins) {
            if (login.getUsername().equals(username) && login.getPassword().equals(password)) {
                return login.getUserID();
            }
        }
        return 0;
    }

    public boolean createAccount(int userID, String username, String password) {
        if (!usernameExists(username)) {
            Login newLogin = new Login(userID, username, password);
            addLogin(newLogin);
            return true;
        } else {
            return false;
        }
    }

    private boolean usernameExists(String username) {
    for (Login login : loginDatabase.getLogins()) {
        if (login.getUsername().equals(username)) {
            return true;
        }
    }
    return false;
}

    public Login getLogin(int userID) {
        List<Login> logins = loginDatabase.getLogins();
        for (Login login : logins) {
            if (login.getUserID() == userID) {
                return login;
            }
        }
        return null;
    }
}
