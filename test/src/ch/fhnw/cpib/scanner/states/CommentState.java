package ch.fhnw.cpib.scanner.states;

import java.util.LinkedList;

import ch.fhnw.cpib.scanner.exceptions.ScannerException;
import ch.fhnw.cpib.scanner.interfaces.IToken;
import ch.fhnw.cpib.scanner.interfaces.IScannerState;

public class CommentState implements IScannerState {

	@Override
	public IScannerState handleCharacter(String ch, LinkedList<IToken> list)
			throws ScannerException {
		if (ch.matches("/n")) {
			return new DefaultState();
		} else {
			return new CommentState();
		}
	}

}
