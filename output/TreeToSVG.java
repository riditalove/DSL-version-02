package output;
import java.io.FileWriter;
import java.io.IOException;

// TreeNode to represent the hierarchy
import java.util.concurrent.atomic.AtomicInteger;

class TreeNode<T> {
    private static final AtomicInteger idGenerator = new AtomicInteger(0); // To generate unique IDs
    T data;  // Generic type data (e.g., shape name)
    String id;  // Unique identifier for the node
    TreeNode<T> left;   // Left child
    TreeNode<T> right;  // Right child

    // Constructor with data and auto-generated unique ID
    public TreeNode(T data) {
        this.data = data;
        this.id = data.toString() + idGenerator.incrementAndGet();
        this.left = null;
        this.right = null;
    }

    // Constructor with data and a custom ID
    public TreeNode(T data, String customId) {
        this.data = data;
        this.id = customId;
        this.left = null;
        this.right = null;
    }

    // Getters and setters for id (optional)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}


// Main class for tree structure and SVG generation
public class TreeToSVG {

    // Recursive method to generate SVG content for a tree
    public static void generateSVG(TreeNode<String> node, StringBuilder svgContent, int x, int y, int levelGap) {
        if (node == null) {
            return;
        }

        // Add the current node to the SVG
        if (node.data.equals("MyRect")) {
            svgContent.append(String.format(
                    "<rect x='%d' y='%d' width='100' height='50' fill='blue' stroke='black' />\n", x, y));
            svgContent.append(String.format(
                    "<text x='%d' y='%d' fill='white' text-anchor='middle' alignment-baseline='middle'>%s</text>\n",
                    x + 50, y + 25, node.data));
        } else if (node.data.equals("MyCircle")) {
            svgContent.append(String.format(
                    "<circle cx='%d' cy='%d' r='30' fill='green' stroke='black' />\n", x, y));
            svgContent.append(String.format(
                    "<text x='%d' y='%d' fill='white' text-anchor='middle' alignment-baseline='middle'>%s</text>\n",
                    x, y, node.data));
        } else if (node.data.equals("MyCirc")) {
            svgContent.append(String.format(
                    "<circle cx='%d' cy='%d' r='30' fill='red' stroke='black' />\n", x, y));
            svgContent.append(String.format(
                    "<text x='%d' y='%d' fill='white' text-anchor='middle' alignment-baseline='middle'>%s</text>\n",
                    x, y, node.data));
        }

        // Draw lines to children
//        if (node.left != null) {
//            svgContent.append(String.format("<line x1='%d' y1='%d' x2='%d' y2='%d' stroke='black' />\n",
//                    x + (node.data.equals("MyRect") ? 50 : 0), y + 25, x - levelGap + 50, y + 100));
//        }
//        if (node.right != null) {
//            svgContent.append(String.format("<line x1='%d' y1='%d' x2='%d' y2='%d' stroke='black' />\n",
//                    x + (node.data.equals("MyRect") ? 50 : 0), y + 25, x + levelGap + 50, y + 100));
//        }

        // Recur for children
        generateSVG(node.left, svgContent, x - levelGap, y + 100, levelGap / 2);
        generateSVG(node.right, svgContent, x + levelGap, y + 100, levelGap / 2);
    }

    public static void main(String[] args) {
        // Construct the tree (MyRect -> MyCircle, MyCirc)
        TreeNode<String> root = new TreeNode<>("MyRect");
        root.left = new TreeNode<>("MyCircle");
        root.right = new TreeNode<>("MyCirc");

        // SVG header
        StringBuilder svgContent = new StringBuilder();
        svgContent.append("<svg xmlns='http://www.w3.org/2000/svg' width='800' height='600'>\n");

        // Generate SVG content for the tree
        generateSVG(root, svgContent, 400, 50, 200);

        // Close SVG
        svgContent.append("</svg>");

        // Write to an SVG file
        try (FileWriter writer = new FileWriter("tree_structure.svg")) {
            writer.write(svgContent.toString());
            System.out.println("SVG file created successfully: tree_structure.svg");
        } catch (IOException e) {
            System.err.println("Error writing SVG file: " + e.getMessage());
        }
    }
}
