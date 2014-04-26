package org.sudoku.operators;

import org.sudoku.Sudoku;
import org.sudoku.SudokuState;

public class Main {
    public static void main(String[] args) {
        int[][] board = {
                {1, 4, 3, 2},
                {3, 2, 4, 1},
                {2, 3, 0, 4},
                {4, 1, 2, 3}};
        Sudoku sudoku = new Sudoku(new SudokuState(board));
        sudoku.execute();
        System.out.println(sudoku);
    }

}
