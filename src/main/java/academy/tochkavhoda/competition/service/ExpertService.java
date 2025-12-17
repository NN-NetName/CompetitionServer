package academy.tochkavhoda.competition.service;

import academy.tochkavhoda.competition.dao.ExpertDao;
import academy.tochkavhoda.competition.dto.request.RegisterExpertRequest;
import academy.tochkavhoda.competition.model.Expert;

public class ExpertService {

    private final ExpertDao expertDao;

    public ExpertService(ExpertDao expertDao) {
        this.expertDao = expertDao;
    }

    public Expert registerExpert(RegisterExpertRequest request) {
        if (expertDao.getByLogin(request.getLogin()) != null) {
            throw new IllegalArgumentException("Expert with login " + request.getLogin() + " already exists");
        }

        Expert expert = new Expert(
                request.getName(),
                request.getLogin(),
                request.getPassword(),
                request.getAreas()
        );

        expertDao.save(expert);
        return expert;
    }

    public Expert login(String login, String password) {
        Expert expert = expertDao.getByLogin(login);
        if (expert == null) {
            throw new IllegalArgumentException("Expert not found");
        }
        if (!expert.getPassword().equals(password)) {
            throw new IllegalArgumentException("Wrong password");
        }
        return expert;
    }

    public void deleteExpert(String login) {
        expertDao.delete(login);
    }
}
