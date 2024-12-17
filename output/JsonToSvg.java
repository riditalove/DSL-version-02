package output;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class JsonToSvg {

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
            svgContent.append(String.format("<rect x=\"%d\" y=\"%d\" width=\"100\" height=\"50\" fill=\"%s\" />\n", x + offset, y, color));
        } else if ("Circle".equalsIgnoreCase(shape)) {
            svgContent.append(String.format("<circle cx=\"%d\" cy=\"%d\" r=\"40\" fill=\"%s\" />\n", x + offset + 50, y + 25, color));
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

    public static void main(String[] args) {
        // Sample AST JSON (the output you generated from your AST)
        String jsonAstString = "{\n" +
                "  \"name\": \"Program\",\n" +
                "  \"action\": \"\",\n" +
                "  \"shape\": \"\",\n" +
                "  \"color\": \"\",\n" +
                "  \"nested\": [\n" +
                "    {\n" +
                "      \"name\": \"MyRect\",\n" +
                "      \"action\": \"create\",\n" +
                "      \"shape\": \"Rectangle\",\n" +
                "      \"color\": \"red\",\n" +
                "      \"nested\": [\n" +
                "        {\n" +
                "          \"name\": \"MyCircle\",\n" +
                "          \"action\": \"create\",\n" +
                "          \"shape\": \"Circle\",\n" +
                "          \"color\": \"blue\",\n" +
                "          \"nested\": []\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        // Convert the string to JSONObject
        JSONObject jsonAst = new JSONObject(jsonAstString);

        // Convert the JSON AST to SVG format
        String svgContent = convertJsonToSvg(jsonAst);

        // Write the SVG content to a file
        writeSvgToFile(svgContent, "output.svg");
    }
}
