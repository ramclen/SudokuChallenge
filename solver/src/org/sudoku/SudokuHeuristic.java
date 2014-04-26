package org.sudoku;

import core.ai.Heuristic;

public class SudokuHeuristic implements Heuristic<SudokuState> {

    private final Sudoku sudoku;

    public SudokuHeuristic(Sudoku sudoku) {
        this.sudoku = sudoku;
    }

    @Override
    public double evaluate(SudokuState state) {
        return 0;
    }
}
