package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.items.BloodBerry;
import game.items.Consumable;
import game.items.HealingVial;

public class HealingAction extends Action {


    /**
     * healingVial implementation, this is the actual consume action calling from the interface canConsume
     * a common denominator between all items consumable
     * @returns action after player input
     */
    private Consumable consumable;

    public HealingAction(Consumable consumable) {
        this.consumable = consumable;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor.getItemInventory().contains(consumable)){
            consumable.canConsume(actor);
            actor.removeItemFromInventory((Item) consumable);
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " " + "has used the healing vial";
    }
}
