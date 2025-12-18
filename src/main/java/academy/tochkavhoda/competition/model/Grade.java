package academy.tochkavhoda.competition.model;

public class Grade {
    private Application application;
    private Expert expert;
    private int value;

    public Grade() {
    }

    public Grade(Application application, Expert expert, int value) {
        this.application = application;
        this.expert = expert;
        this.value = value;
    }

    public Application getApplication() { return application; }
    public void setApplication(Application application) { this.application = application; }

    public Expert getExpert() { return expert; }

    public void setExpert(Expert expert) { this.expert = expert; }

    public int getValue() {
        if (value < 1 || value > 5) {
            throw new IllegalArgumentException("Grade value must be between 1 and 5");
        }
        return value;
    }
    public void setValue(int value) { this.value = value; }
}