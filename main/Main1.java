package main;
import output.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.json.JSONObject;

import java.io.IOException;

public class Main1 {
    public static void main(String[] args) throws Exception {
        // Take the input from the command line or hardcoded string
        String inputCode = "MyRect: create Rectangle red (MyCircle: create Circle blue)";

        // Step 1: Create a CharStream from the input string
        CharStream input = CharStreams.fromString(inputCode);

        // Step 2: Create the lexer
        DSL1Lexer lexer = new DSL1Lexer(input);

        // Step 3: Tokenize the input using a token stream
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Step 4: Create the parser
        DSL1Parser parser = new DSL1Parser(tokens);

        // Step 5: Parse the input starting from the 'program' rule
        ParseTree tree = parser.program();

        // Step 6: Print the parse tree (for debugging purposes)
        System.out.println("Parse Tree:");
        System.out.println(tree.toStringTree(parser));

        // Step 7: Visit the tree and generate the AST
        MyVisitor visitor = new MyVisitor();
        ASTNode ast = visitor.visit(tree);

        // Step 8: Convert the AST to JSON
        JSONObject jsonAst = ASTtoJson.convertToJson(ast);

        // Step 9: Output the JSON AST
        System.out.println("\nAST as JSON:");
        System.out.println(jsonAst.toString(4));  // Pretty print the JSON
    }
}
