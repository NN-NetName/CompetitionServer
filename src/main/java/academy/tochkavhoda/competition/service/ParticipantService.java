package academy.tochkavhoda.competition.service;

import academy.tochkavhoda.competition.dao.ParticipantDao;
import academy.tochkavhoda.competition.dto.request.AddParticipantRequest;
import academy.tochkavhoda.competition.model.Participant;

import java.util.List;

public class ParticipantService {

    private final ParticipantDao participantDao;

    public ParticipantService(ParticipantDao participantDao) {
        this.participantDao = participantDao;
    }

    public Participant addParticipant(AddParticipantRequest request) {
        if (participantDao.getByLogin(request.getLogin()) != null) {
            throw new IllegalArgumentException("User with login " + request.getLogin() + " already exists");
        }
        Participant participant = new Participant(
                request.getName(),
                request.getCompany(),
                request.getLogin(),
                request.getPassword()
        );

        participantDao.save(participant);
        return participant;
    }

    public List<Participant> getParticipants() {
        return participantDao.getAll();
    }

    public Participant login(String login, String password) {
        Participant participant = participantDao.getByLogin(login);
        if (participant == null) {
            throw new IllegalArgumentException("User not found");
        }
        if (!participant.getPassword().equals(password)) {
            throw new IllegalArgumentException("Wrong password");
        }
        return participant;
    }

    public void deleteParticipant(String login) {
        participantDao.delete(login);
    }
}
