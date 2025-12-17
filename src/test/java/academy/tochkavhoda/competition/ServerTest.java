package academy.tochkavhoda.competition;

import academy.tochkavhoda.competition.dto.response.LoginResponse;
import academy.tochkavhoda.competition.dto.response.ServerResponse;
import academy.tochkavhoda.competition.model.Application;
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

    @Test
    public void testDeleteParticipantCascade() {
        Server server = new Server();
        Gson gson = new Gson();

        server.addParticipant("{ \"name\": \"DelUser\", \"login\": \"del\", \"password\": \"123\", \"company\": \"C\" }");
        String token = gson.fromJson(
                server.loginParticipant("{ \"login\": \"del\", \"password\": \"123\" }").getResponseData(),
                LoginResponse.class
        ).getToken();

        ServerResponse appResp = server.addApplication(token,
                "{ \"title\": \"To Delete\", \"description\": \"D\", \"areas\": [], \"requestedAmount\": 100 }");
        Application app = gson.fromJson(appResp.getResponseData(), Application.class);

        ServerResponse delResp = server.deleteParticipant(token, "{}");
        Assertions.assertEquals(200, delResp.getResponseCode());

        ServerResponse loginResp = server.loginParticipant("{ \"login\": \"del\", \"password\": \"123\" }");
        Assertions.assertEquals(400, loginResp.getResponseCode());
    }

    @Test
    public void testCompetitionLogic() {
        Server server = new Server();
        Gson gson = new Gson();

        server.registerExpert("{ \"name\": \"Exp1\", \"login\": \"exp1\", \"password\": \"123\", \"areas\": [\"IT\"] }");
        String tokenExpert = gson.fromJson(
                server.loginExpert("{ \"login\": \"exp1\", \"password\": \"123\" }").getResponseData(),
                LoginResponse.class
        ).getToken();

        server.addParticipant("{ \"name\": \"Part1\", \"login\": \"p1\", \"password\": \"123\", \"company\": \"C1\" }");
        String tokenParticipant = gson.fromJson(
                server.loginParticipant("{ \"login\": \"p1\", \"password\": \"123\" }").getResponseData(),
                LoginResponse.class
        ).getToken();

        ServerResponse resp1 = server.addApplication(tokenParticipant,
                "{ \"title\": \"Expensive\", \"description\": \"desc\", \"areas\": [\"IT\"], \"requestedAmount\": 100000 }");
        Application app1 = gson.fromJson(resp1.getResponseData(), Application.class);

        ServerResponse resp2 = server.addApplication(tokenParticipant,
                "{ \"title\": \"Cheap\", \"description\": \"desc\", \"areas\": [\"IT\"], \"requestedAmount\": 40000 }");
        Application app2 = gson.fromJson(resp2.getResponseData(), Application.class);

        ServerResponse resp3 = server.addApplication(tokenParticipant,
                "{ \"title\": \"Mediocre\", \"description\": \"desc\", \"areas\": [\"IT\"], \"requestedAmount\": 10000 }");
        Application app3 = gson.fromJson(resp3.getResponseData(), Application.class);

        server.setGrade(tokenExpert, "{ \"applicationId\": \"" + app1.getId() + "\", \"value\": 5 }");
        server.setGrade(tokenExpert, "{ \"applicationId\": \"" + app2.getId() + "\", \"value\": 5 }");
        server.setGrade(tokenExpert, "{ \"applicationId\": \"" + app3.getId() + "\", \"value\": 3 }");


        String requestCalc = "{ \"totalBudget\": 110000, \"minThreshold\": 2.0 }";
        ServerResponse resultsResp = server.calculateResults(tokenParticipant, requestCalc);

        Assertions.assertEquals(200, resultsResp.getResponseCode());
        String jsonResponse = resultsResp.getResponseData();

        Assertions.assertTrue(jsonResponse.contains("Cheap"));
        Assertions.assertTrue(jsonResponse.contains("Mediocre"));
        Assertions.assertFalse(jsonResponse.contains("Expensive"));
    }
}
