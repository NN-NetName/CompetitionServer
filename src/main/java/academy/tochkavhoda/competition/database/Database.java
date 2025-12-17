package academy.tochkavhoda.competition.database;

import academy.tochkavhoda.competition.model.Application;
import academy.tochkavhoda.competition.model.Expert;
import academy.tochkavhoda.competition.model.Grade;
import academy.tochkavhoda.competition.model.Participant;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private static Database instance;

    private final List<Participant> participants = new ArrayList<>();
    private final List<Expert> experts = new ArrayList<>();
    private final List<Application> applications = new ArrayList<>();
    private final List<Grade> grades = new ArrayList<>();

    private Database() {
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }
    public List<Participant> getParticipants() {
        return new ArrayList<>(participants);
    }

    public void addExpert(Expert expert) {
        experts.add(expert);
    }
    public List<Expert> getExperts() {
        return new ArrayList<>(experts);
    }

    public void addApplication(Application app) {
        applications.add(app);
    }
    public List<Application> getApplications() {
        return new ArrayList<>(applications);
    }

    public void addGrade(Grade grade) {
        Grade oldGrade = null;
        for (Grade g : grades) {
            if (g.getApplicationId().equals(grade.getApplicationId()) &&
                    g.getExpertLogin().equals(grade.getExpertLogin())) {
                oldGrade = g;
                break;
            }
        }
        if (oldGrade != null) {
            grades.remove(oldGrade);
        }
        grades.add(grade);
    }

    public List<Grade> getGrades() {
        return new ArrayList<>(grades);
    }

    public void removeGrade(String applicationId, String expertLogin) {
        grades.removeIf(g -> g.getApplicationId().equals(applicationId)
                && g.getExpertLogin().equals(expertLogin));
    }
    public void removeParticipant(String login) {
        participants.removeIf(p -> p.getLogin().equals(login));
    }
    public void removeExpert(String login) {
        experts.removeIf(e -> e.getLogin().equals(login));
    }
    public void removeApplicationsByAuthor(String login) {
        applications.removeIf(app -> app.getParticipantId().equals(login));
    }
    public void removeGradesByExpert(String login) {
        grades.removeIf(g -> g.getExpertLogin().equals(login));
    }
}
