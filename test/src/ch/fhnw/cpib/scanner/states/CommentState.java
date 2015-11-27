package ch.fhnw.cpib.scanner.states;

import ch.fhnw.cpib.scanner.exceptions.ScannerException;
import ch.fhnw.cpib.scanner.interfaces.IScannerState;
import ch.fhnw.cpib.scanner.interfaces.ITokenList;

public class CommentState implements IScannerState {

	@Override
	public IScannerState handleCharacter(String ch, ITokenList list)
			throws ScannerException {
		if (ch.matches("\n")) {
			return new DefaultState();
		} else {
			return new CommentState();
		}
	}

}
