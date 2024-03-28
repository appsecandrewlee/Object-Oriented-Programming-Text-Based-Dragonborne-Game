package game.spawners;

import edu.monash.fit2099.engine.positions.Location;

public interface Spawner {
    boolean CouldSpawn(Location location);

    void spawn(Location location);

}
