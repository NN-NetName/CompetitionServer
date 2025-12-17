package academy.tochkavhoda.competition.daoimpl;

import academy.tochkavhoda.competition.dao.ParticipantDao;
import academy.tochkavhoda.competition.database.Database;
import academy.tochkavhoda.competition.model.Participant;

import java.util.List;

public class ParticipantDaoImpl implements ParticipantDao {

    @Override
    public void save(Participant participant) {
        Database.getInstance().addParticipant(participant);
    }

    @Override
    public List<Participant> getAll() {
        return Database.getInstance().getParticipants();
    }

    @Override
    public Participant getByLogin(String login) {
        for (Participant p : Database.getInstance().getParticipants()) {
            if (p.getLogin().equals(login)) {
                return p;
            }
        }
        return null;
    }

    @Override
    public void delete(String login) {
        Database.getInstance().removeParticipant(login);
    }
}
