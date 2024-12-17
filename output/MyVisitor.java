package output;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTree;

public class MyVisitor extends DSL1BaseVisitor<ASTNode> {

    @Override
    public ASTNode visitProgram(DSL1Parser.ProgramContext ctx) {
        ASTNode programNode = new ASTNode("Program", "", "", "", null);
        for (DSL1Parser.StatementContext stmt : ctx.statements().statement()) {
            programNode.addChild(visit(stmt));
        }
        return programNode;
    }

    @Override
    public ASTNode visitStatement(DSL1Parser.StatementContext ctx) {
        ASTNode statementNode = new ASTNode("Statement", "", "", "", null);
        statementNode.setName(ctx.NAME().getText());
        statementNode.setAction(ctx.ACTION().getText());
        statementNode.setShape(ctx.SHAPE().getText());
        if (ctx.COLOR() != null) {
            statementNode.setColor(ctx.COLOR().getText());
        }

        if (ctx.LPAREN() != null) {
            // Process nested statements within parentheses
            for (DSL1Parser.StatementContext childStmt : ctx.statements().statement()) {
                statementNode.addChild(visit(childStmt));
            }
        }

        return statementNode;
    }
}
