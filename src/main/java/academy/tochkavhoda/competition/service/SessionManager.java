package academy.tochkavhoda.competition.service;

import academy.tochkavhoda.competition.model.User;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SessionManager {

    private static SessionManager instance;
    private final Map<String, User> sessions = new HashMap<>();

    private SessionManager() { }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public String createSession(User user) {
        String token = UUID.randomUUID().toString();
        sessions.put(token, user);
        return token;
    }

    public User getUser(String token) {
        return sessions.get(token);
    }

    public void removeSession(String token) {
        sessions.remove(token);
    }
}