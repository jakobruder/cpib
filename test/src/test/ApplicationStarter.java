package test;

import ch.fhnw.cpib.checker.ContextError;
import ch.fhnw.cpib.parser.IAbsTree;
import ch.fhnw.cpib.parser.Parser;
import ch.fhnw.cpib.parser.interfaces.IParser;
import ch.fhnw.cpib.parser.interfaces.IParser.GrammarError;
import ch.fhnw.cpib.scanner.Scanner;
import ch.fhnw.cpib.virtualMachine.ICodeArray;
import ch.fhnw.cpib.virtualMachine.ICodeArray.CodeTooSmallError;
import ch.fhnw.cpib.virtualMachine.IVirtualMachine.ExecutionError;
import ch.fhnw.cpib.virtualMachine.VirtualMachine;

public class ApplicationStarter {

	public static void main(String[] args) throws GrammarError, ContextError,
			CodeTooSmallError, ExecutionError {
		String test = "program testAndOr () " + "global " + "const m: bool; "
				+ "const n: bool; " + "const q: bool " + "do "
				+ "debugin m init; " + "debugin n init; " + "debugin q init;"
				+ "debugout ( m &? n &? q ); " + "debugout ( m |? n |? q ); "
				+ "debugout ( ( m &? n ) |? q ); "
				+ "debugout ( m &? ( n |? q ) ) endprogram";
		Scanner scanner = new Scanner();
		IParser parser = new Parser(scanner.scan(test.subSequence(0,
				test.length())));
		IAbsTree.Program tree = parser.parse().toAbs();
		tree.check();
		ICodeArray array = tree.generateCode();
		VirtualMachine vm = new VirtualMachine(array, 500);
		System.out.println("test");

	}

}
