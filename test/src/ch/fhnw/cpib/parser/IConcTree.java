package ch.fhnw.cpib.parser;

import ch.fhnw.cpib.scanner.Ident;
import ch.fhnw.cpib.scanner.interfaces.IToken;
import ch.fhnw.cpib.scanner.symbols.AddOpr;
import ch.fhnw.cpib.scanner.symbols.BoolAnd;
import ch.fhnw.cpib.scanner.symbols.BoolOr;
import ch.fhnw.cpib.scanner.symbols.Comma;
import ch.fhnw.cpib.scanner.symbols.MultOpr;
import ch.fhnw.cpib.scanner.symbols.RelOpr;

public interface IConcTree {
	
	public interface IExpr {
		public IExpr toAbsSyn();

	}

	public interface IExprbool {
		public IExpr toAbsSyn();

	}

	public interface IRepaddoprterm3 {
		public IExpr toAbsSyn();

	}

	public interface ITerm3 {
		public IExpr toAbsSyn();

	}

	public interface IRepmultoprfactor {
		public IExpr toAbsSyn();

	}

	public interface IFactor {
		public IExpr toAbsSyn();

	}

	public interface IFactorop {
		public IExpr toAbsSyn();

	}

	public interface ITerm2 {
		public IExpr toAbsSyn();

	}

	public interface ITerm1 {
		public IExpr toAbsSyn();

	}

	public interface IExprList {
		public IExpr toAbsSyn();

	}

	public interface IMonadicOpr {
		public IExpr toAbsSyn();

	}

	public interface ITerm2op {
		public IExpr toAbsSyn();

	}

	public interface ITerm1opor {
		public IExpr toAbsSyn();

	}

	public interface ITerm1opand {
		public IExpr toAbsSyn();

	}

	public interface IExprListop {
		public IExpr toAbsSyn();

	}

	public interface IExprListopop {
		public IExpr toAbsSyn();

	}

	public interface IProgram {
		public IExpr toAbsSyn();

	}

	public interface IProgParamList {
		public IExpr toAbsSyn();

	}

	public interface IProgParamListop {
		public IExpr toAbsSyn();

	}

	public interface IProgParamListopop {
		public IExpr toAbsSyn();

	}

	public interface ICpsDecl {
		public IExpr toAbsSyn();

	}

	public interface ICpsDeclop {
		public IExpr toAbsSyn();

	}

	public interface ICpsCmd {
		public IExpr toAbsSyn();

	}

	public interface ICpsCmdop {
		public IExpr toAbsSyn();

	}

	public interface IProgramop {
		public IExpr toAbsSyn();

	}

	public interface IDecl {
		public IExpr toAbsSyn();

	}

	public interface IFunDecl {
		public IExpr toAbsSyn();

	}

	public interface IFunDeclop1 {
		public IExpr toAbsSyn();

	}

	public interface IFunDeclop2 {
		public IExpr toAbsSyn();

	}

	public interface IProcDecl {
		public IExpr toAbsSyn();

	}

	public interface IStoDecl {
		public IExpr toAbsSyn();

	}

	public interface ITypedIdent {
		public IExpr toAbsSyn();

	}

	public interface IParamList {
		public IExpr toAbsSyn();

	}

	public interface IParamListop {
		public IExpr toAbsSyn();

	}

	public interface IParamListopop {
		public IExpr toAbsSyn();

	}

	public interface IGlobImp {
		public IExpr toAbsSyn();

	}

	public interface IGlobImpop1 {
		public IExpr toAbsSyn();

	}

	public interface IGlobImpop2 {
		public IExpr toAbsSyn();

	}

	public interface IGlobImps {
		public IExpr toAbsSyn();

	}

	public interface IGlobImpsop {
		public IExpr toAbsSyn();

	}

	public interface ICpsStoDecl {
		public IExpr toAbsSyn();

	}

	public interface ICpsStoDeclop {
		public IExpr toAbsSyn();

	}

	public interface IProgParam {
		public IExpr toAbsSyn();

	}

