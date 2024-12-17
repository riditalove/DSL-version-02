package main;
import output.*;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Main3 {

    public static void main(String[] args) throws Exception {
        // Take the input from the command line or hardcoded string (DSL input)
        String inputCode = "MyRect: create Rectangle yellow  " +
                "(MyCircle: create Circle blue  " +
                "(MyCirc: create Circle pink ))";

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
        JSONObject jsonAst = convertToJson(ast);

        // Step 9: Convert the JSON AST to String and print it (for debugging)
        String jsonAstString = jsonAst.toString(4);  // Pretty print the JSON

        System.out.println("\nAST as JSON (String format):");
        System.out.println(jsonAstString);

        // Step 10: Convert the JSON to SVG and write to file
        String svgContent = convertJsonToSvg(jsonAst);
        writeSvgToFile(svgContent, "output.svg");
    }

    // Convert the ASTNode to JSON format recursively
    public static JSONObject convertToJson(ASTNode node) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", node.getName());
        jsonObject.put("action", node.getAction());
        jsonObject.put("shape", node.getShape());
        jsonObject.put("color", node.getColor());

        if (node.getNested() != null && !node.getNested().isEmpty()) {
            JSONArray childrenArray = new JSONArray();
            for (ASTNode child : node.getNested()) {
                childrenArray.put(convertToJson(child));
            }
            jsonObject.put("nested", childrenArray);
        }

        return jsonObject;
    }

    // Method to extract shapes from the JSON AST and convert to SVG
    public static String convertJsonToSvg(JSONObject jsonAst) {
        StringBuilder svgContent = new StringBuilder();

        // Add the SVG header with XML declaration and SVG namespace
        svgContent.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        svgContent.append("<svg xmlns=\"http://www.w3.org/2000/svg\" version=\"1.1\" width=\"500\" height=\"500\">\n");

        // Process the AST recursively
        processNode(jsonAst, svgContent, 20, 20, 0);

        // Add SVG footer
        svgContent.append("</svg>\n");

        return svgContent.toString();
    }

    // Recursive method to process each node in the AST and generate SVG shapes
    public static void processNode(JSONObject node, StringBuilder svgContent, int x, int y, int offset) {
        // Extract shape information
        String shape = node.getString("shape");
        String color = node.getString("color");

        // Generate different shapes based on the type
        if ("Rectangle".equalsIgnoreCase(shape)) {
            svgContent.append(String.format("<rect x=\"%d\" y=\"%d\" width=\"100\" height=\"50\" fill=\"%s\" stroke=\"black\" stroke-width=\"2\"/>\n", x + offset, y, color));
        } else if ("Circle".equalsIgnoreCase(shape)) {
            svgContent.append(String.format("<circle cx=\"%d\" cy=\"%d\" r=\"40\" fill=\"%s\" stroke=\"black\" stroke-width=\"2\" />\n", x + offset + 50, y + 25, color));
        }

        // If the node has nested shapes, process them recursively
        if (node.has("nested")) {
            JSONArray nested = node.getJSONArray("nested");
            for (int i = 0; i < nested.length(); i++) {
                JSONObject nestedNode = nested.getJSONObject(i);
                processNode(nestedNode, svgContent, x, y + 100, offset + (i * 120)); // Offset for each nested shape
            }
        }
    }

    // Method to write the SVG content to a file
    public static void writeSvgToFile(String svgContent, String filePath) {
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(svgContent);
            System.out.println("SVG file successfully written to " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing SVG file: " + e.getMessage());
        }
    }
}
