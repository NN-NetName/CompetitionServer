package academy.tochkavhoda.competition.service;

import academy.tochkavhoda.competition.dao.ApplicationDao;
import academy.tochkavhoda.competition.dao.GradeDao;
import academy.tochkavhoda.competition.dto.request.AddApplicationRequest;
import academy.tochkavhoda.competition.dto.request.FilterApplicationsRequest;
import academy.tochkavhoda.competition.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApplicationService {

    private final ApplicationDao applicationDao;
    private final SessionManager sessionManager;
    private final GradeDao gradeDao;

    public ApplicationService(ApplicationDao applicationDao, SessionManager sessionManager, GradeDao gradeDao) {
        this.applicationDao = applicationDao;
        this.sessionManager = sessionManager;
        this.gradeDao = gradeDao;
    }

    public Application addApplication(String token, AddApplicationRequest request) {
        User user = sessionManager.getUser(token);

        if (user == null) {
            throw new IllegalArgumentException("Invalid token. Please login.");
        }
        if (!(user instanceof Participant)) {
            throw new IllegalArgumentException("Only participants can create applications.");
        }

        Application app = new Application(
                user.getLogin(),
                request.getTitle(),
                request.getDescription(),
                request.getAreas(),
                request.getRequestedAmount()
        );

        applicationDao.save(app);
        return app;
    }

    public List<Application> getApplicationsByAreas(String token, FilterApplicationsRequest request) {
        User user = sessionManager.getUser(token);
        if (user == null || !(user instanceof Expert)) {
            throw new IllegalArgumentException("Only experts can view applications");
        }

        List<Application> allApps = applicationDao.getAll();
        List<Application> result = new ArrayList<>();

        List<String> filterAreas = request.getAreas();
        if (filterAreas == null || filterAreas.isEmpty()) {
            filterAreas = ((Expert) user).getAreas();
        }

        for (Application app : allApps) {
            if (!Collections.disjoint(app.getAreas(), filterAreas)) {
                result.add(app);
            }
        }
        return result;
    }

    public List<Application> calculateWinners(String token, int totalBudget, double minThreshold) {
        User user = sessionManager.getUser(token);
        if (user == null) {
            throw new IllegalArgumentException("Not authorized");
        }

        List<Application> allApps = applicationDao.getAll();
        List<Application> winners = new ArrayList<>();

        class AppWithScore {
            Application app;
            double avgScore;

            AppWithScore(Application app, double avgScore) {
                this.app = app;
                this.avgScore = avgScore;
            }
        }

        List<AppWithScore> scoredApps = new ArrayList<>();

        for (Application app : allApps) {
            List<Grade> grades = gradeDao.getAll();
            double sum = 0;
            int count = 0;

            for (Grade g : grades) {
                if (g.getApplicationId().equals(app.getId())) {
                    sum += g.getValue();
                    count++;
                }
            }

            double avg = (count == 0) ? 0 : sum / count;

            if (avg > minThreshold) {
                scoredApps.add(new AppWithScore(app, avg));
            }
        }

        scoredApps.sort((a1, a2) -> {
            int scoreCompare = Double.compare(a2.avgScore, a1.avgScore);
            if (scoreCompare != 0) return scoreCompare;

            return Integer.compare(a1.app.getRequestedAmount(), a2.app.getRequestedAmount());
        });

        int currentBudget = totalBudget;
        for (AppWithScore item : scoredApps) {
            int requested = item.app.getRequestedAmount();
            if (requested <= currentBudget) {
                winners.add(item.app);
                currentBudget -= requested;
            }
        }
        return winners;
    }

    public void deleteApplicationsByAuthor(String login) {
        applicationDao.deleteAllByAuthor(login);
    }
}