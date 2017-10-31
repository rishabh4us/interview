import java.util.Random;

/*
 * Bobi Pu
 * bobi.pu@usc.edu
 * Tic Tac Toe Game
 **/


public class TicTacToe {
	public static void main(String[] args) throws Exception{
		Game game = new Game(3);
		game.startGame();
	}	
}

class Board {
	private int[][] board;	//0 empty, 1 first player, 2 second player
	public int size;
	public Board(int size) throws Exception {
		if (size < 3) {
			throw new Exception("Invalid Size");
		}
		this.size = size;
		board = new int[size][size];
	}
	
	public int getValue(int x, int y) {
		return board[x][y];
	}
	
	public boolean setValue(int x, int y, int code) {
		if (getValue(x, y) != 0) {
			return false;
		} else {
			board[x][y] = code;
			return true;
		}
	}
	
	public int[] getRow(int i) {
		return board[i];
	}
	
	public int[] getColumn(int i) {
		int[] column = new int[size];
		for (int j = 0; j < size; j++) {
			column[j] = board[j][i];
		}
		return column;
	}
	
	public int[] getDiagonal() {
		int[] diagonal = new int[size]; 
		for (int i = 0; i < size; i++) {
			diagonal[i] = board[i][i];
		}
		return diagonal;
	}
	
	public boolean isFull() {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				if (board[i][j] == 0) {
					return false;
				}
			}
		}
		return true;
	}
	public void setEmpty() {
		board = new int[size][size];
	}
}

class TicTacToePlayer {
	int code;
	public TicTacToePlayer(int code) {
		this.code = code;
	}
	
	Coordinate play(final Board board){
		int x = 0, y = 0;
		do {
			Random rand = new Random();
			x = rand.nextInt(board.size);
			y = rand.nextInt(board.size);
		} while(board.getValue(x, y) != 0 && !board.isFull());
		return new Coordinate(x, y);
	}
}

class Game {
	Board board;
	TicTacToePlayer p1, p2;
	public Game(int size) throws Exception {
		board = new Board(size);
		p1 = new TicTacToePlayer(1);
		p2 = new TicTacToePlayer(2);
	}
	
	private int checkWin() {
		int winner = 0;
		for (int i = 0; i < board.size; i++) {
			winner = expandCheck(board.getRow(i));
			if (winner != 0) {
				return winner;
			}
		}
		for (int i = 0; i < board.size; i++) {
			winner = expandCheck(board.getColumn(i));
			if (winner != 0) {
				return winner;
			}		
		}
		
		return expandCheck(board.getDiagonal());
	}
	
	private int expandCheck(int[] row) {
		int mid = row.length / 2;
		if (row[mid] == 0) {
			return 0;
		}
		int left = mid - 1, right = row.length % 2 == 0 ? mid : mid + 1 , flag = row[mid];	//chcek odd
		while (left >= 0 && right <= row.length && row[left] == flag && row[right] == flag) {
			left--;
			right++;
		}
		if (left == -1 && right == row.length) {
			return flag;
		} else {
			return 0;
		}
	}
	
	public void startGame() {
		board.setEmpty();
		while (!board.isFull()) {
			Coordinate move = p1.play(board);
			board.setValue(move.x, move.y, p1.code);
			if (checkWin() != 0) {
				break;
			}
			move = p2.play(board);
			board.setValue(move.x, move.y, p2.code);
			if (checkWin() != 0) {
				break;
			}
		}
		int winner = checkWin();
		String result = winner == 0 ? "Result is Tie." : "Winner is Player " + winner;
		System.out.println(result);
	}
}

class Coordinate {
	int x, y;
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}
}