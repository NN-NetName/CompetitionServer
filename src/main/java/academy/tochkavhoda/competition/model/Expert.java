package academy.tochkavhoda.competition.model;
import java.util.HashSet;
import java.util.Set;

public class Expert extends User{
    private Set<String> areas = new HashSet<>();

    public Expert() {
    }

    public Expert(String name, String login, String password, Set<String> areas) {
        super(name, login, password);
        this.areas = areas;
    }

    public Set<String> getAreas() { return areas; }
    public void setAreas(Set<String> areas) { this.areas = areas; }
}
