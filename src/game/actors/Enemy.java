package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actions.MoveActorAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.AttackAction;
import game.actions.MonologueAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;

import java.util.HashMap;
import java.util.Map;


/**
 *
 *
 */
public abstract class Enemy extends Actor {

    private final Map<Integer, Behaviour> behaviours = new HashMap<>();

    /**
     *
     * @param name
     * @param displayChar
     * @param hitPoints
     */
    public Enemy(String name, char displayChar, int hitPoints){
        super(name, displayChar, hitPoints);
        this.behaviours.put(1,new AttackBehaviour());
        this.behaviours.put(999,new WanderBehaviour());
        this.addCapability(Status.IS_ENEMY);
    }

    /**
     *
     * @param map
     */
    public abstract void dropItem(GameMap map);
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {
        ActionList actions = new ActionList();
        if(otherActor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            actions.add(new AttackAction(this, direction, ((Player) otherActor).getWeapon()));
        }
        this.behaviours.put(2, new FollowBehaviour(otherActor));

        return actions;
    }

    /**
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        for (Behaviour behaviour : behaviours.values()) {
            Action action = behaviour.getAction(this, map);

            if(action != null)
                return action;
        }
        return new DoNothingAction();
    }
}
