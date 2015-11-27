package ch.fhnw.cpib.scanner.states;

import ch.fhnw.cpib.scanner.enums.Operators;
import ch.fhnw.cpib.scanner.exceptions.ScannerException;
import ch.fhnw.cpib.scanner.interfaces.IScannerState;
import ch.fhnw.cpib.scanner.interfaces.ITokenList;
import ch.fhnw.cpib.scanner.symbols.RelOpr;

public class LessState implements IScannerState {

	@Override
	public IScannerState handleCharacter(String ch, ITokenList list)
			throws ScannerException {
		if (ch.matches("=")) {
			return new LeState();
		} else {
			list.add(new RelOpr(Operators.LT));
			return new DefaultState().handleCharacter(ch, list);
		}
	}

}
