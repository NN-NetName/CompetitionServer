package academy.tochkavhoda.competition.server;

import academy.tochkavhoda.competition.daoimpl.ExpertDaoImpl;
import academy.tochkavhoda.competition.daoimpl.ParticipantDaoImpl;
import academy.tochkavhoda.competition.database.Database;
import academy.tochkavhoda.competition.dto.request.AddParticipantRequest;
import academy.tochkavhoda.competition.dto.request.LoginRequest;
import academy.tochkavhoda.competition.dto.request.RegisterExpertRequest;
import academy.tochkavhoda.competition.dto.response.LoginResponse;
import academy.tochkavhoda.competition.dto.response.ServerResponse;
import academy.tochkavhoda.competition.model.Expert;
import academy.tochkavhoda.competition.model.Participant;
import academy.tochkavhoda.competition.service.ExpertService;
import academy.tochkavhoda.competition.service.ParticipantService;
import academy.tochkavhoda.competition.service.SessionManager;
import com.google.gson.Gson;

import java.util.List;

public class Server {

    private final ParticipantService participantService;
    private final ExpertService expertService;
    private final Gson gson;
    private final SessionManager sessionManager;

    public Server() {
        ParticipantDaoImpl participantDao = new ParticipantDaoImpl();
        this.participantService = new ParticipantService(participantDao);

        ExpertDaoImpl expertDao = new ExpertDaoImpl();
        this.expertService = new ExpertService(expertDao);

        this.sessionManager = new SessionManager();
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

    public ServerResponse registerExpert(String requestJsonString) {
        try {
            RegisterExpertRequest request = gson.fromJson(requestJsonString, RegisterExpertRequest.class);
            Expert expert = expertService.registerExpert(request);
            String responseData = gson.toJson(expert);
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

    public ServerResponse loginParticipant(String requestJsonString) {
        try {
            LoginRequest request = gson.fromJson(requestJsonString, LoginRequest.class);
            Participant participant = participantService.login(request.getLogin(), request.getPassword());

            String token = sessionManager.createSession(participant);

            String responseData = gson.toJson(new LoginResponse(token));
            return new ServerResponse(200, responseData);
        } catch (Exception e) {
            return new ServerResponse(400, "Error: " + e.getMessage());
        }
    }
    public ServerResponse loginExpert(String requestJsonString) {
        try {
            LoginRequest request = gson.fromJson(requestJsonString, LoginRequest.class);
            Expert expert = expertService.login(request.getLogin(), request.getPassword());

            String token = sessionManager.createSession(expert);

            String responseData = gson.toJson(new LoginResponse(token));
            return new ServerResponse(200, responseData);

        } catch (Exception e) {
            return new ServerResponse(400, "Error: " + e.getMessage());
        }
    }

    public ServerResponse logout(String token) {
        sessionManager.removeSession(token);
        return new ServerResponse(200, "Logout successful");
    }
}
