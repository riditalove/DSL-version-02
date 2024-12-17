package output;
// Generated from C:/Users/HP/Desktop/8-12-2024/src/DSL1.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link DSL1Parser}.
 */
public interface DSL1Listener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link DSL1Parser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(DSL1Parser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSL1Parser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(DSL1Parser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link DSL1Parser#statements}.
	 * @param ctx the parse tree
	 */
	void enterStatements(DSL1Parser.StatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSL1Parser#statements}.
	 * @param ctx the parse tree
	 */
	void exitStatements(DSL1Parser.StatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link DSL1Parser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(DSL1Parser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link DSL1Parser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(DSL1Parser.StatementContext ctx);
}