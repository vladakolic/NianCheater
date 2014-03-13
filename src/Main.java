import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {

	private static final int BOARD_SIZE = 3;
	private static final File FILE = new File("board");
	
	private static char[][] board = new char[BOARD_SIZE][BOARD_SIZE];
	private static char essentialChar;
	
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(FILE);
		for (int i = 0; i < board.length; i++){
			for (int j = 0; j < board[i].length; j++){
				board[i][j] = scanner.next().charAt(0);
				if ((i == j) && (j == BOARD_SIZE / 2)){
					essentialChar = board[i][j];
				}
			}
		}
	}
}
