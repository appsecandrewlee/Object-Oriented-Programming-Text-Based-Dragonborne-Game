package game.states;

import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import game.actors.Player;


/**
 * State machine for setting up behaviour that is performed upon respawn
 * Andrew Lee
 *
 */
public class Respawn extends State {



    public Respawn(Player player) {
        super(player);
    }


    /**
     * setting up attributes after the player has been knocked unconscious
     */
    @Override
    public void onRespawn() {
            player.modifyAttributeMaximum(BaseActorAttributes.STAMINA, ActorAttributeOperations.UPDATE,100);
            player.modifyAttributeMaximum(BaseActorAttributes.HEALTH,ActorAttributeOperations.UPDATE,100);
            player.modifyAttribute(BaseActorAttributes.STAMINA,ActorAttributeOperations.UPDATE,100);
            player.modifyAttribute(BaseActorAttributes.HEALTH,ActorAttributeOperations.UPDATE,100);
    }
}
