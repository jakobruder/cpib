package ch.fhnw.cpib.scanner;

public class AddOpr extends Base {

	private final Operators opr;
	
	public AddOpr(Terminals t, Operators opr) {
		super(t);
		this.opr = opr;
	}
	
}
