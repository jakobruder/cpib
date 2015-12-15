package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.scanner.Ident;
import ch.fhnw.cpib.scanner.interfaces.IToken;
import ch.fhnw.cpib.scanner.symbols.Comma;

public interface IConcTree {
	public interface IExpr {

	}

	public interface IExprbool {

	}

	public interface IRepaddoprterm3 {

	}

	public interface ITerm3 {

	}

	public interface IRepmultoprfactor {

	}

	public interface IFactor {

	}

	public interface IFactorop {

	}

	public interface ITerm2 {

	}

	public interface ITerm1 {

	}

	public interface IExprList {

	}

	public interface IMonadicOpr {

	}

	public interface ITerm2op {

	}

	public interface ITerm1opor {

	}

	public interface ITerm1opand {

	}

	public interface IEprListop {

	}

	public interface IExprListopop {

	}

	public interface IProgram {

	}

	public interface IProgParamList {

	}

	public interface IProgParamListop {

	}

	public interface IProgParamListopop {

	}

	public interface ICpsDecl {

	}

	public interface ICpsDeclop {

	}

	public interface ICpsCmd {

	}

	public interface ICpsCmdop {

	}

	public interface IProgramop {

	}

	public interface IDecl {

	}

	public interface IFunDecl {

	}

	public interface IFunDeclop1 {

	}

	public interface IFunDeclop2 {

	}

	public interface IProcDecl {

	}

	public interface IStoDecl {

	}

	public interface ITypedIdent {

	}

	public interface IParamList {

	}

	public interface IParamListop {

	}

	public interface IParamListopop {

	}

	public interface IGlobImp {

	}

	public interface IGlobImpop1 {

	}

	public interface IGlobImpop2 {

	}

	public interface IGlobImps {

	}

	public interface IGlobImpsop {

	}

	public interface ICpsStoDecl {

	}

	public interface ICpsStoDeclop {

	}

	public interface IProgParam {

	}

	public interface IParam {

	}

	public interface IParamop {

	}

	public interface ICmd {

	}

	public interface ICmdop {

	}

	public interface IGlobInits {

	}

	public interface IIdents {

	}

	public interface IIdentsop {

	}

	public class Program implements IProgram {
		private Ident ident;
		private IProgParamList progParamList;
		private IProgramop programop;
		private ICpsCmd cpsCmd;

		public Program(Ident ident, IProgParamList progParamList,
				IProgramop programop, ICpsCmd cpsCmd) {
			super();
			this.ident = ident;
			this.progParamList = progParamList;
			this.programop = programop;
			this.cpsCmd = cpsCmd;
		}

	}

	public class ProgParamList implements IProgParamList {
		private IProgParamListop progParamListop;

		public ProgParamList(IProgParamListop progParamListop) {
			this.progParamListop = progParamListop;
		}
	}

	public class ProgParamListop implements IProgParamListop {
		private IProgParam progParam;
		private IProgParamListopop progParamListopop;

		public ProgParamListop(IProgParam progParam,
				IProgParamListopop progParamListopop) {

			this.progParam = progParam;
			this.progParamListopop = progParamListopop;
		}

	}

	public class ProgParamListopopComma implements IProgParamListop {
		private Comma comma;
		private IProgParam progParam;
		private IProgParamListopop progParamListopop;

		public ProgParamListopopComma(Comma comma, IProgParam progParam,
				IProgParamListopop progParamListopop) {
			super();
			this.comma = comma;
			this.progParam = progParam;
			this.progParamListopop = progParamListopop;
		}

	}
}
