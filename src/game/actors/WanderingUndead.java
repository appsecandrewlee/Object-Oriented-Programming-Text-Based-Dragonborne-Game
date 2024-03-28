package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.Behaviour;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import game.Ability;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.Status;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.items.HealingVial;
import game.items.OldKey;
import game.items.Rune;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class WanderingUndead extends Enemy {


    Random random = new Random();

    public WanderingUndead() {
        super("Wandering Undead", 't', 100);
    }

    /**
     * At each turn, select a valid action to perform.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return the valid action that can be performed in that iteration or null if no valid action is found
     */

//    @Override

    /**
     * The wandering undead can be attacked by any actor that has the HOSTILE_TO_ENEMY capability
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return
     */


    @Override
    public void dropItem(GameMap map) {

        spawnKey(map);
        spawnRunes(map);
        spawnVial(map);
    }


    public void spawnKey(GameMap map) {
        int successRate = 90;
        if (random.nextInt(100) <= successRate){
            Location keyLocation = map.locationOf(this);
            keyLocation.addItem(new OldKey());
        }
    }


    public void spawnRunes(GameMap map){
        Location runeLocation = map.locationOf(this);
        Rune runes = new Rune(50);
        runeLocation.addItem(runes);
    }


    public void spawnVial(GameMap map) {
        int successRate = 90;
        if (random.nextInt(100) <= successRate){
            Location vialLocation = map.locationOf(this);
            vialLocation.addItem(new HealingVial());
        }
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(30, "Whacks",50);
    }

}
