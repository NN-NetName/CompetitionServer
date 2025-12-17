package academy.tochkavhoda.competition.dto.request;

public class SetGradeRequest {
    private String applicationId;
    private int value;

    public SetGradeRequest() {
    }

    public SetGradeRequest(String applicationId, int value) {
        this.applicationId = applicationId;
        this.value = value;
    }

    public String getApplicationId() { return applicationId; }
    public void setApplicationId(String applicationId) { this.applicationId = applicationId; }

    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
}