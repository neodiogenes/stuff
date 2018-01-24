package main;

import java.util.Random;

public class EightQueens {
	
	private final int BOARD_HEIGHT = 8;
	private final int BOARD_WIDTH = 8;
	private final int NUM_QUEENS = 8;
	
	
	public EightQueens() {
		
		//board[1][1] = "Q";
		//queensArray[0][0] = 1;
		//queensArray[0][1] = 1;
	}
	
	public String[][] findQueens() {
		String[][] board = new String[BOARD_HEIGHT][BOARD_WIDTH];
		int[][] queensArray = new int[NUM_QUEENS][2];
		
		for (int i=0; i<BOARD_HEIGHT; i++) {
			for (int j=0; j<BOARD_WIDTH; j++) {
				board[i][j] = "-";
			}
		}		
		
		for (int i=0; i<NUM_QUEENS; i++) {
			queensArray[i][0] = -1;
			queensArray[i][1] = -1;
		}
		
		//place the first queen somewhere random otherwise the result is the same each time
		Random r = new Random(System.currentTimeMillis());
		queensArray[0][0] = r.nextInt(7);
		queensArray[0][1] = r.nextInt(7);
		board[queensArray[0][0]][queensArray[0][1]] = "Q";
		
		return addQueen(1, board, queensArray);
	}
	
	private String[][] addQueen( int q, String[][] board, int[][] queensArray ) {
		String[][] localBoard = board;
		
		for (int i=0; i<BOARD_HEIGHT; i++) {
			for (int j = 0; j<BOARD_WIDTH; j++) {
				if ("Q".equals(localBoard[i][j]) || "x".equals(localBoard[i][j])) {
					//do nothing;
					
				} else if (!checkValidMove(i, j, q, queensArray) ){
					//not a valid move
					if (!"Q".equals(board[i][j])) localBoard[i][j] = "x";
					
				} else {
					//try this one

					localBoard[i][j] = "Q";
					queensArray[q][0] = i;
					queensArray[q][1] = j;

					//printBoard(localBoard);
					
					if ( (q+1) == NUM_QUEENS) return localBoard;
					String[][] nextBoard = addQueen( (q+1), localBoard, queensArray);
					
					if (nextBoard != null) {
						return nextBoard;
					
					} else {
						queensArray[q][0] = -1;
						queensArray[q][1] = -1;
						clearBoard(q, localBoard, queensArray);
					}
				}			
			}
		}
		
		return null;
	}
	
	private boolean checkValidMove( int i, int j, int q0, int[][] queensArray) {
		for (int q=0; q<q0; q++) {
			if (i == queensArray[q][0]) return false;
			else if (j == queensArray[q][1]) return false;
			
			else {
				//check the diagonals
				for (int k=0; k<BOARD_HEIGHT && k<BOARD_WIDTH; k++) {
					if ( i+k == queensArray[q][0] && j+k == queensArray[q][1] ) return false;
					if ( i-k == queensArray[q][0] && j+k == queensArray[q][1] ) return false;
					if ( i+k == queensArray[q][0] && j-k == queensArray[q][1] ) return false;
					if ( i-k == queensArray[q][0] && j-k == queensArray[q][1] ) return false;
				}
			}
		}
		return true;
	}
	
	private String[][] clearBoard( int q0, String[][] board, int[][] queensArray){

		for (int i=0; i<BOARD_HEIGHT; i++) {
			for (int j = 0; j<BOARD_WIDTH; j++) {
				boolean found = false;
				for (int q=0; q<q0; q++) {
					if ( i == queensArray[q][0] && j == queensArray[q][1] ) {
						found = true;
						break;
					}
				}
				if (found) board[i][j] = "Q";
				else board[i][j] = "-";		
			}
		}
		
		return board;
	}
	
	public void printBoard (int[][] queensArray){

		for (int i=0; i<BOARD_HEIGHT; i++) {
			for (int j = 0; j<BOARD_WIDTH; j++) {
				boolean found = false;
				for (int q=0; q<NUM_QUEENS; q++) {
					if ( i == queensArray[q][0] && j == queensArray[q][1] ) {
						found = true;
						break;
					}
				}
				if (found) System.out.print("Q");
				else System.out.print("-");		
				System.out.print(" ");
			}
			System.out.println();
		}
		
		System.out.println();
		System.out.println();
	}
	
	public void printBoard(String[][] board) {
		if (board == null) System.out.println("failure");
		else {
			for (int i=0; i<BOARD_HEIGHT; i++) {
				for (int j=0; j<BOARD_WIDTH; j++) {
					System.out.print(board[i][j]);
					System.out.print(" ");
				}
				System.out.println();
			}
			
			System.out.println();
			System.out.println();
		}
	}

	public static void main (String[] args) {
		EightQueens eq = new EightQueens();
		
		eq.printBoard(eq.findQueens());
	}
}
