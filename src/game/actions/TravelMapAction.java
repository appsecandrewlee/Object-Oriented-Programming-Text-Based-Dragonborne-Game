package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.grounds.Gate;

import java.io.PrintStream;

public class TravelMapAction extends Action {


    /**
     * Gate
     */
    private Gate gate;

    private GameMap dest;


    public TravelMapAction(Gate gate,GameMap dest) {
        this.gate = gate;
        this.dest = dest;
    }


    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map){
        Location newLocation = dest.at(22,6);
        actor.addCapability(Status.BURIAL_GROUND);
        map.moveActor(actor, newLocation);
        System.out.println(actor.hasCapability(Status.BURIAL_GROUND));
        String mapName = gate.getcurrMapNames(dest);
        return actor + "Has traveled" + mapName;
    }

    @Override
    public String menuDescription(Actor actor) {
        String mapName = gate.getcurrMapNames(dest);
        return actor.toString() + "travelled to" + " " + mapName;
    }

}
