package academy.tochkavhoda.competition.model;

import java.util.Objects;

public class Participant extends User {
    private String company;

    public Participant(String name, String company, String login, String password) {
        super(name, login, password);
        this.company = company;
    }

    public Participant() {
    }

    public String getCompany() { return company; }
    public void setCompany(String company) { this.company = company; }
}
