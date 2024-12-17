package output;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class ParserTest {
    public static void main(String[] args) throws Exception {
        String input = "MyCircle : create Circle red"; // Replace with sample DSL input
        CharStream charStream = CharStreams.fromString(input);

        // Create the lexer and token stream
        DSL1Lexer lexer = new DSL1Lexer(charStream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create the parser
        DSL1Parser parser = new DSL1Parser(tokens);

        // Start parsing from the top rule (adjust 'startRule' to your grammar's entry point)
        ParseTree tree = parser.program(); // Replace 'startRule' with your grammar's start rule
        System.out.println(tree.toStringTree(parser)); // Print the parse tree
    }
}
