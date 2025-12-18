package academy.tochkavhoda.competition.daoimpl;

import academy.tochkavhoda.competition.dao.ExpertDao;
import academy.tochkavhoda.competition.database.Database;
import academy.tochkavhoda.competition.model.Expert;
import academy.tochkavhoda.competition.model.User;

import java.util.ArrayList;
import java.util.List;

public class ExpertDaoImpl implements ExpertDao {

    @Override
    public void save(Expert expert) {
        Database.getInstance().addUser(expert);
    }

    @Override
    public List<Expert> getAll() {
        return new ArrayList<>(Database.getInstance().getExperts());
    }

    @Override
    public Expert getByLogin(String login) {
        User user = Database.getInstance().getUser(login);
        if (user instanceof Expert) {
            return (Expert) user;
        }
        return null;
    }

    @Override
    public void delete(String login) {
        Database.getInstance().removeUser(login);
    }
}
