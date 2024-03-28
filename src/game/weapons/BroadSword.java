package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Ability;
import game.Status;
import game.actions.FocusAction;
import game.actors.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BroadSword extends WeaponItem implements Skillable{
    private int focusTurns = 0;

    /**
     * Constructor.
     *
     * @param BroadSword  name of the item
     * @param 1 character to use for display when item is on the ground
     * @param 110 amount of damage this weapon does
     * @param attacks verb to use for this weapon, e.g. "hits", "zaps"
     * @param 80 the probability/chance to hit the target.
     */
    public BroadSword() {
        super("BroadSword", '1', 110, "attacks", 80);
        this.addCapability(Ability.CAN_ATTACK);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation);
        tickFocus();

    }

    public void tickFocus() {
        if (this.hasCapability(Status.IS_FOCUSED)) {
            focusTurns++;
//            display.println("incremented to " + focusTurns);
        }
        if (focusTurns == 5) {
           this.deactivateSkills();
        }
    }


    @Override
    public List<Action> getActionSpecific(Actor actor) {
        List<Action> actions = new ArrayList<>();

        if(actor.getAttribute(BaseActorAttributes.STAMINA) >= 40){
            actions.add(new FocusAction(this));
        }
        return actions;
    }

    @Override
    public void activateSkills(Actor actor, GameMap map) {
        if (!this.hasCapability(Status.IS_FOCUSED)) {
            this.addCapability(Status.IS_FOCUSED);
        }
        this.increaseDamageMultiplier(0.1f);
    }


    @Override
    public void deactivateSkills() {
        this.removeCapability(Status.IS_FOCUSED);
        this.updateDamageMultiplier(1.0f);
        this.updateHitRate(80);
        focusTurns = 0;
    }

    @Override
    public void enemyAffected(Actor actor, GameMap map) {

    }

    @Override
    public boolean canActivate(Status status) {
        return hasCapability(status);
    }

}
