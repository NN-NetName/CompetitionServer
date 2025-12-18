package academy.tochkavhoda.competition.model;

import java.util.Set; // Используем Set
import java.util.UUID;

public class Application {

    private String id;
    private Participant participant;
    private String title;
    private String description;
    private Set<String> areas;
    private int requestedAmount;

    public Application() {
    }

    public Application(Participant participant, String title, String description, Set<String> areas, int requestedAmount) {
        this.id = UUID.randomUUID().toString();
        this.participant = participant;
        this.title = title;
        this.description = description;
        this.areas = areas;
        this.requestedAmount = requestedAmount;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Participant getParticipant() { return participant; }
    public void setParticipant(Participant participant) { this.participant = participant; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Set<String> getAreas() { return areas; }
    public void setAreas(Set<String> areas) { this.areas = areas; }

    public int getRequestedAmount() { return requestedAmount; }
    public void setRequestedAmount(int requestedAmount) { this.requestedAmount = requestedAmount; }
}