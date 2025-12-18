package academy.tochkavhoda.competition.dto.request;
// Нарушена clean architecture (deleteGrade)
public class DeleteGradeRequest {
    private String applicationId;

    public DeleteGradeRequest() {
    }

    public DeleteGradeRequest(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
}