import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;


public class Main {

	private static final int BOARD_SIZE = 3;
	private static final File FILE = new File("board");

	private static char[] board = new char[BOARD_SIZE*BOARD_SIZE];
	
	// Use linked list for board? When a character is checked, remove it so we don't check it again.
	private static LinkedList<Character> linkedBoard = new LinkedList<Character>();
	
	private static char essentialChar;
	private static final File DICTIONARY = new File("newbigdic");
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner scanner = new Scanner(FILE);

		for (int i = 0; i < board.length; i++){
			board[i] = scanner.next().charAt(0);
			linkedBoard.addLast(board[i]);
		}
		essentialChar = board[board.length/2];
		
		// sort the linked board, first char will appear first in the dictionary.
		Collections.sort(linkedBoard);
		
		// solve
		String solution = solver(linkedBoard, 0, new StringBuilder("\b"), new Scanner(DICTIONARY));
		
		if (solution.isEmpty()){
			System.out.println("UNSOLVABLE OMG");
		}
		// solve
//		solver(board, new StringBuilder(), 0, new Scanner(DICTIONARY));
	}
	
	
	private static String solver(LinkedList<Character> board, int index,
			StringBuilder sb, Scanner scanner) {
		if (!scanner.hasNextLine()) // end of file, return shiet
			return sb.toString();
		
		if (board.isEmpty())		// no more characters, return shiet
			return sb.toString();
		
		String currentLine = scanner.nextLine();
//		char currentChar = board.remove(index);
//		sb.append(currentChar);
//		if (currentLine.matches(sb.toString())){
//			System.out.println(currentLine);
//		} else {
//			sb.deleteCharAt(sb.length()-1);
//		}
		return solver(board, index, sb, scanner);
		
	}
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * Solver method
	 * @param board The board to be analyzed
	 * @param sb	The current subword match
	 * @param index	The current board index
	 * @return		The string match
	 * @throws FileNotFoundException 
	 */
//	private static StringBuilder solver(char[] board, StringBuilder sb, int index, Scanner scanner) {
//		if (index == board.length) { // checked every letter of the board, current match is the best
//			// do something other than increase index HEHEHEHEHHEEHHEEHEHEHEHEHEEHEHHEHEHEH
//			return sb;
//		}
//		
//		return solver(board, sb, ++index, scanner);
//	}
	


	/**
	 * Very inefficient method for checking if the input string is 
	 * a correct match. 
	 * 
	 * @param s
	 * @return
	 * @throws FileNotFoundException
	 */
	private static boolean match(String s) throws FileNotFoundException {
		if (s.matches(new Character(essentialChar).toString())) return false;
		Scanner scanner = new Scanner(DICTIONARY);
		while (scanner.hasNextLine()){
			if (s.equals(scanner.nextLine())){
				return true;
			}
		}
		return false;
	}
}
