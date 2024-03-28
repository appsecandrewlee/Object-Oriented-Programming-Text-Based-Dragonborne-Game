package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.items.Consumable;
import game.items.RefreshingFlask;

public class StaminaRegenAction extends Action {



    private Consumable consumable;


    public StaminaRegenAction(Consumable consumable) {
        this.consumable = consumable;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return consumed inventory refreshingflask, if it is consumed and actions has been performed, remove the item from the inventory
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        //check the inventory if it contains a refreshingFlask
        if (actor.getItemInventory().contains(consumable)){
            //if it does, and the player chooses to use it
            //activateAction staminaRegen
            consumable.canConsume(actor);
            //remove the item from the inventory
            actor.removeItemFromInventory((Item) consumable);
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + "" + "would you like to consume";
    }
}
