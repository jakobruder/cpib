package ch.fhnw.cpib.parser.interfaces;

import ch.fhnw.cpib.parser.IConcTree;

public interface IParser {

	static class GrammarError extends Exception {
		/**
		 * 
		 */
		private static final long serialVersionUID = -1907360735441659827L;

		public GrammarError(String errorMessage) {
			super(errorMessage);
		}
	}

	public IConcTree.IProgram parse() throws GrammarError;
}