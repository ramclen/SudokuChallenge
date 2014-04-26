package core.ai;



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Search {

    private List<State> visitedStates;
    private Enviroment enviroment;
    private State currentState;
    private final State finalState;
    private long initTime;
    private long endTime;

    protected Search(Enviroment enviroment) {
        this.enviroment = enviroment;
        this.currentState = enviroment.getSudokuState();
        this.finalState = enviroment.getFinalState();
        this.visitedStates = new ArrayList<>();
    }

    public State searchFinalState() {
        setStartTime();
        while (!currentState.isFinal()) {
            updateQueueList(getChilds(currentState));
            markStateAsVisited(currentState);
            updateCurrentState();
        }
        setFinishTime();
        return currentState;
    }

    private void setStartTime() {
        this.initTime = System.currentTimeMillis();
    }

    protected abstract void updateQueueList(List<State> childs);

    private List<State> getChilds(State currentState) {
        List<State> childs = new ArrayList<>();
        for (Iterator it = enviroment.getApplicableActions(currentState).iterator(); it.hasNext();) {
            Action applicableAction = (Action) it.next();
            if (!stateIsVisited(getStateChild(applicableAction, currentState)))
                childs.add(getStateChild(applicableAction, currentState));
        }
        return childs;
    }

    private void markStateAsVisited(State currentState) {
        visitedStates.add(currentState);
    }

    protected abstract void updateCurrentState();

    private void setFinishTime() {
        this.endTime = System.currentTimeMillis();
    }

    private boolean stateIsVisited(State nextState) {
        for (State visitedState : visitedStates)
            if (visitedState.equals(nextState)) return true;
        return false;
    }

    private State getStateChild(Action applicableAction, State currentState) {
        return applicableAction.execute(currentState);
    }

    public Enviroment getEnviroment() {
        return enviroment;
    }

    public void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    public State getCurrentState() {
        return currentState;
    }

    public SearchMetrics getSearchMetrics() {
        SearchMetrics searchMetrics = new SearchMetrics();
        searchMetrics.setOpenListSize(getOpenListSize());
        searchMetrics.setMaxOpenListSize(getMaxOpenListSize());
        searchMetrics.setExpandedStates(visitedStates.size());
        searchMetrics.setPathSize(getPathSize(currentState));
        searchMetrics.setSearchTime(endTime - initTime);
        return searchMetrics;
    }

    protected abstract double getOpenListSize();

    protected abstract double getMaxOpenListSize();

    private double getPathSize(State currentState) {
        double sizeCounter = 0;
        State nextState = currentState;
        while ((nextState = nextState.getParent()) != null)
            sizeCounter++;
        return sizeCounter;
    }
}
