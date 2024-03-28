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
import game.items.BloodBerry;
import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.items.Rune;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LivingBranch extends Enemy{

    Random random = new Random();

    public LivingBranch() {
        super("Living Branch", '?', 75);
        this.addCapability(Status.SANCTUARY);
        this.addCapability(Status.IMMUNE_TO_PIT);
    }

    @Override
    public void dropItem(GameMap map) {
        spawnBloodBerry(map);
        spawnRunes(map);
    }

    public void spawnRunes(GameMap map){
        Location runeLocation = map.locationOf(this);
        Rune runes = new Rune(500);
        runeLocation.addItem(runes);
    }

    public void spawnBloodBerry(GameMap map) {
        int successRate = 50;
        if (random.nextInt(100) <= successRate){
            Location berryLocation = map.locationOf(this);
            berryLocation.addItem(new BloodBerry());
        }
    }

    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(250,"attacks",90);
    }
}
