package academy.tochkavhoda.competition.dto.request;

import java.util.List;

public class AddApplicationRequest {
    private String title;
    private String description;
    private List<String> areas;
    private int requestedAmount;

    public AddApplicationRequest() { }

    public AddApplicationRequest(String title, String description, List<String> areas, int requestedAmount) {
        this.title = title;
        this.description = description;
        this.areas = areas;
        this.requestedAmount = requestedAmount;
    }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getAreas() { return areas; }
    public void setAreas(List<String> areas) { this.areas = areas; }

    public int getRequestedAmount() { return requestedAmount; }
    public void setRequestedAmount(int requestedAmount) { this.requestedAmount = requestedAmount; }
}
