package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.ForestKeeper;
import game.spawners.ForestKeeperSpawner;
import game.actors.RedWolf;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hut extends SpawnableGround {

    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */


    /**
     * A class that represents Hut, a form of spawning ground mechanism in game.
     * Created by: Andrew Lee
     * @author Andrew Lee
     * Modified by: Andrew Lee, Charles Liu
     *
     */

    private Random random = new Random();
    public Hut() {
        super('h');
    }

    ForestKeeperSpawner forestKeeperSpawner = new ForestKeeperSpawner();
    @Override
    public void tick(Location location) {
        super.tick(location);

        if (forestKeeperSpawner.CouldSpawn(location)) {
            List<Exit> exits = location.getExits();
            List<Location> validLocations = new ArrayList<>();

            for (Exit exit : exits) {
                Location locations = exit.getDestination();
                Actor forestKeeper = new ForestKeeper();

                if (!locations.containsAnActor() && locations.getGround().canActorEnter(forestKeeper)) {
                    validLocations.add(locations);
                }
            }
            if (!validLocations.isEmpty()) {
                Location spawnLocation = validLocations.get(random.nextInt(validLocations.size()));
                forestKeeperSpawner.spawn(spawnLocation);
            }
        }
    }
}
