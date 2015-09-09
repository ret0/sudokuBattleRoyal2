package ch.elca.students.sudokubattleroyal2.game;

import static org.junit.Assert.*;

import org.junit.Test;

public class SudokuGeneratorTest {

	@Test
	public void testBoardIsIndependentOfSolution() {
		int[][] solution = SudokuGenerator.createSolution();
		int[][] board = SudokuGenerator.createGame(32, solution);
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				if (board[i][j] == 0) {
					assertTrue("solution was modified", solution[i][j] != 0);
				}
			}
		}
	}

}
