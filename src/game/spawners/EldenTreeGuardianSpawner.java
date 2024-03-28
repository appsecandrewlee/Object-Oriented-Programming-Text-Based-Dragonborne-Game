package game.spawners;

import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import game.actors.EldentreeGuardian;
import game.actors.Enemy;
import game.actors.ForestKeeper;
import game.actors.LivingBranch;

import java.util.Random;

import static game.Status.BURIAL_GROUND;
import static game.Status.SANCTUARY;

public class EldenTreeGuardianSpawner implements Spawner{

    private final int SPAWN_CHANCE = 20;



    Random random = new Random();
    @Override
    public boolean CouldSpawn(Location location) {
        GameMap map = location.map();
        for (int i = 0; i < map.getXRange().max() - 1; i++) {
            for (int j = 0; j < map.getYRange().max() - 1; j++) {
                if (map.at(i, j).containsAnActor()) {
                    if (map.at(i, j).getActor().hasCapability(SANCTUARY)) {
                        return false;
                    }
                }
            }
        }
        return random.nextInt(100) < SPAWN_CHANCE;
    }

    @Override
    public void spawn(Location location) {
        Enemy eldenGuardian = new EldentreeGuardian();
        location.addActor(eldenGuardian);
    }
}
