package core.ai.searches;

import core.ai.Enviroment;
import core.ai.Heuristic;
import core.ai.InformedState;
import core.ai.State;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class BestFirstWithPathCost extends BestFirst {

    public BestFirstWithPathCost(Heuristic heuristicFunction, Enviroment enviroment) {
        super(heuristicFunction, enviroment);
    }

    @Override
    protected void updateQueueList(List<State> childs) {
        InformedState currentState = (InformedState) getCurrentState();
        evaluateCostAndHeuristic(currentState);
        addChildsToOpenList(childs);
        updateMaxListSize();
        sortQueue();
    }

    private void addChildsToOpenList(List<State> childs) {
        for (Iterator<State> it = childs.iterator(); it.hasNext();) {
            InformedState childState = (InformedState) it.next();
            evaluateCostAndHeuristic(childState);
            super.getOpenList().add(childState);
        }
    }

    private void evaluateCostAndHeuristic(InformedState currentState) {
        if (!currentState.isEvaluated()) super.evaluateCurrentState(currentState);
        if (!currentState.isCostCalculated()) calculateCurrentStateCost(currentState);
    }

    private void updateMaxListSize() {
        if (super.getOpenList().size() > getMaxOpenListSize())
            setMaxOpenListSize(super.getOpenList().size());
    }

    private void sortQueue() {
        Collections.sort(super.getOpenList(), this);
    }

    private void calculateCurrentStateCost(InformedState currentState) {
        currentState.setCostValue(currentState.calculateCostValue());
    }

    @Override
    public int compare(State stateA, State stateB) {
        return compare((InformedState) stateA, (InformedState) stateB);
    }

    @Override
    public int compare(InformedState stateA, InformedState stateB) {
        double heuristicValueA = stateA.getHeuristicValue();
        double heuristicValueB = stateB.getHeuristicValue();
        if (heuristicValueA < heuristicValueB) return 1;
        if (heuristicValueA == heuristicValueB)
            if (stateA.calculateCostValue() < stateB.calculateCostValue())
                return 1;
        return -1;
    }
}
