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
        String jsonRequest = "{ \"name\": \"Ivan\", \"login\": \"ivan_test\", \"password\": \"123\", \"company\": \"IvanCo\" }";

        ServerResponse response = server.addParticipant(jsonRequest);

        Assertions.assertEquals(200, response.getResponseCode());
        Assertions.assertTrue(response.getResponseData().contains("Ivan"));
    }

    @Test
    public void testGetParticipants() {
        Server server = new Server();

        String jsonAdd = "{ \"name\": \"Maria\", \"login\": \"maria_test\", \"password\": \"456\", \"company\": \"MariaDesign\" }";
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

    @Test
    public void testLoginSuccess() {
        Server server = new Server();
        server.addParticipant("{ \"name\": \"Petr\", \"login\": \"petr1\", \"password\": \"pass1\", \"company\": \"OOO Petr\" }");

        ServerResponse response = server.loginParticipant("{ \"login\": \"petr1\", \"password\": \"pass1\" }");

        Assertions.assertEquals(200, response.getResponseCode());
        Assertions.assertTrue(response.getResponseData().contains("token"));
    }

    @Test
    public void testLoginWrongPassword() {
        Server server = new Server();
        server.addParticipant("{ \"name\": \"Petr\", \"login\": \"petr1\", \"password\": \"pass1\", \"company\": \"OOO Petr\" }");

        ServerResponse response = server.loginParticipant("{ \"login\": \"petr1\", \"password\": \"WRONG\" }");

        Assertions.assertEquals(400, response.getResponseCode());
    }

    @Test
    public void testLoginUserNotFound() {
        Server server = new Server();

        ServerResponse response = server.loginParticipant("{ \"login\": \"ghost\", \"password\": \"000\" }");
        Assertions.assertEquals(400, response.getResponseCode());
    }

    @Test
    public void testLogout() {
        Server server = new Server();
        ServerResponse response = server.logout("any-token");
        Assertions.assertEquals(200, response.getResponseCode());
    }

    @Test
    public void testCreateApplication() {
        Server server = new Server();

        server.addParticipant("{ \"name\": \"Petr\", \"login\": \"p1\", \"password\": \"123\", \"company\": \"OOO\" }");

        ServerResponse loginResp = server.loginParticipant("{ \"login\": \"p1\", \"password\": \"123\" }");

        String tokenJson = loginResp.getResponseData();
        String token = tokenJson.substring(10, tokenJson.length() - 2);

        String appJson = "{ \"title\": \"Super App\", \"description\": \"Best project\", \"areas\": [\"IT\"], \"requestedAmount\": 100000 }";

        ServerResponse appResp = server.addApplication(token, appJson);

        Assertions.assertEquals(200, appResp.getResponseCode());
        Assertions.assertTrue(appResp.getResponseData().contains("Super App"));
    }
}
