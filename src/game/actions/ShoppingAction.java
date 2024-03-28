package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Wallet;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.TheIsolatedTraveller;

public class ShoppingAction extends Action {

    private Item tradables;

    private TheIsolatedTraveller theIsolatedTraveller;

    public ShoppingAction(Item tradables, TheIsolatedTraveller theIsolatedTraveller) {
        this.tradables = tradables;
        this.theIsolatedTraveller = theIsolatedTraveller;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return message execution successful, otherwise the balance is not there
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        int itemPrice = theIsolatedTraveller.getPrice(tradables);
        if (actor.getBalance()>= itemPrice){
            actor.deductBalance(itemPrice);
            actor.addItemToInventory(tradables);
            return actor + "bought" + tradables;
        }else{
            return "You do not have enough balance";
        }
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " " + "buys" + " " + tradables;

    }
}
