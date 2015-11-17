package ch.fhnw.cpib.scanner;

public class MultOpr extends Base {

	private final Operators operator;

	MultOpr(Terminals t, Operators op) {
		super(t.MULTOPR);
		operator = op;
		// TODO Auto-generated constructor stub
	}

	public Operators getOperator() {
		return operator;
	}
	
	public String toString(){
		return operator.toString();
	}

}
