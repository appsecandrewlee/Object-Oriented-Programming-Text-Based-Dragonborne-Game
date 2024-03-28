package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.Player;
import game.weapons.GiantHammer;


/**
 *
 *
 *
 *
 */
public class GreatSlamAction extends Action {


    /**
     * The ability of GreatSlam activates skills, same implementation as the FocusAction
     *
     *
     */

    private GiantHammer giantHammer;


     public GreatSlamAction(GiantHammer giantHammer){
         this.giantHammer = giantHammer;
     }

    @Override
    public String execute(Actor actor, GameMap map) {
        giantHammer.activateSkills(actor,map);

        int replenishedStamina = actor.getAttribute(BaseActorAttributes.STAMINA) - 40;

        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.UPDATE, replenishedStamina);


        return "You have used Giant Hammer Ability";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + "" + "use giant hammer ability?";
    }
}
