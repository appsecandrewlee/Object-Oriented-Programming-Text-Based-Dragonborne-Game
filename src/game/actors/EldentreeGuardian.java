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
import game.items.Consumable;
import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.items.Rune;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class EldentreeGuardian extends Enemy{
    Random random = new Random();

    public EldentreeGuardian() {
        super("EldenTree Guardian", 'e', 250);
        this.addCapability(Status.SANCTUARY);
        this.addCapability(Status.IMMUNE_TO_PIT);
    }

    @Override
    public void dropItem(GameMap map) {
        spawnVial(map);
        spawnRunes(map);
        spawnFlask(map);
    }

    public void spawnRunes(GameMap map){
        Location runeLocation = map.locationOf(this);
        Rune runes = new Rune(250);
        runeLocation.addItem(runes);
    }

    public void spawnVial(GameMap map) {
        int successRate = 25;
        if (random.nextInt(100) <= successRate){
            Location vialLocation = map.locationOf(this);
            vialLocation.addItem(new HealingVial());
        }
    }

    public void spawnFlask(GameMap map) {
        int successRate = 15;
        if (random.nextInt(100) <= successRate) {
            Location flaskLocation = map.locationOf((this));

            RefreshingFlask flask = new RefreshingFlask();
            flaskLocation.addItem(flask);
        }
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(50,"attacks",80);
    }
}
