package academy.tochkavhoda.competition.daoimpl;

import academy.tochkavhoda.competition.dao.ParticipantDao;
import academy.tochkavhoda.competition.database.Database;
import academy.tochkavhoda.competition.model.Participant;
import academy.tochkavhoda.competition.model.User;

import java.util.ArrayList;
import java.util.List;

public class ParticipantDaoImpl implements ParticipantDao {

    @Override
    public void save(Participant participant) {
        Database.getInstance().addUser(participant);
    }

    @Override
    public List<Participant> getAll() {
        return new ArrayList<>(Database.getInstance().getParticipants());
    }

    @Override
    public Participant getByLogin(String login) {
        User user = Database.getInstance().getUser(login);
        if (user instanceof Participant) {
            return (Participant) user;
        }
        return null;
    }

    @Override
    public void delete(String login) {
        Database.getInstance().removeUser(login);
    }
}
