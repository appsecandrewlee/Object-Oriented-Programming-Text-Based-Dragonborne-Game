package game.actors;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.MonologueAction;
import game.actions.SellingAction;
import game.actions.ShoppingAction;
import game.items.BloodBerry;
import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.monologues.AbMonologue;
import game.weapons.BroadSword;
import game.weapons.GiantHammer;
import game.weapons.GreatKnife;

import java.util.*;


/**
 * The isolatedTraveller extends AbMonologue and represents as the IsolatedTraveller in game
 * This class handles all the actions that is performing by the otheractor (Player)
 * and provides monologue for the player to interact with
 *
 * Andrew Lee
 */
public class TheIsolatedTraveller extends AbMonologue {


    Random random = new Random();

    //Hash maps for both selling and buying action

    private ArrayList<Integer> localList = new ArrayList<>();



    /**
     * The constructor of the Actor class.
     * Andrew Lee
     * @param TheIsolatedTraveller  the name of the Actor
     * @param 'ඞ' the character that will represent the Actor in the display
     * @param 1000000   the Actor's starting hit points
     */
    public TheIsolatedTraveller() {
        super("The Isolated Traveller",'ඞ' , 100000000);


        priceItemSell.put(bloodBerry,50);
        priceItemSell.put(broadSword,100);
        priceItemSell.put(healingVial,150);
        priceItemSell.put(refreshingFlask,130);
        priceItemSell.put(greatKnife,170);

        priceItemBuy.put(bloodBerry.toString(),10);
        priceItemBuy.put(broadSword.toString(),100);
        priceItemBuy.put(healingVial.toString(),100);
        priceItemBuy.put(refreshingFlask.toString(),25);
        priceItemBuy.put(greatKnife.toString(),1000000);
        priceItemBuy.put(giantHammer.toString(),0);

    }


    /**
     * Andrew Lee
     * @param Player the Actor that might be performing attack
     * @param Facing Direction  String representing the direction of the other Actor
     * @param currMap current GameMap
     * @return actions from actionlist
     */
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = super.allowableActions(otherActor, direction, map);
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            for (Item sellable: this.getItemInventory()){
                actions.add(new ShoppingAction(sellable,this));
            }
        }
        if (otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            for (Item buyable: otherActor.getItemInventory()){
                System.out.println(otherActor.getItemInventory());
                if (priceItemBuy.containsKey(buyable.toString())){
                    actions.add(new SellingAction(buyable,this));
                }
            }
        }
        return actions;
    }

    /**
     *
     * @param otherActor
     * @return Randomised Monologue Options
     * Charles Liu, Andrew Lee
     */
    @Override
    public String getMonologue(Actor otherActor) {
        localList.add(7);
        localList.add(8);
        localList.add(9);
        localList.add(10);
        localList.add(11);
        localList.add(12);

        for (Item item: otherActor.getItemInventory()) {
            if (priceItemBuy.containsKey(item.toString()) && otherActor.hasCapability(Status.DEFEATED_ABXERVYER)) {
                localList.add(15);
                if (localList.contains(13)) {
                    return null;
                }
            }
            else if (priceItemBuy.containsKey(item.toString())) {
                localList.add(13);
            }
        }
        if (!otherActor.hasCapability(Status.DEFEATED_ABXERVYER)) {
            localList.add(14);
        }

        Integer randomIndex = localList.get(random.nextInt(localList.size()));
        return monologue.get(randomIndex);
    }
}