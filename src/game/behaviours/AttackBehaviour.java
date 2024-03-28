package game.behaviours;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.AttackAction;

import java.util.List;

public class AttackBehaviour implements Behaviour {


    /**
     * 
     * @param target the Actor acting
     * @param currentmap the GameMap containing the Actor
     * @return
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        //create a list for the 8 surrounding exits
        List<Exit> exitLocations =  map.locationOf(actor).getExits();

        //iterate through the exits
        for (int i = 0; i < exitLocations.size(); i++){
            //check if there is an actor in the exit
            if (exitLocations.get(i).getDestination().containsAnActor()){
                //if the actor is the player, attack the player.
                Actor target = exitLocations.get(i).getDestination().getActor();
                if(target.hasCapability(Status.HOSTILE_TO_ENEMY) && !target.hasCapability(Status.IS_ENEMY)) {
                    String direction = exitLocations.get(i).getName();
                    return new AttackAction(target,direction);
                }
            }
        }
        return null;
    }
}
