package ch.fhnw.cpib.parser.interfaces;

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

	public IProgram parse() throws GrammarError;
}