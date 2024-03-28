package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.TheIsolatedTraveller;

public class SellingAction extends Action {

    private Item tradables;

    private TheIsolatedTraveller theIsolatedTraveller;


    public SellingAction(Item tradables, TheIsolatedTraveller theIsolatedTraveller){
        this.tradables = tradables;
        this.theIsolatedTraveller = theIsolatedTraveller;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the message execution successful
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        int itemPrice = theIsolatedTraveller.getBuyingPrice(tradables);

        actor.removeItemFromInventory(tradables);
        actor.addBalance(itemPrice);

        return actor + "sold" + "the item" + tradables + " " + "for" + itemPrice ;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " " + "Sells" + " " + tradables;
    }
}
