package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Ground;

import static game.Status.IS_ENEMY;

/**
 * A class that represents the floor inside a building.
 * Created by:
 * @author Riordan D. Alfredo
 * Modified by:
 *
 */
public class Floor extends Ground {
    public Floor() {
        super('_');
    }

    @Override
    public boolean canActorEnter(Actor actor) {
        if (actor.hasCapability(IS_ENEMY)){
            return false;
        }
        return true;
    }


}
