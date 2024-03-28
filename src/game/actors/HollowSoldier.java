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
import game.Status;
import game.actions.AttackAction;
import game.behaviours.AttackBehaviour;
import game.behaviours.FollowBehaviour;
import game.behaviours.WanderBehaviour;
import game.items.HealingVial;
import game.items.OldKey;
import game.items.Rune;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class HollowSoldier extends Enemy {


    /**
     * random library
     */
    Random random = new Random();

    /**
     * The constructor of the Actor class.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public HollowSoldier() {
        super("Hollow Soldier", '&', 200);
        this.addCapability(Status.BURIAL_GROUND);
    }


    public void spawnRunes(GameMap map){
        Location runeLocation = map.locationOf(this);
        Rune runes = new Rune(100);
        runeLocation.addItem(runes);
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(50,"attacks",50);

    }

    public void spawnVial(GameMap map) {
        int successRate = 90;
        if (random.nextInt(100) <= successRate){
            Location vialLocation = map.locationOf(this);
            vialLocation.addItem(new HealingVial());
        }
    }


    @Override
    public void dropItem(GameMap map) {
        spawnVial(map);
        spawnRunes(map);
    }
}
