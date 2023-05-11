package transmitted;

import java.io.Serializable;

/**
 * Class for get username and password.
 */
public class User implements Serializable {
    private String login;
    private String password;

    public User(String username, String password) {
        this.login = username;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return login + ":" + password;
    }

    @Override
    public int hashCode() {
        return login.hashCode() + password.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof User) {
            User userObj = (User) obj;
            return login.equals(userObj.getLogin()) && password.equals(userObj.getPassword());
        }
        return false;
    }
}
