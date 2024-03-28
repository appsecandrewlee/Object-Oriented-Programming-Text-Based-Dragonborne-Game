package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;

import java.util.List;

public interface Skillable {


    /**
     *
     * @param actor
     * @return
     */
    List<Action> getActionSpecific(Actor actor);
    void activateSkills(Actor actor, GameMap map);

    void deactivateSkills();

    /**
     *
     * @param actor
     * @param map
     */
    void enemyAffected(Actor actor, GameMap map);

    /**
     *
     * @param status
     * @return
     */
    boolean canActivate(Status status);

}
