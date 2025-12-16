package academy.tochkavhoda.competition.service;

import academy.tochkavhoda.competition.dao.ApplicationDao;
import academy.tochkavhoda.competition.dto.request.AddApplicationRequest;
import academy.tochkavhoda.competition.model.Application;
import academy.tochkavhoda.competition.model.User;
import academy.tochkavhoda.competition.model.Participant;

public class ApplicationService {

    private final ApplicationDao applicationDao;
    private final SessionManager sessionManager;

    public ApplicationService(ApplicationDao applicationDao, SessionManager sessionManager) {
        this.applicationDao = applicationDao;
        this.sessionManager = sessionManager;
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
}