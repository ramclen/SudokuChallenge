package core.ai;



public class SearchMetrics {

    public double nodesExpanded;
    public double searchTime;
    public double openListSize;
    public double pathSize;
    public double maxOpenListSize;

    public double getStatesExpanded() {
        return nodesExpanded;
    }

    public double getSearchTime() {
        return searchTime;
    }

    public double getOpenListSize() {
        return openListSize;
    }

    public double getPathSize() {
        return pathSize;
    }

    public double getMaxOpenListSize() {
        return maxOpenListSize;
    }

    public void setMaxOpenListSize(double maxOpenListSize) {
        this.maxOpenListSize = maxOpenListSize;
    }

    public void setExpandedStates(double nodesExpanded) {
        this.nodesExpanded = nodesExpanded;
    }

    public void setPathSize(double pathSize) {
        this.pathSize = pathSize;
    }

    public void setOpenListSize(double openListSize) {
        this.openListSize = openListSize;
    }

    public void setSearchTime(double searchTime) {
        this.searchTime = searchTime;
    }
}
