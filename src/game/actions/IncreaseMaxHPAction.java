package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.BloodBerry;
import game.items.Consumable;


/**
 * Andrew Lee
 * This class performs increasing max HP for the actor after action
 *
 */

public class IncreaseMaxHPAction extends Action {


    /**
     *
     * @return the after effect of the bloodberries
     * can consume bloodberries.
     *
     */
    private Consumable consumable;



    public IncreaseMaxHPAction(Consumable consumable){
        this.consumable = consumable;
    }
    @Override



    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the
     */

    public String execute(Actor actor, GameMap map) {

        if (actor.getItemInventory().contains(consumable)){

            consumable.canConsume(actor);

            actor.removeItemFromInventory((Item) consumable);

        }
        return null;

    }

    @Override
    public String menuDescription(Actor actor) {
        return "consume this and increase max health";
    }
}
