package ch.fhnw.cpib.scanner.states;

import java.util.LinkedList;

import ch.fhnw.cpib.scanner.exceptions.ScannerException;
import ch.fhnw.cpib.scanner.interfaces.IToken;
import ch.fhnw.cpib.scanner.interfaces.IScannerState;

public class SlashState implements IScannerState {

	@Override
	public IScannerState handleCharacter(String ch, LinkedList<IToken> list)
			throws ScannerException {
		if (ch.matches("/")) {
			return new CommentState();
		} else if (ch.matches("=")) {
			return new NotEqualState();
		} else {
			throw new ScannerException("Invalid Character in Slash State: "
					+ ch);
		}
	}

}
