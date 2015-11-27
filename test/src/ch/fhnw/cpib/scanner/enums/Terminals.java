package ch.fhnw.cpib.scanner.enums;

public enum Terminals {
	LPAREN("LPAREN"), RPAREN("RPAREN"), LBRACE("LBRACE"), RBRACE("RBRACE"),

	COMMA("COMMA"), SEMICOLON("SEMICOLON"), COLON("COLON"),

	QUESTMARK("QUESTMARK"), EXCLAMARK("EXCLAMARK"), BECOMES("BECOMES"),

	MULTOPR("MULTOPR"), ADDOPR("ADDOPR"), RELOPR("RELOPR"), BOOLAND("BOOLAND"), BOOLOR("BOOLOR"),

	LITERAL("LITERAL"), IDENT("IDENT"), TYPE("TYPE"),

	FLOWMODE("FLOWMODE"), CHANGEMODE("CHANGEMODE"), MECHMODE("MECHMODE"),

	SENTINEL("SENTINEL"),

	BOOL("BOOL"), CALL("CALL"), CONST("CONST"), COPY("COPY"), DEBUGOUT("DEBUGOUT"), DEBUGIN("DEBUGIN"),

	DIVE("DIV_E"), DO("DO"), ELSE("ELSE"), ENDFUN("ENDFUN"), ENDIF("ENDIF"), ENDPROC("ENDPROC"),

	ENDPROGRAM("ENDPROGRAM"), ENDWHILE("ENDWHILE"), FALSE("FALSE"), FUN("FUN"), GLOBAL("GLOBAL"),

	IF("IF"), INIT("INIT"), INT64("INT64"), LOCAL("LOCAL"), MODE("MOD_E"), NOT("NOT"),

	PROC("PROC"), PROGRAM("PROGRAM"), REF("REF"), RETURNS("RETURNS"), SKIP("SKIP"), THEN("THEN"),

	TRUE("TRUE"), VAR("VAR"), WHILE("WHILE"), VALUE("VALUE");

	Terminals(String toString) {
		this.toString = toString;
	}

	private String toString;

	@Override
	public String toString() {
		return toString;
	}
}
