package academy.tochkavhoda.competition.dto.request;

public class AddParticipantRequest {
    private String name;
    private String company;
    private String login;
    private String password;

    public AddParticipantRequest() {
    }

    public AddParticipantRequest(String name, String company, String login, String password) {
        this.name = name;
        this.company = company;
        this.login = login;
        this.password = password;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
