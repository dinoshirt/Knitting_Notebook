package model;

import java.util.ArrayList;
import java.util.List;

//This represents any knitting supply that may be used in the project, be it yarns or needles.
public abstract class KnittingSupplies {

    protected List<String> listOfSupplies;

    //EFFECTS: Creates an empty list of knitting supplies
    public KnittingSupplies() {
        listOfSupplies = new ArrayList<>();
    }

    //EFFECTS: Returns the list of knitting supplies
    public List<String> getSupplies() {
        return listOfSupplies;
    }

    //MODIFIES: this
    //EFFECTS: adds the given supply to the list of supplies
    public void addSupply(String supply) {
        listOfSupplies.add(supply);
        EventLog.getInstance().logEvent(new Event("Added to Project: " + supply));

    }

    //REQUIRES: list of supplies should not be empty. It should contain supply.
    //MODIFIES: this
    //EFFECTS: removes the given supply to the list of supplies
    public void removeSupply(String supply) {
        listOfSupplies.remove(supply);
        EventLog.getInstance().logEvent(new Event("Removed from Project: " + supply));
    }

    //REQUIRES: supply should exist in listOfSupplies
    //EFFECTS: returns the supply being looked for. If no such supply exists, return an empty string.
//    public String getOneSupply(String supply) {
//        String currentSupply;
//        for (int i = 0; i < listOfSupplies.size(); i++) {
//            currentSupply = listOfSupplies.get(i);
//            return currentSupply;
//        }
//        return "";
//    }


}



