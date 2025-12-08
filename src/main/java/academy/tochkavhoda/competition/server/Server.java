package academy.tochkavhoda.competition.server;

import academy.tochkavhoda.competition.daoimpl.ParticipantDaoImpl;
import academy.tochkavhoda.competition.database.Database;
import academy.tochkavhoda.competition.dto.request.AddParticipantRequest;
import academy.tochkavhoda.competition.dto.response.ServerResponse;
import academy.tochkavhoda.competition.model.Participant;
import academy.tochkavhoda.competition.service.ParticipantService;
import com.google.gson.Gson;

import java.util.List;

public class Server {

    private final ParticipantService participantService;
    private final Gson gson;

    public Server() {
        Database database = new Database();
        ParticipantDaoImpl participantDao = new ParticipantDaoImpl(database);
        this.participantService = new ParticipantService(participantDao);
        this.gson = new Gson();
    }

    public ServerResponse addParticipant(String requestJsonString) {
        try {
            AddParticipantRequest request = gson.fromJson(requestJsonString, AddParticipantRequest.class);
            Participant participant = participantService.addParticipant(request);
            String responseData = gson.toJson(participant);
            return new ServerResponse(200, responseData);
        } catch (Exception e) {
            return new ServerResponse(400, "Error: " + e.getMessage());
        }
    }

    public ServerResponse getParticipants(String requestJsonString) {
        try {
            List<Participant> participants = participantService.getParticipants();
            String responseData = gson.toJson(participants);
            return new ServerResponse(200, responseData);

        } catch (Exception e) {
            return new ServerResponse(400, "Error: " + e.getMessage());
        }
    }
}
