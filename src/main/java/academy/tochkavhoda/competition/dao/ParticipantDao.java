package academy.tochkavhoda.competition.dao;

import academy.tochkavhoda.competition.model.Participant;
import java.util.List;

public interface ParticipantDao {
    void save(Participant participant);
    List<Participant> getAll();
    Participant getByLogin(String login);
    void delete(String login);
}
