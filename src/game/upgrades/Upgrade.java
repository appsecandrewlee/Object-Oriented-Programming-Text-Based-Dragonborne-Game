package game.upgrades;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import game.Ability;
import game.Status;
import game.items.Consumable;

/**
 * Andrew Lee
 *
 * This class controls whether they can be upgraded or not, if the class extends upgrade
 * this will indicate that the item can be upgraded.
 */
public abstract class Upgrade extends Item implements Upgradable, Consumable {


    //using decorator pattern, not sure if i actually used it correctly
    //but referencing from refactoring guru, would require a implementable interface first and then
    //abstract class, which is what i did

    Display display = new Display();
    /***
     * Constructor.
     *  @param individual items that is upgradable the name of this Item
     * @param their displaychar the character to use to represent this item if it is on the ground
     * @param can they be carried true if and only if the Item can be picked up
     */
    public Upgrade(String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        this.addCapability(Status.CAN_UPGRAD);
    }


    /**
     *
     * @param upgradables
     */
    @Override
    public void canUpgrade(Item item) {
        if (item.hasCapability(Status.CAN_UPGRAD)) {
        }else{
            display.println("This item is not upgradable");
        }
    }

}
