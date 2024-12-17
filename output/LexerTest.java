package output;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.CommonTokenStream;

public class LexerTest {
    public static void main(String[] args) throws Exception {
        String input = "MyCircle : create Circle red (MyRect : create Rectangle blue)"; // Replace with sample DSL input
        CharStream charStream = CharStreams.fromString(input);

        // Create the lexer
        DSL1Lexer lexer = new DSL1Lexer(charStream);

        // Fetch tokens and print them
        for (Token token : lexer.getAllTokens()) {
            System.out.println("Token: " + token.getText() + ", Type: " + lexer.getVocabulary().getSymbolicName(token.getType()));
        }
    }
}
