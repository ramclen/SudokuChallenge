package org.sudoku;

import core.ai.State;

import java.util.LinkedList;
import java.util.List;

public class SudokuState extends State {
    private int[][] board;

    public SudokuState(int[][] board) {
        this(null, board);
    }

    public SudokuState(State parent, int[][] board) {
        super(parent);
        this.board = board;
    }

    @Override
    public boolean equals(State otherState) {
        if (!(otherState instanceof SudokuState)) return false;
        SudokuState otherBoard = (SudokuState) otherState;
        for (int row = 0; row < board.length; row++)
            for (int column = 0; column < board.length; column++)
                if (board != otherBoard.board) return false;
        return true;
    }

    @Override
    public boolean isFinal() {
        return emptyCellsPositions().size() == 0;
    }

    public List<Position> emptyCellsPositions() {
        List<Position> positions = new LinkedList<Position>();
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board.length; j++)
                if (isEmpty(i, j)) positions.add(new Position(i, j));
        return positions;
    }

    private boolean isEmpty(int row, int column) {
        return board[row][column] == 0;
    }

    public int size() {
        return board.length;
    }

    public int[][] board() {
        return board;
    }
}
