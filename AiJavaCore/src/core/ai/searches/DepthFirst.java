package core.ai.searches;

import core.ai.Enviroment;
import core.ai.Heuristic;
import core.ai.Search;
import core.ai.State;

import java.util.List;
import java.util.Stack;

public class DepthFirst extends Search {

    private Stack<State> openList;
    private double maxOpenListSize;

    public DepthFirst(Heuristic sudokuHeuristic, Enviroment enviroment) {
        super(enviroment);
        this.openList = new Stack<>();
    }

    @Override
    protected void updateCurrentState() {
        setCurrentState(openList.pop());
    }

    @Override
    protected void updateQueueList(List<State> childs) {
        for (State state : childs)
            openList.push(state);
        if (openList.size() > maxOpenListSize) maxOpenListSize = openList.size();
    }

    @Override
    protected double getOpenListSize() {
        return openList.size();
    }

    @Override
    protected double getMaxOpenListSize() {
        return maxOpenListSize;
    }
}
