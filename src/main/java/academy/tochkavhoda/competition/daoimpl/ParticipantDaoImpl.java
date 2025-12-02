package academy.tochkavhoda.competition.daoimpl;

import academy.tochkavhoda.competition.dao.ParticipantDao;
import academy.tochkavhoda.competition.database.Database;
import academy.tochkavhoda.competition.model.Participant;

import java.util.List;

public class ParticipantDaoImpl implements ParticipantDao {

    private final Database database;

    public ParticipantDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public void save(Participant participant) {
        database.addParticipant(participant);
    }

    @Override
    public List<Participant> getAll() {
        return database.getParticipants();
    }
}
