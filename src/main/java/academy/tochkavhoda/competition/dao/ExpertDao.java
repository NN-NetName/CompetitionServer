package academy.tochkavhoda.competition.dao;

import academy.tochkavhoda.competition.model.Expert;
import java.util.List;

public interface ExpertDao {
    void save(Expert expert);
    List<Expert> getAll();
    Expert getByLogin(String login);
    void delete(String login);
}
