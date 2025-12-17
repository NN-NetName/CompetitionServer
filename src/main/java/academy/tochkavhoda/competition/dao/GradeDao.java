package academy.tochkavhoda.competition.dao;

import academy.tochkavhoda.competition.model.Grade;
import java.util.List;

public interface GradeDao {
    void save(Grade grade);
    List<Grade> getAll();
    void delete(String applicationId, String expertLogin);
    List<Grade> getByExpertLogin(String login);
    void deleteAllByExpert(String login);
}