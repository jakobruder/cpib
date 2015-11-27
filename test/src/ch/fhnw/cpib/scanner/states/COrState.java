package ch.fhnw.cpib.scanner.states;

import ch.fhnw.cpib.scanner.enums.Operators;
import ch.fhnw.cpib.scanner.exceptions.ScannerException;
import ch.fhnw.cpib.scanner.interfaces.IScannerState;
import ch.fhnw.cpib.scanner.interfaces.ITokenList;
import ch.fhnw.cpib.scanner.symbols.BoolOr;

public class COrState implements IScannerState {

	@Override
	public IScannerState handleCharacter(String ch, ITokenList list)
			throws ScannerException {
		list.add(new BoolOr(Operators.COR));
		return new DefaultState().handleCharacter(ch, list);
	}

}
