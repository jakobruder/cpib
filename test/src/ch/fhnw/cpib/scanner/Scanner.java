package ch.fhnw.cpib.scanner;

import java.util.LinkedList;

import ch.fhnw.cpib.scanner.exceptions.ScannerException;
import ch.fhnw.cpib.scanner.interfaces.IScannerState;
import ch.fhnw.cpib.scanner.interfaces.IToken;
import ch.fhnw.cpib.scanner.states.DefaultState;

public class Scanner {

	private IScannerState state = new DefaultState();

	private static final String[] ALL_SYMBOLS = new String[] { "(", ")", ",",
			";", ":", ":=", "*", "+", "-", "=", "/=", "<", ">", "<=", ">=",
			"&?", "|?" };

	public LinkedList<IToken> scan(CharSequence program) {
		LinkedList<IToken> tokenList = new LinkedList<>();
		StringBuffer currentString = new StringBuffer();

		for (int i = 0; i < program.length(); i++) {
			String currentChar = String.valueOf(program.charAt(i));
			try {
				state = state.handleCharacter(currentChar, tokenList);
			} catch (ScannerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return tokenList;
	}

	public static void main(String[] args) {
		Scanner s = new Scanner();
		CharSequence program = "asdf := /= ; 123 + - + * 123123abab< <= > >= :=";
		LinkedList<IToken> list = s.scan(program);

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
