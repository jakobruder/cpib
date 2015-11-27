package ch.fhnw.cpib.scanner.enums;

public enum Value {
	
	BOOLVAL("BOOL"), INT64VAL("INT");
	
	Value(String toString) {
		this.toString = toString;
	}

	private String toString;

	@Override
	public String toString() {
		return toString;
	}
}
