package ch.fhnw.cpib.scanner.enums;

public enum Value {
	
	BOOL("BOOL"), INT64("INT");
	
	Value(String toString) {
		this.toString = toString;
	}

	private String toString;

	@Override
	public String toString() {
		return toString;
	}
}
