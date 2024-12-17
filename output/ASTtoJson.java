package output;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class ASTtoJson {

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
}

