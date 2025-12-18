package academy.tochkavhoda.competition.database;

import academy.tochkavhoda.competition.model.Application;
import academy.tochkavhoda.competition.model.Expert;
import academy.tochkavhoda.competition.model.Grade;
import academy.tochkavhoda.competition.model.Participant;
import academy.tochkavhoda.competition.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Database {

    private static Database instance;

    private final Map<String, User> users = new HashMap<>();
    private final Map<String, Application> applications = new HashMap<>();
    private final Map<String, Grade> grades = new HashMap<>();

    private Database() {
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void addUser(User user) {
        users.put(user.getLogin(), user);
    }
    public User getUser(String login) {
        return users.get(login);
    }
    public void removeUser(String login) {
        users.remove(login);
    }

    public Collection<Participant> getParticipants() {
        return users.values().stream()
                .filter(u -> u instanceof Participant)
                .map(u -> (Participant) u)
                .collect(Collectors.toList());
    }

    public Collection<Expert> getExperts() {
        return users.values().stream()
                .filter(u -> u instanceof Expert)
                .map(u -> (Expert) u)
                .collect(Collectors.toList());
    }

    public void addApplication(Application app) {
        applications.put(app.getId(), app);
    }

    public Collection<Application> getApplications() {
        return new ArrayList<>(applications.values());
    }

    public Application getApplicationById(String id) {
        return applications.get(id);
    }

    public void removeApplicationsByAuthor(String login) {
        applications.values().removeIf(app -> {
            return app.getParticipant().equals(login);
        });
    }

    public void addGrade(Grade grade) {
        String key = grade.getApplication().getId() + "_" + grade.getExpert().getLogin();
        grades.put(key, grade);
    }

    public Collection<Grade> getGrades() {
        return new ArrayList<>(grades.values());
    }

    public void removeGrade(String applicationId, String expertLogin) {
        String key = applicationId + "_" + expertLogin;
        grades.remove(key);
    }
    public void removeGradesByExpert(String login) {
        grades.values().removeIf(g -> g.getExpert().getLogin().equals(login));
    }
}
