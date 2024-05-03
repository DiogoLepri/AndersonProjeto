package com.example.andersonprojeto;

import java.util.HashMap;
import java.util.Map;

public class UserStorage {
    private static UserStorage instance;
    private Map<String, String> users;

    private UserStorage() {
        users = new HashMap<>();
    }

    public static UserStorage getInstance() {
        if (instance == null) {
            instance = new UserStorage();
        }
        return instance;
    }

    public Map<String, String> getUsers() {
        return users;
    }

    public boolean addUser(String crm, String password) {
        if (users.containsKey(crm)) {
            return false;
        }
        users.put(crm, password);
        return true;
    }

    public boolean authenticateUser(String crm, String password) {
        return password.equals(users.get(crm));
    }
}
