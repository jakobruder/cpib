package ch.fhnw.cpib.scanner.symbols;

import ch.fhnw.cpib.scanner.enums.FlowMode;
import ch.fhnw.cpib.scanner.enums.Terminals;

public class FlowModeToken extends Base{

	private FlowMode mode;
	
	public FlowModeToken(FlowMode mode) {
		super(Terminals.FLOWMODE);
		this.mode = mode;
	}
	
	public FlowMode getFlowMode() {
		return mode;
	}
	
	public String toString() {
		return "(" + super.toString() + ", " + mode + ")";
	}

}
