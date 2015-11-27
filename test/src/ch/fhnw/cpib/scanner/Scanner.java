package ch.fhnw.cpib.scanner;

import java.util.LinkedList;

import ch.fhnw.cpib.scanner.enums.ChangeMode;
import ch.fhnw.cpib.scanner.enums.MechMode;
import ch.fhnw.cpib.scanner.enums.Operators;
import ch.fhnw.cpib.scanner.enums.Terminals;
import ch.fhnw.cpib.scanner.exceptions.ScannerException;
import ch.fhnw.cpib.scanner.interfaces.IScannerState;
import ch.fhnw.cpib.scanner.interfaces.IToken;
import ch.fhnw.cpib.scanner.states.DefaultState;
import ch.fhnw.cpib.scanner.symbols.Base;
import ch.fhnw.cpib.scanner.symbols.ChangeModeToken;
import ch.fhnw.cpib.scanner.symbols.MechModeToken;
import ch.fhnw.cpib.scanner.symbols.MultOpr;

public class Scanner {

	private IScannerState state = new DefaultState();

	public LinkedList<IToken> scan(CharSequence program) {
		LinkedList<IToken> tokenList = new LinkedList<>();
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
		Scanner s = new Scanner();
		CharSequence program = "asdf := /= ; 123 + - + * 123123abab< <= > >= :=";
		LinkedList<IToken> list = s.scan(program);

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
}
