package academy.tochkavhoda.competition.daoimpl;

import academy.tochkavhoda.competition.dao.GradeDao;
import academy.tochkavhoda.competition.database.Database;
import academy.tochkavhoda.competition.model.Grade;

import java.util.ArrayList;
import java.util.List;

public class InMemoryGradeDao implements GradeDao {

    private final Database database;

    public InMemoryGradeDao(Database database) {
        this.database = database;
    }

    @Override
    public void save(Grade grade) {
        database.addGrade(grade);
    }

    @Override
    public List<Grade> getAll() {
        return new ArrayList<>(database.getGrades());
    }

    @Override
    public void delete(String applicationId, String expertLogin) {
        database.removeGrade(applicationId, expertLogin);
    }

    @Override
    public List<Grade> getByExpertLogin(String login) {
        List<Grade> result = new ArrayList<>();
        for (Grade g : database.getGrades()) {
            if (g.getExpert().getLogin().equals(login)) {
                result.add(g);
            }
        }
        return result;
    }

    @Override
    public void deleteAllByExpert(String login) {
        database.removeGradesByExpert(login);
    }
}