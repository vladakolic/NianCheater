import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class Main {

	private static final int BOARD_SIZE = 3;
	private static final File FILE = new File("board");

	// Use linked list for board? When a character is checked, remove it so we
	// don't check it again.
	private static LinkedList<Character> linkedBoard = new LinkedList<Character>();

	private static char essentialChar;
	private static final File DICTIONARY = new File("newbigdic");

	public static void main(String[] args) throws FileNotFoundException {

		Scanner scanner = new Scanner(FILE);

		// key: character, value: number of occurences
		Map<Character, Integer> chars = new HashMap<Character, Integer>();
		for (int i = 0; i < BOARD_SIZE*BOARD_SIZE; i++) {
			char currentChar = scanner.next().charAt(0);
			if(chars.containsKey(currentChar)){
				chars.put(currentChar, (chars.get(currentChar) + 1));
			} else {
				chars.put(currentChar, 1);
			}
			if(i == (BOARD_SIZE*BOARD_SIZE)/2)
				essentialChar = currentChar;
		}

		// sort the linked board, first char will appear first in the
		// dictionary.
		

		// HAHAHAHHAHA givet listan {o, b, c, c, d, o, f} så matchas följande regex. PLUG THAT SHIT IN IF YOU'D LIKE
		//(?=(?:[^o]*o){0,2}[^o]*$)(?=(?:[^c]*c){0,2}[^c]*$)(?=[^b]*b?[^b]*$)(?=[^d]*d?[^d*]*$)(?=[^f]*f?[^f]*$)^[obcdf]+$
		
		// solve
		String result = new String();
		while(scanner.hasNextLine()){
			String currentLine = scanner.nextLine();
			if (currentLine.indexOf(essentialChar) == -1){
				// do nothing
			} else {
				for (int i = 0; i < currentLine.length(); i++){
					if (chars.containsKey(currentLine.charAt(i))){
						if (currentLine.length() > result.length()){
							currentLine = result;
						}
					} else {
						break; // not a match
					}
				}
			}
		}
		// solve
		// solver(board, new StringBuilder(), 0, new Scanner(DICTIONARY));
	}

	private static String solver(LinkedList<Character> board, int index,
			StringBuilder sb, Scanner scanner) {
		// if (!scanner.hasNextLine()) // end of file, return shiet
		// return sb.toString();
		//
		// if (board.isEmpty()) // no more characters, return shiet
		// return sb.toString();
		//
		// String currentLine = scanner.nextLine();
		// // char currentChar = board.remove(index);
		// // sb.append(currentChar);
		// // if (currentLine.matches(sb.toString())){
		// // System.out.println(currentLine);
		// // } else {
		// // sb.deleteCharAt(sb.length()-1);
		// // }
		// return solver(board, index, sb, scanner);

		String result = new String();
		while (scanner.hasNextLine() && !board.isEmpty()) {
			String currentLine = scanner.nextLine();
			if (currentLine.indexOf(essentialChar) == -1) { // essentialChar
															// does not occur
				// do nothing
			} else if (currentLine.equals(sb.toString())) { // we got a match !
				if (currentLine.length() > result.length()) { // check if this
																// match is
																// better than
																// the last one
					result = currentLine;
				} else {
					// do nothing
				}
			} else if (currentLine.startsWith(sb.toString())) { // partial match
				char currentChar = board.removeFirst();
			}
		}
		return sb.toString();

	}

	/**
	 * Solver method
	 * 
	 * @param board
	 *            The board to be analyzed
	 * @param sb
	 *            The current subword match
	 * @param index
	 *            The current board index
	 * @return The string match
	 * @throws FileNotFoundException
	 */
	// private static StringBuilder solver(char[] board, StringBuilder sb, int
	// index, Scanner scanner) {
	// if (index == board.length) { // checked every letter of the board,
	// current match is the best
	// // do something other than increase index
	// HEHEHEHEHHEEHHEEHEHEHEHEHEEHEHHEHEHEH
	// return sb;
	// }
	//
	// return solver(board, sb, ++index, scanner);
	// }

	/**
	 * Very inefficient method for checking if the input string is a correct
	 * match.
	 * 
	 * @param s
	 * @return
	 * @throws FileNotFoundException
	 */
	private static boolean match(String s) throws FileNotFoundException {
		if (s.matches(new Character(essentialChar).toString()))
			return false;
		Scanner scanner = new Scanner(DICTIONARY);
		while (scanner.hasNextLine()) {
			if (s.equals(scanner.nextLine())) {
				return true;
			}
		}
		return false;
	}
}
