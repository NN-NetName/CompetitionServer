package academy.tochkavhoda.competition.database;

import academy.tochkavhoda.competition.model.Participant;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private static Database instance;

    private final List<Participant> participants = new ArrayList<>();

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
}
