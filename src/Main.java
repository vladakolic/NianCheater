import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

	private static String board = "ditoslnuv";
	private static char essentialChar = 's';
	private static final File DICTIONARY = new File("newbigdic");

	public static void main(String[] args) throws FileNotFoundException {
		Main m = new Main();
		m.solve(board, new Scanner(DICTIONARY));
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
	public void solve(String board, Scanner scanner) {
		String regex = createRegex(board);
		while (scanner.hasNextLine()) {
			String currentLine = scanner.nextLine();
			if (currentLine.indexOf(essentialChar) == -1) { // essentialChar
				continue; // does not occur
			} else if (currentLine.matches(regex)) {
				System.out.println(currentLine);
			}
		}
	}

	/**
	 * Very inefficient method for checking if the input string is a correct
	 * match.
	 * 
	 * @param s
	 * @return
	 * @throws FileNotFoundException
	 */
	// private static boolean match(String s) throws FileNotFoundException {
	//
	// }

	/**
	 * Generates regex that represents the 3x3 board
	 * 
	 * @param board
	 *            the board in string format
	 */
	private String createRegex(String board) {
		// String singleOccurenceRegex = "(?=[^%s]*%s?[^%s]*$)";
		String multiOccurenceRegex = "(?=(?:[^%s]*%s){0,%d}[^%s]*$)";
		StringBuilder sb = new StringBuilder();
		// Add board to hashmap together with letters occurences
		HashMap<Character, Integer> characterOccurences = populateMap(board);

		// Start building regex
		for (Character c : characterOccurences.keySet()) {
			sb.append(String.format(multiOccurenceRegex, c, c,
					characterOccurences.get(c).intValue(), c));
		}
		// Append weird dollar stuff at end..
		sb.append("^[");
		for (Character c : characterOccurences.keySet()) {
			sb.append(c);
		}
		sb.append("]+$");
		return sb.toString();
	}

	/**
	 * It just works.
	 * 
	 * @param board
	 * @return
	 */
	private HashMap<Character, Integer> populateMap(String board) {
		HashMap<Character, Integer> characterOccurences = new HashMap<>();
		for (int i = 0; i < board.length(); i++) {
			if (characterOccurences.containsKey(board.charAt(i))) {
				Integer temp = characterOccurences.get(board.charAt(i));
				Integer newVal = temp + 1;
				characterOccurences.put(board.charAt(i), newVal);
			} else {
				characterOccurences.put(board.charAt(i), new Integer(1));
			}
		}
		return characterOccurences;
	}

}
