package game.grounds;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Application;
import game.Status;
import game.actions.OpenGateAction;
import game.actions.TravelMapAction;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static game.Status.HOSTILE_TO_ENEMY;

public class Gate extends Ground {

    /**
     * A class that represents Gate, a form of travelling mechanism in game.
     * Created by: Andrew Lee
     *
     * @author Andrew Lee
     * Modified by: Andrew Lee
     */
    private GameMap abandonedWoods;

    private GameMap burialGrounds;

    private GameMap bossMap;

    private GameMap overGrownSact;

    private final HashMap<GameMap, String> currMapNames = new HashMap<>();


    /**
     * Constructor.
     *
     * @param initialmap is the name of the map
     * @param =          character to display for this type of terrain
     * @param =          burialMap is the name of the map being travelled to
     */
    public Gate(GameMap abandonedWoods, GameMap burialGrounds, GameMap bossMap, GameMap overGrownSact) {
        super('=');
        this.abandonedWoods = abandonedWoods;
        this.burialGrounds = burialGrounds;
        this.overGrownSact = overGrownSact;
        this.bossMap = bossMap;

        currMapNames.put(burialGrounds, "Burial Grounds");
        currMapNames.put(abandonedWoods, "Abandoned Woods");
        currMapNames.put(bossMap,"Abxervyer Map");
        currMapNames.put(overGrownSact, "Overgrown Sanctuary");
        this.addCapability(Status.IS_LOCKED);
    }

    public String getcurrMapNames(GameMap gameMap) {
        return currMapNames.get(gameMap);
    }

    /**
     *
     * @return burialmap
     * @return InitialMap (not used, could be used for later)
     */

    /**
     *
     * @param Player, because of enum HOSTILE_TO_ENEMY the Actor acting
     * @param x,y co-ordinates location the current Location
     * @param exit directions, the direction of the Ground from the Actor
     * @return actions, in this case spawning action
     */
    @Override
    public ActionList allowableActions(Actor actor, Location location, String direction) {

        List<Exit> exitLists = location.getExits();
        ActionList actions = new ActionList();

        if (this.hasCapability(Status.IS_LOCKED)) {
            // check gate's location
            if (location.containsAnActor() && actor.hasCapability(HOSTILE_TO_ENEMY)) {
                actions.add(new OpenGateAction(this));
                return actions;
            }
            // check surrounding locations
            for (Exit exit : exitLists) {
                Location exitLocation = exit.getDestination();
                // check if player stands in the exit
                if (exitLocation.containsAnActor() && actor.hasCapability(HOSTILE_TO_ENEMY)) {
                    actions.add(new OpenGateAction(this));
                    return actions;
                }
            }
        }
        else {
            if (location.containsAnActor() && actor.hasCapability(HOSTILE_TO_ENEMY)) {
                for (Map.Entry <GameMap,String> mapPair : currMapNames.entrySet()) {
                    actions.add(new TravelMapAction(this,mapPair.getKey()));
                }
                return actions;
            }
            // check surrounding locations
            for (Exit exit : exitLists) {
                Location exitLocation = exit.getDestination();
                // check if player stands in the exit
                if (exitLocation.containsAnActor() && actor.hasCapability(HOSTILE_TO_ENEMY)) {
                    for (Map.Entry <GameMap,String> mapPair : currMapNames.entrySet()) {
                        actions.add(new TravelMapAction(this,mapPair.getKey()));
                    }
                    return actions;
                }
            }
        }
        return actions;
    }
}
