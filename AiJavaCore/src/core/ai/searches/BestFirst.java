package core.ai.searches;

import core.ai.Enviroment;
import core.ai.Heuristic;
import core.ai.InformedState;
import core.ai.Search;
import core.ai.State;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class BestFirst extends Search implements Comparator<State> {

    private List<State> openList;
    private Heuristic heuristic;
    private double maxOpenListSize;

    public BestFirst(Heuristic heuristicFunction, Enviroment enviroment) {
        super(enviroment);
        this.heuristic = heuristicFunction;
        this.openList = new ArrayList<>();
    }

    @Override
    protected void updateCurrentState() {
        this.setCurrentState(openList.remove(openList.size() - 1));
    }

    @Override
    protected void updateQueueList(List<State> childs) {
        InformedState currentState = (InformedState) getCurrentState();
        if (!currentState.isEvaluated()) evaluateCurrentState(currentState);
        for (Iterator<State> it = childs.iterator(); it.hasNext();) {
            InformedState childState = (InformedState) it.next();
            childState.setHeuristicValue(heuristic.evaluate(childState));
            openList.add(childState);
        }
        if (openList.size() > maxOpenListSize) maxOpenListSize = openList.size();
        Collections.sort(openList, this);
    }

    public List<State> getOpenList() {
        return openList;
    }

    public Heuristic getHeuristic() {
        return heuristic;
    }

    @Override
    protected double getOpenListSize() {
        return openList.size();
    }

    @Override
    protected double getMaxOpenListSize() {
        return maxOpenListSize;
    }

    protected void evaluateCurrentState(InformedState currentState) {
        currentState.setHeuristicValue(heuristic.evaluate(currentState));
    }

    protected void setOpenList(List<State> openList) {
        this.openList = openList;
    }

    protected void setMaxOpenListSize(double maxOpenListSize) {
        this.maxOpenListSize = maxOpenListSize;
    }

    @Override
    public int compare(State stateA, State stateB) {
        return compare((InformedState)stateA, (InformedState)stateB);
    }
    
    public int compare(InformedState stateA, InformedState stateB) {
        double heuristicValueA = stateA.getHeuristicValue();
        double heuristicValueB = stateB.getHeuristicValue();
        if (heuristicValueA <= heuristicValueB) return 1;
        return -1;
    }
}
