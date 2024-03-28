package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actors.Player;
import game.weapons.GreatKnife;

public class StabAndStepAction extends Action {


    private GreatKnife greatKnife;

    public StabAndStepAction(GreatKnife greatKnife){
        this.greatKnife = greatKnife;
    }


    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return Stamina calculation, else there is not enough stamina.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        greatKnife.activateSkills(actor, map);
        int n = (int) (actor.getAttribute(BaseActorAttributes.STAMINA) - (actor.getAttributeMaximum(BaseActorAttributes.STAMINA) * 0.25));

        if (n > actor.getAttribute(BaseActorAttributes.STAMINA)){
            return "you do not have enough Stamina";
        }
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.DECREASE,n);

        return actor + " has used Great Knife Ability!";
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " f" +  "use Great Knife Ability";
    }
}
