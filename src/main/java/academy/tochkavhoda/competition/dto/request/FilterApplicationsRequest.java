package academy.tochkavhoda.competition.dto.request;

import java.util.Set;

public class FilterApplicationsRequest {
    private Set<String> areas;

    public FilterApplicationsRequest() {
    }

    public FilterApplicationsRequest(Set<String> areas) {
        this.areas = areas;
    }

    public Set<String> getAreas() {
        return areas;
    }

    public void setAreas(Set<String> areas) {
        this.areas = areas;
    }
}