package academy.tochkavhoda.competition.model;

import java.util.List;
import java.util.UUID;

public class Application {

    private String id;
    private String participantId;
    private String title;
    private String description;
    private List<String> areas;
    private int requestedAmount;

    public Application() {
    }

    public Application(String participantId, String title, String description, List<String> areas, int requestedAmount) {
        this.id = UUID.randomUUID().toString();
        this.participantId = participantId;
        this.title = title;
        this.description = description;
        this.areas = areas;
        this.requestedAmount = requestedAmount;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getParticipantId() { return participantId; }
    public void setParticipantId(String participantId) { this.participantId = participantId; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getAreas() { return areas; }
    public void setAreas(List<String> areas) { this.areas = areas; }

    public int getRequestedAmount() { return requestedAmount; }
    public void setRequestedAmount(int requestedAmount) { this.requestedAmount = requestedAmount; }
}
