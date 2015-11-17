package ch.fhnw.cpib.scanner;

public class RelOpr extends Base {

	private final Operators relOpr;

	RelOpr(Terminals t, Operators relOpr) {
		super(t.RELOPR);
		this.relOpr = relOpr;
		// TODO Auto-generated constructor stub
	}

	public Operators getRelOpr() {
		return relOpr;
	}

}
