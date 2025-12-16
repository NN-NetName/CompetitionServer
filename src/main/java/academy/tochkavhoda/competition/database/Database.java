package academy.tochkavhoda.competition.database;

import academy.tochkavhoda.competition.model.Application;
import academy.tochkavhoda.competition.model.Expert;
import academy.tochkavhoda.competition.model.Participant;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private static Database instance;

    private final List<Participant> participants = new ArrayList<>();
    private final List<Expert> experts = new ArrayList<>();
    private final List<Application> applications = new ArrayList<>();

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
}
