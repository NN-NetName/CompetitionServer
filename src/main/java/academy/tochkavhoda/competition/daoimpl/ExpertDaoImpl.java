package academy.tochkavhoda.competition.daoimpl;

import academy.tochkavhoda.competition.dao.ExpertDao;
import academy.tochkavhoda.competition.database.Database;
import academy.tochkavhoda.competition.model.Expert;
import java.util.List;

public class ExpertDaoImpl implements ExpertDao {

    @Override
    public void save(Expert expert) {
        Database.getInstance().addExpert(expert);
    }

    @Override
    public List<Expert> getAll() {
        return Database.getInstance().getExperts();
    }

    @Override
    public Expert getByLogin(String login) {
        for (Expert e : Database.getInstance().getExperts()) {
            if (e.getLogin().equals(login)) {
                return e;
            }
        }
        return null;
    }
}
