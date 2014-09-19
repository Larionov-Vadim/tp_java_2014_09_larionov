package main;

import sun.security.util.Password;

/**
 * Created by vadim on 19.09.14.
 */
public class UserProfile {
    private String login;
    private String email;
    private Password password;

    public UserProfile(String login, String email, Password password) {
        this.login = login;
        this.email = email;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getEmail() {
        return email;
    }

    public Password getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(Password password) {
        this.password = password;
    }







    public boolean checkPassword(String password) {
        return true;
    }
}
