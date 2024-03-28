package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Wallet;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actors.Player;
import game.items.Rune;


/**
 *
 * Andrew Lee
 * This is a class for Adding balance after the player picks up the runes
 *
 */
public class AddToWalletAction extends Action {

    private final Rune rune;

    /**
     *
     * @param the rune that is being added to the wallet
     */
    public AddToWalletAction(Rune item) {
        this.rune = item;
    }

    /**
     * The actor will add balance after picking up the rune items
     * and they can perform addtowalletaction to add the amount to their current wallet (balance)
     *
     */


    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return success message whether if the runes have been consumed
     */

    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)) {
            actor.addBalance(rune.getRuneCount());
            map.locationOf(actor).removeItem(rune);
            actor.removeItemFromInventory(rune);
        }
        return "You have consumed the runes";
    }


    /**
     *
     * @param The Player performing the action.
     * @return menu option description for consuming the runes
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " " + "Consume the Runes";
    }

}
