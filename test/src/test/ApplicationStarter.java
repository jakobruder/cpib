package test;

import java.util.EnumSet;

import ch.fhnw.cpib.scanner.enums.Keywords;

public class ApplicationStarter {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		for(Keywords k : EnumSet.allOf(Keywords.class))
			System.out.println(k);
	}

}
