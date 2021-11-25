package model;

import org.json.JSONArray;
import org.json.JSONObject;

//This represents a type of knitting supply. A needle has a name, and info on whether it is straight or circular.
public class Needles extends KnittingSupplies {

    //TODO: add a method to sort needles by size and type later

    //Note, if not straight then the needle is circular
    private boolean straightNeedle;

    //EFFECTS: Constructs needles as an empty list
    public Needles(KnittingProject kp) {
        super(kp);
    }

//    public JSONArray needlesToJson() {
//        JSONArray jsonArray = new JSONArray();
//        JSONObject json = new JSONObject();
//
//        for (int i = 0; i < this.getSupplies().size(); i++) {
//            String supplyName = this.getSupplies().get(i);
//            //json.put("needles", supplyName);
//
//            jsonArray.put(supplyName);
//        }
//
//        return jsonArray;
//    }

}
