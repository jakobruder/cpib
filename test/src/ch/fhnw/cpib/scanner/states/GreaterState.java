package ch.fhnw.cpib.scanner.states;

import java.util.LinkedList;

import ch.fhnw.cpib.scanner.enums.Operators;
import ch.fhnw.cpib.scanner.exceptions.ScannerException;
import ch.fhnw.cpib.scanner.interfaces.IScannerState;
import ch.fhnw.cpib.scanner.interfaces.IToken;
import ch.fhnw.cpib.scanner.symbols.RelOpr;

public class GreaterState implements IScannerState {

	@Override
	public IScannerState handleCharacter(String ch, LinkedList<IToken> list)
			throws ScannerException {
		if (ch.matches("=")) {
			return new GeState();
		} else {
			list.add(new RelOpr(Operators.GT));
			return new DefaultState().handleCharacter(ch, list);
		}
	}

}
