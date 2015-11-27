package ch.fhnw.cpib.scanner;

import java.util.LinkedList;

import ch.fhnw.cpib.scanner.enums.Terminals;
import ch.fhnw.cpib.scanner.exceptions.ScannerException;
import ch.fhnw.cpib.scanner.interfaces.IScannerState;
import ch.fhnw.cpib.scanner.interfaces.IToken;
import ch.fhnw.cpib.scanner.interfaces.ITokenList;
import ch.fhnw.cpib.scanner.states.DefaultState;
import ch.fhnw.cpib.scanner.symbols.Base;

public class Scanner {

	private IScannerState state = new DefaultState();

	public ITokenList scan(CharSequence program) {
		ITokenList tokenList = new TokenList();
		try {
			for (int i = 0; i < program.length(); i++) {
				String currentChar = String.valueOf(program.charAt(i));

				state = state.handleCharacter(currentChar, tokenList);

			}
			// handle end
			state.handleCharacter("", tokenList);
		} catch (ScannerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tokenList.add(new Base(Terminals.SENTINEL));
		return tokenList;
	}

	public static void main(String[] args) {

	}
}
