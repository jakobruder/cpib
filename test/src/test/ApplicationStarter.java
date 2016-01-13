package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

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

	public static void main(String[] args)
			throws GrammarError, ContextError, CodeTooSmallError, ExecutionError, IOException {

		String program = readFromFile("testIML.txt");
		
		
		
//		String test = "program isEligibleForMaster ()" + "global " + "credits: int64; " + "gradeTimesTen: int64; "
//				+ "age: int64; " + "recommendation: bool " + "do " + "debugin credits init; "
//				+ "debugin gradeTimesTen init; " + "debugin age init; " + "debugin recommendation init; "
//				+ "if credits > 150 &? gradeTimesTen > 50 &? (age < 35 |? recommendation) then " + "debugout true "
//				+ "else " + "debugout false endif " + "endprogram";
//		String test2 = "program isSorted () " + "global " + "a: int64; " + "b: int64; " + "c: int64 " + "do "
//				+ "debugin a init; " + "debugin b init; " + "debugin c init; " + "if ( a <= b <= c ) then "
//				+ "debugout true " + "else " + "if ( a >= b >= c ) then " + "debugout true " + "else "
//				+ "debugout false " + "endif " + "endif " + "endprogram";
//		String test3 = "program isSorted ()" + " global" + " a: int64;" + " b: int64;" + " c: int64;"
//				+ " proc sorted(in copy d:int64, in copy e:int64, in copy f:int64)" + " do" + " if ( d <= e <= f ) then"
//				+ " debugout true" + " else" + " if ( d >= e >= f ) then" + " debugout true" + " else"
//				+ " debugout false" + " endif" + " endif" + " endproc" + " do" + " debugin a init;" + " debugin b init;"
//				+ " debugin c init;" + " call sorted(a, b, c)" + " endprogram";

		Scanner scanner = new Scanner();
		IParser parser = new Parser(scanner.scan(program.subSequence(0, program.length())));
		IAbsTree.Program tree = parser.parse().toAbs();
		tree.check();
		ICodeArray array = tree.generateCode();
		VirtualMachine vm = new VirtualMachine(array, 500);
		System.out.println("test");

	}

	public static String readFromFile(String url) throws IOException, FileNotFoundException {
		BufferedReader br = new BufferedReader(new FileReader(new File(url)));
		String program = "";
		String s = "";
		while ((s = br.readLine()) != null) {
			program += s += " ";
		}
		
		program = program.replaceAll("\t", " ");
		br.close();
		return program;

	}

}