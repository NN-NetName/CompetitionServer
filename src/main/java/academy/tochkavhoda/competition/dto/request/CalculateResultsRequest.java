package academy.tochkavhoda.competition.dto.request;

public class CalculateResultsRequest {
    private int totalBudget;
    private double minThreshold;

    public CalculateResultsRequest() {}

    public CalculateResultsRequest(int totalBudget, double minThreshold) {
        this.totalBudget = totalBudget;
        this.minThreshold = minThreshold;
    }

    public int getTotalBudget() { return totalBudget; }
    public double getMinThreshold() { return minThreshold; }
}