	public interface IParam {
		public IExpr toAbsSyn();

	}

	public interface IParamop {
		public IExpr toAbsSyn();

	}

	public interface ICmd {
		public IExpr toAbsSyn();

	}

	public interface ICmdop {
		public IExpr toAbsSyn();

	}

	public interface IGlobInits {
		public IExpr toAbsSyn();

	}

	public interface IIdents {
		public IExpr toAbsSyn();

	}

	public interface IIdentsop {
		public IExpr toAbsSyn();

	}	

	public class Expr implements IExpr {

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class ExprLParen implements IExpr {
		private ITerm1 term1;
		private IExprbool exprbool;

		public ExprLParen(ITerm1 term1, IExprbool exprbool) {
			super();
			this.term1 = term1;
			this.exprbool = exprbool;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class ExprAddopr implements IExpr {
		private ITerm1 term1;
		private IExprbool exprbool;

		public ExprAddopr(ITerm1 term1, IExprbool exprbool) {
			super();
			this.term1 = term1;
			this.exprbool = exprbool;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class ExprNot implements IExpr {
		private ITerm1 term1;
		private IExprbool exprbool;

		public ExprNot(ITerm1 term1, IExprbool exprbool) {
			super();
			this.term1 = term1;
			this.exprbool = exprbool;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class ExprIdent implements IExpr {
		private ITerm1 term1;
		private IExprbool exprbool;

		public ExprIdent(ITerm1 term1, IExprbool exprbool) {
			super();
			this.term1 = term1;
			this.exprbool = exprbool;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class ExprBoolOr implements IExpr {
		private BoolOr or;
		private ITerm1 term1;
		private ITerm1opor term1opor;

		public ExprBoolOr(BoolOr or, ITerm1 term1, ITerm1opor term1opor) {
			super();
			this.or = or;
			this.term1 = term1;
			this.term1opor = term1opor;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class ExprBoolAnd implements IExpr {
		private BoolAnd and;
		private ITerm1 term1;
		private ITerm1opand term1opand;

		public ExprBoolAnd(BoolAnd and, ITerm1 term1, ITerm1opand term1opand) {
			super();
			this.and = and;
			this.term1 = term1;
			this.term1opand = term1opand;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class Exprbool implements IExprbool {

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class ExprboolBoolor implements IExprbool {
		private BoolOr or;
		private ITerm1 term1;
		private ITerm1opor term1opor;

		public ExprboolBoolor(BoolOr or, ITerm1 term1, ITerm1opor term1opor) {
			super();
			this.or = or;
			this.term1 = term1;
			this.term1opor = term1opor;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class ExprboolBoolAnd implements IExprbool {
		private BoolAnd and;
		private ITerm1 term1;
		private ITerm1opand term1opand;

		public ExprboolBoolAnd(BoolAnd and, ITerm1 term1, ITerm1opand term1opand) {
			super();
			this.and = and;
			this.term1 = term1;
			this.term1opand = term1opand;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class RepAddoprTerm3 implements IRepaddoprterm3 {
		private AddOpr addopr;
		private ITerm3 term3;
		private IRepaddoprterm3 repaddoprterm3;

		public RepAddoprTerm3(AddOpr addopr, ITerm3 term3, IRepaddoprterm3 repaddoprterm3) {
			super();
			this.addopr = addopr;
			this.term3 = term3;
			this.repaddoprterm3 = repaddoprterm3;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class Term3 implements ITerm3 {

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class Term3LParen implements ITerm3 {
		private IFactor factor;
		private IRepmultoprfactor repmultoprfactor;

		public Term3LParen(IFactor factor, IRepmultoprfactor repmultoprfactor) {
			super();
			this.factor = factor;
			this.repmultoprfactor = repmultoprfactor;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class Term3Addopr implements ITerm3 {
		private IFactor factor;
		private IRepmultoprfactor repmultoprfactor;

		public Term3Addopr(IFactor factor, IRepmultoprfactor repmultoprfactor) {
			super();
			this.factor = factor;
			this.repmultoprfactor = repmultoprfactor;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class Term3Not implements ITerm3 {
		private IFactor factor;
		private IRepmultoprfactor repmultoprfactor;

		public Term3Not(IFactor factor, IRepmultoprfactor repmultoprfactor) {
			super();
			this.factor = factor;
			this.repmultoprfactor = repmultoprfactor;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class Term3Ident implements ITerm3 {
		private IFactor factor;
		private IRepmultoprfactor repmultoprfactor;

		public Term3Ident(IFactor factor, IRepmultoprfactor repmultoprfactor) {
			super();
			this.factor = factor;
			this.repmultoprfactor = repmultoprfactor;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class RepMultoprFactor implements IRepmultoprfactor {
		private MultOpr multopr;
		private IFactor factor;
		private IRepmultoprfactor repmultoprfactor;

		public RepMultoprFactor(MultOpr multopr, IFactor factor, IRepmultoprfactor repmultoprfactor) {
			super();
			this.multopr = multopr;
			this.factor = factor;
			this.repmultoprfactor = repmultoprfactor;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class Factor implements IFactor {

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class FactorLParen implements IFactor {
		private IToken lparen;
		private IExpr expr;
		private IToken rparen;

		public FactorLParen(IToken lparen, IExpr expr, IToken rparen) {
			super();
			this.lparen = lparen;
			this.expr = expr;
			this.rparen = rparen;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class FactorAddopr implements IFactor {
		private IMonadicOpr monadicOpr;
		private IFactor factor;

		public FactorAddopr(IMonadicOpr monadicOpr, IFactor factor) {
			super();
			this.monadicOpr = monadicOpr;
			this.factor = factor;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class FactorNot implements IFactor {
		private IMonadicOpr monadicOpr;
		private IFactor factor;

		public FactorNot(IMonadicOpr monadicOpr, IFactor factor) {
			super();
			this.monadicOpr = monadicOpr;
			this.factor = factor;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class FactorIdent implements IFactor {
		private Ident ident;
		private IFactorop factorop;

		public FactorIdent(Ident ident, IFactorop factorop) {
			super();
			this.ident = ident;
			this.factorop = factorop;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class FactorOp implements IFactorop {

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class FactorOpLParen implements IFactorop {
		private IExprList exprList;

		public FactorOpLParen(IExprList exprList) {
			super();
			this.exprList = exprList;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class FactorOpInit implements IFactorop {
		private IToken init;

		public FactorOpInit(IToken init) {
			super();
			this.init = init;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class Term2 implements ITerm2 {

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class Term2LParen implements ITerm2 {
		private ITerm3 term3;
		private IRepaddoprterm3 repaddoprterm3;

		public Term2LParen(ITerm3 term3, IRepaddoprterm3 repaddoprterm3) {
			super();
			this.term3 = term3;
			this.repaddoprterm3 = repaddoprterm3;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class Term2Addopr implements ITerm2 {
		private ITerm3 term3;
		private IRepaddoprterm3 repaddoprterm3;

		public Term2Addopr(ITerm3 term3, IRepaddoprterm3 repaddoprterm3) {
			super();
			this.term3 = term3;
			this.repaddoprterm3 = repaddoprterm3;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class Term2Not implements ITerm2 {
		private ITerm3 term3;
		private IRepaddoprterm3 repaddoprterm3;

		public Term2Not(ITerm3 term3, IRepaddoprterm3 repaddoprterm3) {
			super();
			this.term3 = term3;
			this.repaddoprterm3 = repaddoprterm3;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class Term2Ident implements ITerm2 {
		private ITerm3 term3;
		private IRepaddoprterm3 repaddoprterm3;

		public Term2Ident(ITerm3 term3, IRepaddoprterm3 repaddoprterm3) {
			super();
			this.term3 = term3;
			this.repaddoprterm3 = repaddoprterm3;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class Term2op implements ITerm2op {
		private RelOpr relopr;
		private ITerm2 term2;
		private ITerm2op term2Op;

		public Term2op(RelOpr relopr, ITerm2 term2, ITerm2op term2Op) {
			super();
			this.relopr = relopr;
			this.term2 = term2;
			this.term2Op = term2Op;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class Term1 implements ITerm1 {

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class Term1LParen implements ITerm1 {
		private ITerm2 term2;
		private ITerm2op term2Op;

		public Term1LParen(ITerm2 term2, ITerm2op term2Op) {
			super();
			this.term2 = term2;
			this.term2Op = term2Op;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class Term1Addopr implements ITerm1 {
		private ITerm2 term2;
		private ITerm2op term2Op;

		public Term1Addopr(ITerm2 term2, ITerm2op term2Op) {
			super();
			this.term2 = term2;
			this.term2Op = term2Op;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class Term1Not implements ITerm1 {
		private ITerm2 term2;
		private ITerm2op term2Op;

		public Term1Not(ITerm2 term2, ITerm2op term2Op) {
			super();
			this.term2 = term2;
			this.term2Op = term2Op;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class Term1Ident implements ITerm1 {
		private ITerm2 term2;
		private ITerm2op term2Op;

		public Term1Ident(ITerm2 term2, ITerm2op term2Op) {
			super();
			this.term2 = term2;
			this.term2Op = term2Op;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class Term1OpOr implements ITerm1opor {
		private BoolOr or;
		private ITerm1 term1;
		private ITerm1opor term1OpOr;

		public Term1OpOr(BoolOr or, ITerm1 term1, ITerm1opor term1OpOr) {
			super();
			this.or = or;
			this.term1 = term1;
			this.term1OpOr = term1OpOr;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class Term1OpAnd implements ITerm1opand {
		private BoolAnd and;
		private ITerm1 term1;
		private ITerm1opand term1OpAnd;

		public Term1OpAnd(BoolAnd and, ITerm1 term1, ITerm1opand term1OpAnd) {
			super();
			this.and = and;
			this.term1 = term1;
			this.term1OpAnd = term1OpAnd;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class ExprList implements IExprList {
		private IToken lparen;
		private IExprListop exprListOp;

		public ExprList(IExprListop exprListOp) {
			super();
			this.exprListOp = exprListOp;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	public class MonadicOpr implements IMonadicOpr {

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	public class MonadicOprAddOpr implements IMonadicOpr {
		private AddOpr add;

		public MonadicOprAddOpr(AddOpr add) {
			super();
			this.add = add;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	public class MonadicOprNot implements IMonadicOpr {
		private IToken not;

		public MonadicOprNot(IToken not) {
			super();
			this.not = not;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	public class ExprListOp implements IExprListop {

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	
	public class ExprListOpLParen implements IExprListop {
		private IToken lparen;
		private IExprListop exprListOp;
		private IToken rparen;

		public ExprListOpLParen(IToken lparen, IExprListop exprListOp, IToken rparen) {
			super();
			this.lparen = lparen;
			this.exprListOp = exprListOp;
			this.rparen = rparen;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class ExprListOpAddOpr implements IExprListop {
		private IToken lparen;
		private IExprListop exprListOp;
		private IToken rparen;

		public ExprListOpAddOpr(IToken lparen, IExprListop exprListOp, IToken rparen) {
			super();
			this.lparen = lparen;
			this.exprListOp = exprListOp;
			this.rparen = rparen;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	public class ExprListOpNot implements IExprListop {
		private IToken lparen;
		private IExprListop exprListOp;
		private IToken rparen;

		public ExprListOpNot(IToken lparen, IExprListop exprListOp, IToken rparen) {
			super();
			this.lparen = lparen;
			this.exprListOp = exprListOp;
			this.rparen = rparen;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	public class ExprListOpIdent implements IExprListop {
		private IToken lparen;
		private IExprListop exprListOp;
		private IToken rparen;

		public ExprListOpIdent(IToken lparen, IExprListop exprListOp, IToken rparen) {
			super();
			this.lparen = lparen;
			this.exprListOp = exprListOp;
			this.rparen = rparen;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	
	public class ExprListOpOp implements IExprListopop {
		private Comma comma;
		private IExpr expr;
		private IExprListopop exprListOpOp;

		public ExprListOpOp(Comma comma, IExpr expr, IExprListopop exprListOpOp) {
			super();
			this.comma = comma;
			this.expr = expr;
			this.exprListOpOp = exprListOpOp;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}
	// TODO: IIdents???
	public class Program implements IProgram {
		private Ident ident;
		private IProgParamList progParamList;
		private IProgramop programop;
		private ICpsCmd cpsCmd;

		public Program(Ident ident, IProgParamList progParamList, IProgramop programop, ICpsCmd cpsCmd) {
			super();
			this.ident = ident;
			this.progParamList = progParamList;
			this.programop = programop;
			this.cpsCmd = cpsCmd;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class ProgParamList implements IProgParamList {
		private IProgParamListop progParamListop;

		public ProgParamList(IProgParamListop progParamListop) {
			this.progParamListop = progParamListop;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class ProgParamListop implements IProgParamListop {
		private IProgParam progParam;
		private IProgParamListopop progParamListopop;

		public ProgParamListop(IProgParam progParam, IProgParamListopop progParamListopop) {

			this.progParam = progParam;
			this.progParamListopop = progParamListopop;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class ProgParamListopopComma implements IProgParamListop {
		private Comma comma;
		private IProgParam progParam;
		private IProgParamListopop progParamListopop;

		public ProgParamListopopComma(Comma comma, IProgParam progParam, IProgParamListopop progParamListopop) {
			super();
			this.comma = comma;
			this.progParam = progParam;
			this.progParamListopop = progParamListopop;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class CpsDecl implements ICpsDecl {
		private IDecl decl;
		private ICpsDeclop cpsDeclop;

		public CpsDecl(IDecl decl, ICpsDeclop cpsDeclop) {
			super();
			this.decl = decl;
			this.cpsDeclop = cpsDeclop;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class CpsDeclopSemicolon implements ICpsDeclop {
		private IToken semicolon;
		private IDecl decl;
		private ICpsDeclop cpsDeclop;

		public CpsDeclopSemicolon(IToken semicolon, IDecl decl, ICpsDeclop cpsDeclop) {
			super();
			this.semicolon = semicolon;
			this.decl = decl;
			this.cpsDeclop = cpsDeclop;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	// TODO: Nothing?
	public class CpsDeclopDo implements ICpsDeclop {

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
		// empty
	}

	public class CpsCmd implements ICpsCmd {
		private ICmd cmd;
		private ICpsCmdop cpsCmdop;

		public CpsCmd(ICmd cmd, ICpsCmdop cpsCmdop) {
			super();
			this.cmd = cmd;
			this.cpsCmdop = cpsCmdop;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class CpsCmdopSemicolon implements ICpsCmdop {
		private IToken semicolon;
		private ICmd cmd;
		private ICpsCmdop cpsCmdop;

		public CpsCmdopSemicolon(IToken semicolon, ICmd cmd, ICpsCmdop cpsCmdop) {
			super();
			this.semicolon = semicolon;
			this.cmd = cmd;
			this.cpsCmdop = cpsCmdop;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	// TODO:
	public class CpsCmdop implements ICpsCmdop {

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
		// empty
	}

	public class ProgramopGlobal implements IProgramop {
		private IToken global;
		private ICpsDecl cpsDecl;

		public ProgramopGlobal(IToken global, ICpsDecl cpsDecl) {
			super();
			this.global = global;
			this.cpsDecl = cpsDecl;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	// TODO:
	public class ProgramopDo implements IProgramop {

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
		// Empty
	}

	public class DeclSto implements IDecl {
		private IStoDecl stoDecl;

		public DeclSto(IStoDecl stoDecl) {
			super();
			this.stoDecl = stoDecl;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class DeclFun implements IDecl {
		private IFunDecl funDecl;

		public DeclFun(IFunDecl funDecl) {
			super();
			this.funDecl = funDecl;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class DeclProc implements IDecl {
		private IProcDecl procDecl;

		public DeclProc(IProcDecl procDecl) {
			super();
			this.procDecl = procDecl;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class FunDecl implements IFunDecl {
		private IToken fun;
		private IToken Ident;
		private IParamList paramList;
		private IToken returns;
		private IStoDecl stoDecl;
		private IFunDeclop1 funDeclop1;
		private IFunDeclop2 funDeclop2;
		private IToken tokenDo;
		private ICpsCmd cpsCmd;
		private IToken endFun;

		public FunDecl(IToken fun, IToken ident, IParamList paramList, IToken returns, IStoDecl stoDecl, IFunDeclop1 funDeclop1, IFunDeclop2 funDeclop2,
				IToken tokenDo, ICpsCmd cpsCmd, IToken endFun) {
			super();
			this.fun = fun;
			Ident = ident;
			this.paramList = paramList;
			this.returns = returns;
			this.stoDecl = stoDecl;
			this.funDeclop1 = funDeclop1;
			this.funDeclop2 = funDeclop2;
			this.tokenDo = tokenDo;
			this.cpsCmd = cpsCmd;
			this.endFun = endFun;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	public class FunDeclop1Global implements IFunDeclop1 {
		private IToken global;
		private IGlobImps globImps;

		public FunDeclop1Global(IToken global, IGlobImps globImps) {
			super();
			this.global = global;
			this.globImps = globImps;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}

	// TODO:
	public class FunDeclop1 implements IFunDeclop1 {

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
		// empty?
	}

	public class FunDeclop2Local implements IFunDeclop2 {
		private IToken local;
		private ICpsStoDecl cpsStoDecl;

		public FunDeclop2Local(IToken local, ICpsStoDecl cpsStoDecl) {
			super();
			this.local = local;
			this.cpsStoDecl = cpsStoDecl;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	// TODO:
	public class FunDeclop2 implements IFunDeclop2 {

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
		// empty?
	}

	public class ProcDecl implements IProcDecl {
		private IToken proc;
		private IToken ident;
		private IParamList paramList;
		private IToken returns;
		private IStoDecl stoDecl;
		private IFunDeclop1 funDeclop1;
		private IFunDeclop2 funDeclop2;
		private IToken tokenDo;
		private ICpsCmd cpsCmd;
		private IToken endProc;

		public ProcDecl(IToken proc, IToken ident, IParamList paramList, IToken returns, IStoDecl stoDecl, IFunDeclop1 funDeclop1, IFunDeclop2 funDeclop2,
				IToken tokenDo, ICpsCmd cpsCmd, IToken endProc) {
			super();
			this.proc = proc;
			this.ident = ident;
			this.paramList = paramList;
			this.returns = returns;
			this.stoDecl = stoDecl;
			this.funDeclop1 = funDeclop1;
			this.funDeclop2 = funDeclop2;
			this.tokenDo = tokenDo;
			this.cpsCmd = cpsCmd;
			this.endProc = endProc;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class StoDeclChangemode implements IStoDecl {
		private IToken Changemode;
		private ITypedIdent typedIdent;

		public StoDeclChangemode(IToken changemode, ITypedIdent typedIdent) {
			super();
			Changemode = changemode;
			this.typedIdent = typedIdent;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class StoDeclIdent implements IStoDecl {
		private ITypedIdent typedIdent;

		public StoDeclIdent(ITypedIdent typedIdent) {
			super();
			this.typedIdent = typedIdent;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class TypedIdent implements ITypedIdent {
		private IToken ident;
		private IToken colon;
		private IToken type;

		public TypedIdent(IToken ident, IToken colon, IToken type) {
			super();
			this.ident = ident;
			this.colon = colon;
			this.type = type;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class ParamList implements IParamList {
		private IToken lParen;
		private IParamListop paramListop;
		private IToken rParen;

		public ParamList(IToken lParen, IParamListop paramListop, IToken rParen) {
			super();
			this.lParen = lParen;
			this.paramListop = paramListop;
			this.rParen = rParen;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class ParamListop implements IParamListop {
		private IParam param;
		private IParamListopop paramListopop;

		public ParamListop(IParam param, IParamListopop paramListopop) {
			super();
			this.param = param;
			this.paramListopop = paramListopop;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	// TODO:
	public class ParamListopRparen implements IParamListop {

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
		// empty?
	}

	public class ParamListopopComma implements IParamListopop {
		private IToken comma;
		private IParam param;
		private IParamListopop paramListopop;

		public ParamListopopComma(IToken comma, IParam param, IParamListopop paramListopop) {
			super();
			this.comma = comma;
			this.param = param;
			this.paramListopop = paramListopop;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	// TODO:
	public class ParamListopopRparen implements IParamListopop {

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
		// empty?
	}

	public class GlobImp implements IGlobImp {
		private IGlobImpop1 globImpop1;
		private IGlobImpop2 globImpop2;
		private IToken ident;

		public GlobImp(IGlobImpop1 globImpop1, IGlobImpop2 globImpop2, IToken ident) {
			super();
			this.globImpop1 = globImpop1;
			this.globImpop2 = globImpop2;
			this.ident = ident;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class GlobImpop1Flowmode implements IGlobImpop1 {
		private IToken flowmode;

		public GlobImpop1Flowmode(IToken flowmode) {
			super();
			this.flowmode = flowmode;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	// TODO:
	public class GlobImpop1 implements IGlobImpop1 {

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
		// empty?
	}

	public class GlobImpop2Changemode implements IGlobImpop2 {
		private IToken changemode;

		public GlobImpop2Changemode(IToken changemode) {
			super();
			this.changemode = changemode;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	// TODO:
	public class GlobImpop2Ident implements IGlobImpop2 {

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
		// empty
	}

	public class GlobImps implements IGlobImps {
		private IGlobImp globImp;
		private IGlobImpsop globImpsop;

		public GlobImps(IGlobImp globImp, IGlobImpsop globImpsop) {
			super();
			this.globImp = globImp;
			this.globImpsop = globImpsop;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class GlobImpsop implements IGlobImpsop {
		private IToken comma;
		private IGlobImp globImp;

		public GlobImpsop(IToken comma, IGlobImp globImp) {
			super();
			this.comma = comma;
			this.globImp = globImp;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class CpsStoDecl implements ICpsStoDecl {
		private IStoDecl stoDecl;
		private ICpsStoDeclop cpsStoDeclop;

		public CpsStoDecl(IStoDecl stoDecl, ICpsStoDeclop cpsStoDeclop) {
			super();
			this.stoDecl = stoDecl;
			this.cpsStoDeclop = cpsStoDeclop;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class CpsStoDeclopSemicolon implements ICpsStoDeclop {
		private IToken semicolon;
		private IStoDecl stoDecl;
		private ICpsStoDeclop cpsStoDeclop;

		public CpsStoDeclopSemicolon(IToken semicolon, IStoDecl stoDecl, ICpsStoDeclop cpsStoDeclop) {
			super();
			this.semicolon = semicolon;
			this.stoDecl = stoDecl;
			this.cpsStoDeclop = cpsStoDeclop;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	// TODO:
	public class CpsStoDeclopDo implements ICpsStoDeclop {

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
		// empty
	}

	public class Param implements IParam {
		private IGlobImpop1 globImpop1;
		private IParamop paramop;
		private IGlobImpop2 globImpop2;
		private ITypedIdent typedIdent;

		public Param(IGlobImpop1 globImpop1, IParamop paramop, IGlobImpop2 globImpop2, ITypedIdent typedIdent) {
			super();
			this.globImpop1 = globImpop1;
			this.paramop = paramop;
			this.globImpop2 = globImpop2;
			this.typedIdent = typedIdent;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class ParamopMechmode implements IParamop {
		private IToken mechmode;

		public ParamopMechmode(IToken mechmode) {
			super();
			this.mechmode = mechmode;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	// TODO:
	public class Paramop implements IParamop {

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
		// empty
	}

	public class CmdSkip implements ICmd {
		private IToken skip;

		public CmdSkip(IToken skip) {
			super();
			this.skip = skip;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class CmdBecomes implements ICmd {
		private IExpr expr1;
		private IToken becomes;
		private IExpr expr2;

		public CmdBecomes(IExpr expr1, IToken becomes, IExpr expr2) {
			super();
			this.expr1 = expr1;
			this.becomes = becomes;
			this.expr2 = expr2;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class CmdIf implements ICmd {
		private IToken tokenIf;
		private IExpr expr;
		private IToken then;
		private ICpsCmd cpsCmd1;
		private IToken tokenElse;
		private ICpsCmd cpsCmd2;
		private IToken endif;

		public CmdIf(IToken tokenIf, IExpr expr, IToken then, ICpsCmd cpsCmd1, IToken tokenElse, ICpsCmd cpsCmd2, IToken endif) {
			super();
			this.tokenIf = tokenIf;
			this.expr = expr;
			this.then = then;
			this.cpsCmd1 = cpsCmd1;
			this.tokenElse = tokenElse;
			this.cpsCmd2 = cpsCmd2;
			this.endif = endif;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class CmdWhile implements ICmd {
		private IToken tokenWhile;
		private IExpr expr;
		private IToken tokenDo;
		private ICpsCmd cpsCmd;
		private IToken endWhile;

		public CmdWhile(IToken tokenWhile, IExpr expr, IToken tokenDo, ICpsCmd cpsCmd, IToken endWhile) {
			super();
			this.tokenWhile = tokenWhile;
			this.expr = expr;
			this.tokenDo = tokenDo;
			this.cpsCmd = cpsCmd;
			this.endWhile = endWhile;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class CmdCall implements ICmd {
		private IToken call;
		private IToken ident;
		private IExprList exprList;
		private ICmdop cmdop;

		public CmdCall(IToken call, IToken ident, IExprList exprList, ICmdop cmdop) {
			super();
			this.call = call;
			this.ident = ident;
			this.exprList = exprList;
			this.cmdop = cmdop;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class CmdDebugin implements ICmd {
		private IToken debugin;

		public CmdDebugin(IToken debugin) {
			super();
			this.debugin = debugin;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class CmdDebugout implements ICmd {
		private IToken debugout;

		public CmdDebugout(IToken debugout) {
			super();
			this.debugout = debugout;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class CmdopInit implements ICmdop {
		private IGlobInits globInits;

		public CmdopInit(IGlobInits globInits) {
			super();
			this.globInits = globInits;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	// TODO:
	public class Cmdop implements ICmdop {

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
		// empty
	}

	public class GlobInits implements IGlobInits {
		private IToken init;
		private IIdents idents;

		public GlobInits(IToken init, IIdents idents) {
			super();
			this.init = init;
			this.idents = idents;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class Idents implements IIdents {
		private IToken ident;
		private IIdentsop identsop;

		public Idents(IToken ident, IIdentsop identsop) {
			super();
			this.ident = ident;
			this.identsop = identsop;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	public class IdentsopComma implements IIdentsop {
		private IToken comma;
		private IToken ident;
		private IIdentsop identsop;

		public IdentsopComma(IToken comma, IToken ident, IIdentsop identsop) {
			super();
			this.comma = comma;
			this.ident = ident;
			this.identsop = identsop;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}

	}

	// TODO:
	public class Identsop implements IIdentsop {
		private IToken ident;
		private IIdentsop identsop;

		public Identsop(IToken ident, IIdentsop identsop) {
			super();
			this.ident = ident;
			this.identsop = identsop;
		}

		@Override
		public IExpr toAbsSyn() {
			// TODO Auto-generated method stub
			return null;
		}
	}
}
