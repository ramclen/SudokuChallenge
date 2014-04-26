package org.sudoku;

import core.ai.Action;
import core.ai.ActionList;
import core.ai.Enviroment;
import core.ai.State;
import core.ai.searches.DepthFirst;

import java.util.List;

public class Sudoku implements Enviroment {
    private SudokuState sudokuState;

    public Sudoku(SudokuState sudokuState) {
        this.sudokuState = sudokuState;
    }

    public void execute() {
        DepthFirst depthFirstSearch = new DepthFirst(new SudokuHeuristic(this), this);
        sudokuState = (SudokuState) depthFirstSearch.searchFinalState();
    }

    @Override
    public List<Action> getApplicableActions(State state) {
        ActionList applicableList = new ActionList();
        for (Action action : getActionList(state).getActions())
            if (action.isApplicable(state)) applicableList.add(action);
        return applicableList.getActions();
    }

    @Override
    public State getFinalState() {
        return null;
    }

    @Override
    public State getSudokuState() {
        return sudokuState;
    }

    @Override
    public ActionList getActionList(State state) {
        ActionList actionList = new ActionList();
        SudokuState sudokuState = (SudokuState) state;
        List<Position> emptyPositions = sudokuState.emptyCellsPositions();
        for (Position position : emptyPositions) {
            for (int actionNumber = 1; actionNumber <= this.sudokuState.size(); actionNumber++) {
                actionList.add(new SudokuAction(position, actionNumber));
            }
        }
        return actionList;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int[][] board = sudokuState.board();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                builder.append(board[i][j]);
            }
            builder.append('\n');
        }
        return builder.toString();
    }
}
