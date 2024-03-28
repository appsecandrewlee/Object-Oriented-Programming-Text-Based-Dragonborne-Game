package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.MonologueAction;
import game.actions.SellingAction;
import game.actions.ShoppingAction;
import game.actions.UpgradeAction;
import game.monologues.AbMonologue;
import game.upgrades.Upgradable;
import game.upgrades.Upgrade;

import java.util.ArrayList;
import java.util.Random;


/**
 * Blacksmith is an actor that upgrades weapons, healing vials and other various items on the map
 *
 *
 *
 */
public class BlackSmith extends AbMonologue {


    Random random = new Random();
    private ArrayList<Integer> localList = new ArrayList<>();

    /**
     * The constructor of the Actor class.
     *
     * @param Black Smith        the name of the Actor
     * @param B the character that will represent the Actor in the display
     * @param 10000000   the Actor's starting hit points
     */
    public BlackSmith() {
        super("Black Smith",'B' , 100000000);

    }

    /**
     *  Andrew Lee
     * @param Player the Actor that might be performing action
     * @param current Direction facing the actor,  String representing the direction of the other Actor
     * @param currMap  current GameMap
     * @return
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            for (Item upgradable: otherActor.getItemInventory()){
                if (upgradable.hasCapability(Status.CAN_UPGRAD)){
                    actions.add(new UpgradeAction((Upgrade) upgradable));
                }
            }
        }
        return actions;
    }


    /**
     *  Andrew Lee
     * @param Player
     * @return randomised monologue options based on preconditions
     */
    @Override
    public String getMonologue(Actor otherActor) {
        localList.add(1);
        localList.add(2);
        localList.add(3);
        localList.add(6);

        for (Item item: otherActor.getItemInventory()) {
            if (priceItemBuy.containsKey(item.toString())){
                localList.add(5);
                if (localList.contains(5)) {
                    return null;
                }
            }
        }
        if (!otherActor.hasCapability(Status.DEFEATED_ABXERVYER)){
            localList.add(4);
        }
        if (otherActor.hasCapability(Status.DEFEATED_ABXERVYER)){
            localList.add(6);
        }
        Integer randomIndex = localList.get(random.nextInt(localList.size()));
        return monologue.get(randomIndex);
        }

}
