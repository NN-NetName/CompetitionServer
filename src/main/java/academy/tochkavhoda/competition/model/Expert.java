package academy.tochkavhoda.competition.model;
import java.util.ArrayList;
import java.util.List;

public class Expert extends User{
    private List<String> areas = new ArrayList<>();

    public Expert() {
    }

    public Expert(String name, String login, String password, List<String> areas) {
        super(name, login, password);
        this.areas = areas;
    }

    public List<String> getAreas() { return areas; }
    public void setAreas(List<String> areas) { this.areas = areas; }
}
