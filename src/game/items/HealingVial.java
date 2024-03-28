package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.Status;
import game.actions.HealingAction;
import game.upgrades.Upgradable;
import game.upgrades.Upgrade;

import java.util.ArrayList;
import java.util.List;

import static edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes.HEALTH;

public class HealingVial extends Upgrade implements Consumable, Upgradable {


    boolean a;
    double healingPercentage = 0.10;

    public HealingVial() {
        super("Healing Vial", 'a', true);
        this.addCapability(Status.CONSUMABLE);
    }

    @Override
    public void canConsume(Actor actor) {
        actor.heal((int) (actor.getAttributeMaximum(HEALTH) * healingPercentage));
    }

    @Override
    public List<Action> getActionSpecific(Actor actor) {
        List<Action> actions = new ArrayList<>();
        actions.add(new HealingAction(this));
        return actions;
    }

    @Override
    public void canUpgrade(Item item) {
        a = true;
        this.healingPercentage = 0.80;
    }

    @Override
    public String toString() {
        if (a){
            return "Upgraded Healing Vial";
        }
        else{
            return "Healing Vial";
        }
    }
}
