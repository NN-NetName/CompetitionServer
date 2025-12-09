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
}
