package model;

//This represents a type of knitting supply. A needle has a name, and info on whether it is straight or circular.
public class Needles extends KnittingSupplies {

    //TODO: add a method to sort needles by size and type later

    //Note, if not straight then the needle is circular
    private boolean straightNeedle;

    //EFFECTS: Constructs needles as an empty list
    public Needles() {
        super();
    }

}
