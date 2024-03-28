package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.FancyMessage;
import game.Status;
import game.actions.AttackAction;
import game.actions.DrinkPuddleAction;
import game.actors.Player;
import game.behaviours.FollowBehaviour;

import static edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes.HEALTH;
import static game.Status.HOSTILE_TO_ENEMY;

public class Puddle extends Ground {
    public Puddle() {
        super('~');
    }

    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {
        ActionList actions = new ActionList();
        //if the location contains an actor, which indicates someone is standing ontop of puddle
        if (location.containsAnActor()) {
            //if the actor is the player because only player has the HOSTILE_TO_ENEMY status
            if (actor.hasCapability(HOSTILE_TO_ENEMY)) {
                //give the player the option to drink
                actions.add(new DrinkPuddleAction());
            }
        }
        return actions;
    }

}
