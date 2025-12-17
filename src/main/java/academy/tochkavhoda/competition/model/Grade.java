package academy.tochkavhoda.competition.model;

public class Grade {
    private String applicationId;
    private String expertLogin;
    private int value;

    public Grade() {
    }

    public Grade(String applicationId, String expertLogin, int value) {
        this.applicationId = applicationId;
        this.expertLogin = expertLogin;
        this.value = value;
    }

    public String getApplicationId() { return applicationId; }
    public void setApplicationId(String applicationId) { this.applicationId = applicationId; }

    public String getExpertLogin() { return expertLogin; }
    public void setExpertLogin(String expertLogin) { this.expertLogin = expertLogin; }

    public int getValue() { return value; }
    public void setValue(int value) { this.value = value; }
}