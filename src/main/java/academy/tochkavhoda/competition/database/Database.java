package academy.tochkavhoda.competition.database;

import academy.tochkavhoda.competition.model.Participant;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private final List<Participant> participants = new ArrayList<>();

    public void addParticipant(Participant participant) {
        participants.add(participant);
    }

    public List<Participant> getParticipants() {
        return new ArrayList<>(participants);
    }
}
