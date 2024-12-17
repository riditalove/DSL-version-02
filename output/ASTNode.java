package output;

import java.util.List;
import java.util.ArrayList;

public class ASTNode {
    private String name;
    private String action;
    private String shape;
    private String color;
    private List<ASTNode> nested;  // A list to hold child nodes

    // Constructor
    public ASTNode(String name, String action, String shape, String color, List<ASTNode> nested) {
        this.name = name;
        this.action = action;
        this.shape = shape;
        this.color = color;
        this.nested = nested != null ? nested : new ArrayList<>();
    }

    // Getter and Setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {
        this.shape = shape;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public List<ASTNode> getNested() {
        return nested;
    }

    public void setNested(List<ASTNode> nested) {
        this.nested = nested;
    }

    // Method to add a child node to the nested list
    public void addChild(ASTNode child) {
        nested.add(child);
    }
}
