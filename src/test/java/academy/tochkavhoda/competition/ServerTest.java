package academy.tochkavhoda.competition;

import academy.tochkavhoda.competition.dto.response.ServerResponse;
import academy.tochkavhoda.competition.server.Server;
import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ServerTest {

    @Test
    public void testAddParticipant() {
        Server server = new Server();
        String jsonRequest = "{ \"name\": \"Ivan\", \"age\": 25, \"city\": \"Moscow\" }";

        ServerResponse response = server.addParticipant(jsonRequest);

        Assertions.assertEquals(200, response.getResponseCode());
        Assertions.assertTrue(response.getResponseData().contains("Ivan"));
    }

    @Test
    public void testGetParticipants() {
        Server server = new Server();

        String jsonAdd = "{ \"name\": \"Maria\", \"age\": 30, \"city\": \"Berlin\" }";
        server.addParticipant(jsonAdd);

        ServerResponse response = server.getParticipants("{}");

        Assertions.assertEquals(200, response.getResponseCode());
        Assertions.assertTrue(response.getResponseData().contains("Maria"));
    }

    @Test
    public void testAddParticipantInvalidJson() {
        Server server = new Server();

        String badJson = "{ \"name\": \"Hacker\" ";

        ServerResponse response = server.addParticipant(badJson);

        Assertions.assertEquals(400, response.getResponseCode());
        Assertions.assertTrue(response.getResponseData().contains("Error"));
    }

}
