package ch.fhnw.cpib.scanner;

public class Ident extends Base {

	private final String ident;
	
	public Ident(Terminals t, String ident) {
		super(t);
		this.ident = ident;
	}
	
	public String getIdent() {
		return ident;
	}
	
	public String toString() {
		return ident;
	}
	
}
