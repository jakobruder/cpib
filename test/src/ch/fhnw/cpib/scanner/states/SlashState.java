package ch.fhnw.cpib.scanner.states;

import ch.fhnw.cpib.scanner.exceptions.ScannerException;
import ch.fhnw.cpib.scanner.interfaces.IScannerState;
import ch.fhnw.cpib.scanner.interfaces.ITokenList;

public class SlashState implements IScannerState {

	@Override
	public IScannerState handleCharacter(String ch, ITokenList list)
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
