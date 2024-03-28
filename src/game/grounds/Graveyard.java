package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.Status;
import game.actors.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static game.Status.BURIAL_GROUND;
import static game.Status.HOSTILE_TO_ENEMY;

public class Graveyard extends Ground {

    private Random random = new Random();


    /**
     * A class that represents Gate, a form of travelling mechanism in game.
     * Created by: Andrew Lee
     * @author Andrew Lee
     * Modified by: Andrew Lee
     *
     */

    /**
     * Constructor.
     *
     * @param n character to display for this type of terrain
     */
    public Graveyard() {
        super('n');
        //first step: load the enemy that you are trying spawn
        //second step: in the tick, spawn enemy if success rate else
        //Wanderingundead wanderingundead = new wandeiner
        //spawnlocation(wanderingundead)
        //load enemy in constructor Enemy enemy = new enemy(ForestKeeper);
    }
    /**
     *
     * @param location The location of the Ground
     */

    @Override
    public void tick(Location location) {
        super.tick(location);
        //spawnlocation(enemy);
        //success rate is 25% for spawning undead
        int successRate = 25;
        boolean a = false;
            for (int i = 0; i < location.map().getXRange().max() - 1; i++) {
                for (int j = 0; j < location.map().getYRange().max() - 1; j++) {
                    if (location.map().at(i, j).containsAnActor()) {
                        if (location.map().at(i, j).getActor().hasCapability(BURIAL_GROUND)) {
                            a = true;
                        }
                    }
                }
            }
        if (a) {
            int newSuccessRate = 10;
            if (random.nextInt(100) < newSuccessRate){
                List<Exit> exits = location.getExits();
                // create a list to store valid locations to spawn undead
                List<Location> validLocations = new ArrayList<>();
                // check if the surrounding exits are valid locations to spawnunuddead
                for (Exit exit : exits) {
                    //get the co-ordinates of the exit
                    Location locations = exit.getDestination();
                    //check if it contains any actor, and if actor can stand on it.
                    Actor hollowingSoldier = new HollowSoldier();
                    if (!locations.containsAnActor() && locations.getGround().canActorEnter(hollowingSoldier)) {
                        //add the valid location to the arrayList
                        validLocations.add(locations);
                    }
                }
                //if there is valid locations
                if (!validLocations.isEmpty()) {
                    Location spawnLocation = validLocations.get(random.nextInt(validLocations.size()));
                    //if player has capability of burial ground
                    //pick a valid location from list
                    //spawn undead on the targeted location
                    Actor hollowingSoldier = new HollowSoldier();
                    spawnLocation.addActor(hollowingSoldier);
            }
        }
        }
        else{
            if (random.nextInt(100) < successRate){
                //TODO
                // get the surrounding 8 exits
                List<Exit> exits = location.getExits();
                // create a list to store valid locations to spawn undead
                List<Location> validLocations = new ArrayList<>();
                // check if the surrounding exits are valid locations to spawnunuddead
                for (Exit exit : exits) {
                    //get the co-ordinates of the exit
                    Location locations = exit.getDestination();
                    //check if it contains any actor, and if actor can stand on it.
                    Actor wanderingUndead = new WanderingUndead();
                    if (!locations.containsAnActor() && locations.getGround().canActorEnter(wanderingUndead)) {
                        //add the valid location to the arrayList
                        validLocations.add(locations);
                    }
                }
                //if there is valid locations
                if (!validLocations.isEmpty()) {
                    Location spawnLocation = validLocations.get(random.nextInt(validLocations.size()));
                    //if player has capability of burial ground
                    //pick a valid location from list
                    //spawn undead on the targeted location
                    Actor wanderingUndead = new WanderingUndead();
                    spawnLocation.addActor(wanderingUndead);
                }
            }
        }
    }
}

