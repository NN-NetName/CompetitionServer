package academy.tochkavhoda.competition.dao;

import academy.tochkavhoda.competition.model.Application;
import java.util.List;

public interface ApplicationDao {
    void save(Application application);
    List<Application> getAll();
}