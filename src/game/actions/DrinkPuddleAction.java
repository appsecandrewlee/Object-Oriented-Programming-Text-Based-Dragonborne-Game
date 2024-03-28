package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;


/**
 * Andrew Lee
 * DrinkPuddleAction is a class that is intractable by the puddle (~)
 * This allows specific action (replenish health to be performed) on the actor
 *
 */
public class DrinkPuddleAction extends Action {


    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return execution for replenishing health and stamina
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        actor.heal(5);
        int replenishedStamina = (int) (actor.getAttributeMaximum(BaseActorAttributes.STAMINA) * 0.01);
        int newStamina = actor.getAttribute(BaseActorAttributes.STAMINA) + replenishedStamina;
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.UPDATE,newStamina);
        return actor + "has healed 5 and replenished" + " " + replenishedStamina;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return menu description on the actor that drinks from the puddle
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " " + "Drink from puddle";
    }
}
