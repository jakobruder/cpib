package ch.fhnw.cpib.scanner.states;

import java.util.LinkedList;

import ch.fhnw.cpib.scanner.enums.Operators;
import ch.fhnw.cpib.scanner.exceptions.ScannerException;
import ch.fhnw.cpib.scanner.interfaces.IToken;
import ch.fhnw.cpib.scanner.symbols.RelOpr;
import ch.fhnw.cpib.scanner.interfaces.IScannerState;

public class NotEqualState implements IScannerState {

	@Override
	public IScannerState handleCharacter(String ch, LinkedList<IToken> list)
			throws ScannerException {
		list.add(new RelOpr(Operators.NE));
		return new DefaultState().handleCharacter(ch, list);
	}

}
