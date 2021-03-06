package ch.fhnw.cpib.scanner.states;

import ch.fhnw.cpib.scanner.enums.Operators;
import ch.fhnw.cpib.scanner.exceptions.ScannerException;
import ch.fhnw.cpib.scanner.interfaces.IScannerState;
import ch.fhnw.cpib.scanner.interfaces.ITokenList;
import ch.fhnw.cpib.scanner.symbols.RelOpr;

public class GeState implements IScannerState {

	@Override
	public IScannerState handleCharacter(String ch, ITokenList list)
			throws ScannerException {
		list.add(new RelOpr(Operators.GE));
		return new DefaultState().handleCharacter(ch, list);
	}

}
