package main;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by vadim on 19.09.14.
 */
public class AccountService {
    private Map<String, UserProfile> registeredUsers;
    private Map<String, UserProfile> users;

    public AccountService() {
        this.registeredUsers = new HashMap<String, UserProfile>();
        this.users = new HashMap<String, UserProfile>();
    }
}
