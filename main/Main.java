package main;
import output.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;

public class Main {
    public static void main(String[] args) throws Exception {
        // Check if input file is provided as a command-line argument
        // Input DSL code as a string
        String inputCode = "MyRect: create Rectangle red (MyCircle: create Circle blue)";

        // Create a CharStream from the input
        CharStream input = CharStreams.fromString(inputCode);


        // Create the lexer
        DSL1Lexer lexer = new DSL1Lexer(input);

        // Create a token stream from the lexer
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Create the parser using the token stream
        DSL1Parser parser = new DSL1Parser(tokens);

        // Start parsing from the 'program' rule (you can change this if needed)
        ParseTree tree = parser.program();

        // Print the parse tree (for debugging purposes)
        System.out.println("Parse Tree:");
        System.out.println(tree.toStringTree(parser));

        // Create the AST using a custom visitor
//        MyVisitor visitor = new MyVisitor();
//        ASTNode ast = visitor.visit(tree);
//
//        // Print the AST
//        System.out.println("\nAbstract Syntax Tree (AST):");
//        System.out.println(ast);
    }
}


