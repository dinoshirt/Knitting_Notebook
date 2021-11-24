package model;

import org.json.JSONArray;
import org.json.JSONObject;

//This represents a type of knitting supply. Yarns are described by its name, stored as a string.
public class Yarns extends KnittingSupplies {

    //TODO: add a method to sort yarns alphabetically later

    //EFFECTS: Constructs yarns as an empty list
    public Yarns(KnittingProject kp) {
        super(kp);
    }

    public JSONArray yarnsToJson() {
        JSONArray jsonArray = new JSONArray();
        JSONObject json = new JSONObject();

        for (int i = 0; i < this.getSupplies().size(); i++) {
            String supplyName = this.getSupplies().get(i);
            jsonArray.put(supplyName);
        }

        return jsonArray;
    }


}
