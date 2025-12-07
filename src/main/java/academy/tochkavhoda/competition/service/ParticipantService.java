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
                request.getAge(),
                request.getCity()
        );

        participantDao.save(participant);

        return participant;
    }

    // Метод получения всех участников
    public List<Participant> getParticipants() {
        return participantDao.getAll();
    }
}
