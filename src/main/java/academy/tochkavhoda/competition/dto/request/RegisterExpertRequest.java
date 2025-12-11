package academy.tochkavhoda.competition.dto.request;

import java.util.List;

public class RegisterExpertRequest {
    private String name;
    private String login;
    private String password;
    private List<String> areas;

    public RegisterExpertRequest() {
    }

    public RegisterExpertRequest(String name, String login, String password, List<String> areas) {
        this.name = name;
        this.login = login;
        this.password = password;
        this.areas = areas;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public List<String> getAreas() { return areas; }
    public void setAreas(List<String> areas) { this.areas = areas; }
}
