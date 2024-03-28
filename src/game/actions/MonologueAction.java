package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.actors.TheIsolatedTraveller;
import game.monologues.AbMonologue;
import game.weapons.GiantHammer;
import game.weapons.GreatKnife;

import java.util.ArrayList;


/**
 *
 * Andrew Lee
 * Monologue option that is utilising Abmonologue for actors that is capable for monologue
 *
 *
 *
 */
public class MonologueAction extends Action {

    private AbMonologue actorWithMonologue;

    public MonologueAction(AbMonologue actorWithMonologue) {
        this.actorWithMonologue = actorWithMonologue;
    }

    @Override
    public String execute(Actor otherActor, GameMap map) {
        return actorWithMonologue.getMonologue(otherActor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " listens to " + actorWithMonologue.toString();
    }
}