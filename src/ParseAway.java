import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;


public class ParseAway {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException{
		Scanner scanner = new Scanner(new File("bigdic"));
		PrintWriter pw = new PrintWriter("newbigdic", "UTF-8");
		while(scanner.hasNextLine()){
			String line = scanner.nextLine();
			if (line.length() < 4 || line.length() > 9 || line.matches("-")){
				// do nothing
			} else {
				pw.println(line);
			}
		}
	}
}
