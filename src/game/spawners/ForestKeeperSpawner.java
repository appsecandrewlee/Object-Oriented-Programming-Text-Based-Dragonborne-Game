package game.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.ForestKeeper;

import java.util.Random;

import static game.Status.BURIAL_GROUND;

public class ForestKeeperSpawner implements Spawner{

    private final int SPAWN_CHANCE = 15;

    Random random = new Random();
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
        Actor forestKeeper = new ForestKeeper();
        location.addActor(forestKeeper);
    }
}
