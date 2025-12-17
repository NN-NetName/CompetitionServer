package academy.tochkavhoda.competition.daoimpl;

import academy.tochkavhoda.competition.dao.ApplicationDao;
import academy.tochkavhoda.competition.database.Database;
import academy.tochkavhoda.competition.model.Application;
import java.util.List;

public class ApplicationDaoImpl implements ApplicationDao {

    @Override
    public void save(Application application) {
        Database.getInstance().addApplication(application);
    }

    @Override
    public List<Application> getAll() {
        return Database.getInstance().getApplications();
    }

    @Override
    public Application getById(String id) {
        for (Application app : Database.getInstance().getApplications()) {
            if (app.getId().equals(id)) {
                return app;
            }
        }
        return null;
    }
}