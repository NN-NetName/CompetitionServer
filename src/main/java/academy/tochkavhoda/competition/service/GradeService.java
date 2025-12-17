package academy.tochkavhoda.competition.service;

import academy.tochkavhoda.competition.dao.ApplicationDao;
import academy.tochkavhoda.competition.dao.GradeDao;
import academy.tochkavhoda.competition.dto.request.DeleteGradeRequest;
import academy.tochkavhoda.competition.dto.request.SetGradeRequest;
import academy.tochkavhoda.competition.model.Application;
import academy.tochkavhoda.competition.model.Expert;
import academy.tochkavhoda.competition.model.Grade;
import academy.tochkavhoda.competition.model.User;

import java.util.ArrayList;
import java.util.List;

public class GradeService {

    private final GradeDao gradeDao;
    private final ApplicationDao applicationDao;
    private final SessionManager sessionManager;

    public GradeService(GradeDao gradeDao, ApplicationDao applicationDao, SessionManager sessionManager) {
        this.gradeDao = gradeDao;
        this.applicationDao = applicationDao;
        this.sessionManager = sessionManager;
    }

    public void setGrade(String token, SetGradeRequest request) {
        User user = sessionManager.getUser(token);

        if (user == null) {
            throw new IllegalArgumentException("Not authorized");
        }
        if (!(user instanceof Expert)) {
            throw new IllegalArgumentException("Only experts can set grades");
        }

        Expert expert = (Expert) user;

        if (applicationDao.getById(request.getApplicationId()) == null) {
            throw new IllegalArgumentException("Application not found");
        }
        if (request.getValue() < 1 || request.getValue() > 5) {
            throw new IllegalArgumentException("Grade value must be between 1 and 5");
        }

        Grade grade = new Grade(request.getApplicationId(), expert.getLogin(), request.getValue());
        gradeDao.save(grade);
    }

    public void deleteGrade(String token, DeleteGradeRequest request) {
        User user = sessionManager.getUser(token);
        if (user == null) {
            throw new IllegalArgumentException("Not authorized");
        }
        if (!(user instanceof Expert)) {
            throw new IllegalArgumentException("Only experts can delete grades");
        }

        Expert expert = (Expert) user;

        gradeDao.delete(request.getApplicationId(), expert.getLogin());
    }

    public List<Application> getGradedApplications(String token) {
        User user = sessionManager.getUser(token);
        if (user == null || !(user instanceof Expert)) {
            throw new IllegalArgumentException("Only experts usage");
        }

        List<Grade> grades = gradeDao.getByExpertLogin(user.getLogin());

        List<Application> result = new ArrayList<>();
        for (Grade g : grades) {
            Application app = applicationDao.getById(g.getApplicationId());
            if (app != null) {
                result.add(app);
            }
        }
        return result;
    }

    public void deleteGradesByExpert(String login) {
        gradeDao.deleteAllByExpert(login);
    }
}