package game.grounds;

import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.Player;

import static edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes.HEALTH;
import static game.Status.HOSTILE_TO_ENEMY;
import static game.Status.IMMUNE_TO_PIT;

import game.states.Respawn;

public class Pitt extends Ground {


    /**
     * A class that represents Gate, a form of travelling mechanism in game.
     * Created by: Andrew Lee
     * @author Andrew Lee
     * Modified by: Andrew Lee, Charles Liu
     *
     */
    /**
     * Constructor.
     */
    public Pitt() {
        super('+');
    }



    @Override
    public void tick(Location location) {
        super.tick(location);
        if (location.containsAnActor()) {
            Actor actor = location.getActor();
            if(!actor.hasCapability(IMMUNE_TO_PIT)) {
                actor.hurt(actor.getAttributeMaximum(HEALTH));
            }
            if (actor.hasCapability(HOSTILE_TO_ENEMY)){
                location.map().moveActor(actor,location.map().at(20,3));
                ((Player) actor).changeState(new Respawn((Player) actor));
                ((Player) actor).respawn();
            }
        }
    }

}
