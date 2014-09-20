package main;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vadim on 19.09.14.
 */

public class AccountService {
    private Map<String, UserProfile> sessions;  // Запущенные на сервере сессии
    private Map<String, UserProfile> users;     // Зарегистрированные пользователи

    public AccountService() {
        this.sessions = new HashMap<String, UserProfile>();
        this.users = new HashMap<String, UserProfile>();
        }

        public UserProfile getUser(String login) {
            return users.get(login);
        }

    // Количество зарегистрированных пользователей
    public String getCountUsers() {
        return String.valueOf(users.size());
    }

    public String getLogin(String sessionId) {
        UserProfile user = sessions.get(sessionId);
        if (user != null)
            return user.getLogin();
        else
            return null;
    }

    // Проверка на существование логина обеспечивает его уникальность
    // true - логин занят; false - логин свободен
    private boolean isExistLogin(String login) {
        UserProfile user = users.get(login);
        if (user == null)
            return false;
        else
            return true;
    }

    public boolean isLogged(String login, String sessionId, UserProfile user) {
        if (sessions.get(sessionId) == null)
            return false;
        if (sessions.get(sessionId).getLogin() != login)
            return false;
        return true;
    }

    // Вход
    public boolean signIn(String login, String password, String sessionId) {
        UserProfile user = users.get(login);

        // Если пользователь не зарегистрирован
        if (user == null)
            return false;

        // Если пользователь уже залогинен
        if (isLogged(login, sessionId, user)) {
            return false;
        }

        if (user.checkPassword(password)) {
            sessions.put(sessionId, user);
            return true;
        }
        return false;
    }

    // Регистрация
    public boolean singUp(String login, String password, String email) {
        if (login.length() == 0 || password.length() == 0 || email.length() == 0)
            return false;

        if (isExistLogin(login))
            return false;

        UserProfile user = new UserProfile(login, password, email);
        users.put(login, user);
        return true;
    }

    // Logout
    public void logout(String sessionId) {
        sessions.remove(sessionId);
    }

}
