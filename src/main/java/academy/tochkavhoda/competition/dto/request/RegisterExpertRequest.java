package academy.tochkavhoda.competition.dto.request;

import java.util.Set;

public class RegisterExpertRequest {
    private String name;
    private String login;
    private String password;
    private Set<String> areas;

    public RegisterExpertRequest() {
    }

    public RegisterExpertRequest(String name, String login, String password, Set<String> areas) {
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

    public Set<String> getAreas() { return areas; }
    public void setAreas(Set<String> areas) { this.areas = areas; }
}
