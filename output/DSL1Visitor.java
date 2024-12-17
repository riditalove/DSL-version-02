package output;

// Generated from C:/Users/HP/Desktop/8-12-2024/src/DSL1.g4 by ANTLR 4.13.2
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link DSL1Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface DSL1Visitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link DSL1Parser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(DSL1Parser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link DSL1Parser#statements}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatements(DSL1Parser.StatementsContext ctx);
	/**
	 * Visit a parse tree produced by {@link DSL1Parser#statement}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatement(DSL1Parser.StatementContext ctx);
}