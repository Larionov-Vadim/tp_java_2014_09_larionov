package main;


/**
 * Created by vadim on 19.09.14.
 */
public class UserProfile {
    private String login;
    private String password;
    private String email;

    public UserProfile(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public boolean checkPassword(String pass) {
       if (pass.equals(this.password)) {
           return true;
       } else {
           return false;
       }
    }

}
