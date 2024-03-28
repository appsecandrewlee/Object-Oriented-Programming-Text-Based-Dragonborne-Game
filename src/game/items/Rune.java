package game.items;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;

public class Rune extends Item{

    private int runeCounter;


    /***
     * Constructor.
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     * @param runeLocation
     */
    public Rune(int runeCounter) {
        super("Rune",'$',true);
        this.runeCounter = runeCounter;
        this.addCapability(Status.CURRENCY);
    }

    public int getRuneCount(){
        return this.runeCounter;
    }
}
