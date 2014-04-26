package core.ai.searches;

import core.ai.Enviroment;
import core.ai.Search;
import core.ai.State;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;

public class BreadthFirst extends Search {

    private Queue<State> openList;
    private int maxOpenListSize;
    
    public BreadthFirst(Enviroment enviroment) {
        super(enviroment);
        this.openList = new ArrayDeque<>();
    }

    @Override
    protected void updateCurrentState() {
        this.setCurrentState(openList.poll());
    }

    
    @Override
    protected void updateQueueList(List<State> childs) {
        openList.addAll(childs);
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
