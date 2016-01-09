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
		String test = "program isEligibleForMaster ()"
				+ "global "
				+ "credits: int64; "
				+ "gradeTimesTen: int64; "
				+ "age: int64; "
				+ "recommendation: bool "
				+ "do "
				+ "debugin credits init; "
				+ "debugin gradeTimesTen init; "
				+ "debugin age init; "
				+ "debugin recommendation init; "
				+ "if credits > 150 &? gradeTimesTen > 50 &? (age < 35 |? recommendation) then "
				+ "debugout true " + "else " + "debugout false endif "
				+ "endprogram";
		String test2 = "program isSorted () " + "global " + "a: int64; "
				+ "b: int64; " + "c: int64 " + "do " + "debugin a init; "
				+ "debugin b init; " + "debugin c init; "
				+ "if ( a <= b <= c ) then " + "debugout true " + "else "
				+ "if ( a >= b >= c ) then " + "debugout true " + "else "
				+ "debugout false " + "endif " + "endif " + "endprogram";
		Scanner scanner = new Scanner();
		IParser parser = new Parser(scanner.scan(test2.subSequence(0,
				test2.length())));
		IAbsTree.Program tree = parser.parse().toAbs();
		tree.check();
		ICodeArray array = tree.generateCode();
		VirtualMachine vm = new VirtualMachine(array, 500);
		System.out.println("test");

	}

}
