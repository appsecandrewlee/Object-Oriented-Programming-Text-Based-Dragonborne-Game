package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actors.Player;
import game.weapons.BroadSword;


/**
 * Andrew Lee
 * FocusAction is an Action that is performed by the actor holding the broadsword
 * It is a unique ability to increase damage output by percentage multiplier
 *
 */
public class FocusAction extends Action {


    /**
     * BroadSword ability
     * This gives the ability of the player to activate skills
     * and the implementation of Broadsword ability
     *
     */


    private BroadSword broadSword;


    public FocusAction(BroadSword broadSword) {
        this.broadSword = broadSword;
    }


    /**
     *
     * @param Player with pickedup item The actor performing the action.
     * @param current map the actor is on.
     * @return
     */


    /**
     *
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return the description after a successful execution
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // add focus status to weapon

        broadSword.activateSkills(actor, map);

        int replenishedStamina = actor.getAttribute(BaseActorAttributes.STAMINA) - 40;

        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.UPDATE, replenishedStamina);


        return "has used focus on broadsword";
    }


    /**
     *
     * @param actor The actor performing the action.
     * @return the menu description for the player to activate the special skill of broadsword
     */
    @Override
    public String menuDescription(Actor actor) {
        return (actor + " activates the special skill of BroadSword");
    }
}
