package game.grounds;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.Ground;
import edu.monash.fit2099.engine.positions.Location;
import game.spawners.RedWolfSpawner;
import game.actors.RedWolf;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bush extends SpawnableGround {
    /**
     * Constructor.
     *
     * @param displayChar character to display for this type of terrain
     */
    public Bush() {
        super('m');
    }


    private Random random = new Random();
    private RedWolfSpawner redWolfSpawner = new RedWolfSpawner();

    @Override
    public void tick(Location location) {
        super.tick(location);

        if (redWolfSpawner.CouldSpawn(location)) {
            List<Exit> exits = location.getExits();
            List<Location> validLocations = new ArrayList<>();

            for (Exit exit : exits) {
                Location locations = exit.getDestination();
                Actor redWolf = new RedWolf();

                if (!locations.containsAnActor() && locations.getGround().canActorEnter(redWolf)) {
                    validLocations.add(locations);
                }
            }

            if (!validLocations.isEmpty()) {
                Location spawnLocation = validLocations.get(random.nextInt(validLocations.size()));
                redWolfSpawner.spawn(spawnLocation);
            }
        }
    }


}
