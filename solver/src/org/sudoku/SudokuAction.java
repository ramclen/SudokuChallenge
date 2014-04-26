package org.sudoku;

import core.ai.Action;
import core.ai.State;

public class SudokuAction implements Action {
    private final Position position;
    private final int actionNumber;

    public SudokuAction(Position position, int actionNumber) {
        this.position = position;
        this.actionNumber = actionNumber;
    }

    @Override
    public State execute(State state) {
        SudokuState oldSudokuState = (SudokuState) state;
        int[][] newBoard = oldSudokuState.board();
        newBoard[position.row()][position.column()] = actionNumber;
        return new SudokuState(oldSudokuState, newBoard);
    }

    @Override
    public boolean isApplicable(State state) {
        SudokuState sudokuState = (SudokuState) state;
        if (isInRow(sudokuState, actionNumber, position.row())) return false;
        if (isInColumn(sudokuState, actionNumber, position.column())) return false;
        if (isInQuadrant(sudokuState, actionNumber)) return false;
        return true;
    }

    private boolean isInRow(SudokuState sudokuState, int actionNumber) {
        int[][] board = sudokuState.board();
        for (int i = 0; i < board.length; i++)
            if (board[position.row()][i] == actionNumber) return false;
        return true;
    }

    private boolean isInColumn(SudokuState sudokuState, int actionNumber) {
        int[][] board = sudokuState.board();
        for (int i = 0; i < board.length; i++)
            if (board[i][position.column()] == actionNumber) return false;
        return true;

    }

    private boolean isInQuadrant(SudokuState sudokuState, int actionNumber) {
        double quadrantLength = Math.sqrt(sudokuState.board().length);
        int metaRow = (int) (position.row() / quadrantLength);
        int metaColumn = (int) (position.row() / quadrantLength);
        for (int i = (int) (metaRow * quadrantLength); i < (metaRow + 1) * quadrantLength; i++)
            for (int j = (int) (metaColumn * quadrantLength); j < ((metaColumn + 1) * quadrantLength); j++)
                if (sudokuState.board()[i][j] == actionNumber) return false;
        return true;
    }
}
