package game.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.RedWolf;

import java.util.Random;

import static game.Status.BURIAL_GROUND;


public class RedWolfSpawner implements Spawner {


    private Random random = new Random();
    private final int SPAWN_CHANCE = 15;


    /**
     *
     * @param location is used to get current actor location, or if a location contains actor, gets exit lists
     * @return
     */
    @Override
    public boolean CouldSpawn(Location location) {
        GameMap map = location.map();
        for (int i = 0; i < map.getXRange().max() - 1; i++) {
            for (int j = 0; j < map.getYRange().max() - 1; j++) {
                if (map.at(i, j).containsAnActor()) {
                    if (map.at(i, j).getActor().hasCapability(BURIAL_GROUND)) {
                        return false;
                    }
                }
            }
        }
        return random.nextInt(100) < SPAWN_CHANCE;
    }

    @Override
    public void spawn(Location location) {
        Actor redWolf = new RedWolf();
        location.addActor(redWolf);
    }
}

