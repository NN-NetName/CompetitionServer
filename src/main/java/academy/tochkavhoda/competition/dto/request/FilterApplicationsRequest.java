package academy.tochkavhoda.competition.dto.request;

import java.util.List;

public class FilterApplicationsRequest {
    private List<String> areas; // Список направлений для фильтрации

    public FilterApplicationsRequest() {
    }

    public FilterApplicationsRequest(List<String> areas) {
        this.areas = areas;
    }

    public List<String> getAreas() {
        return areas;
    }

    public void setAreas(List<String> areas) {
        this.areas = areas;
    }
}