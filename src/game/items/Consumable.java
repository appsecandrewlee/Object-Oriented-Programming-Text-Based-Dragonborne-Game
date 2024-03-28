package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;

import java.util.List;

public interface Consumable {

    /**
     * interface for the implementation of consumable
     * @param actor can consume certain specific items
     */
    void canConsume(Actor actor);

    /**
     *
     * @param actor gets returned specific actions
     * @return this implementation will work once an item implements this interfac
     */
    List<Action> getActionSpecific(Actor actor);

}

