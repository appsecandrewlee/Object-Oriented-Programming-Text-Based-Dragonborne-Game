package game.weapons;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.positions.Exit;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.Ability;
import game.Status;
import game.actions.FocusAction;
import game.actions.StabAndStepAction;
import game.actors.Player;

import java.util.ArrayList;
import java.util.Random;

import java.util.List;

public class GreatKnife extends WeaponItem implements Skillable{

    /**
     * Constructor.
     *
     * @param GreatKnife        name of the item
     * @param > character to use for display when item is on the ground
     * @param 75      amount of damage this weapon does
     * @param Stabs        verb to use for this weapon, e.g. "hits", "zaps"
     * @param 70     the probability/chance to hit the target.
     */
    public GreatKnife() {
        super("Great Knife",'>',75,"Stabs", 70);
        this.addCapability(Ability.CAN_ATTACK);
    }

    @Override
    public void tick(Location currentLocation, Actor actor) {
        super.tick(currentLocation);
    }

    /**
     *
     * @param actor if it has the capability Status_Stab_AND_STEP
     * @param map
     */
    @Override
    public void activateSkills(Actor actor, GameMap map) {

        if (!this.hasCapability(Status.STAB_AND_STEP)) {
            this.addCapability(Status.STAB_AND_STEP);
        }
    }

    /**
     *
     * @param actor
     * @return
     */
    @Override
    public List<Action> getActionSpecific(Actor actor) {
        List<Action> actions = new ArrayList<>();

        if (actor.getAttribute(BaseActorAttributes.STAMINA) > actor.getAttribute(BaseActorAttributes.STAMINA) * 0.25){
            actions.add(new StabAndStepAction(this));
        }
        return actions;
    }

    @Override
    public void deactivateSkills() {
        if (this.hasCapability(Status.STAB_AND_STEP)){
            this.removeCapability(Status.STAB_AND_STEP);
        }
    }

    @Override
    public void enemyAffected(Actor actor, GameMap map) {
        Location currentLocation = map.locationOf(actor);
        if (this.hasCapability(Status.STAB_AND_STEP)) {

            List<Exit> exits = currentLocation.getExits();
            List<Location> validLocations = new ArrayList<>();
            for (Exit exit : exits) {
                Location locations = exit.getDestination();
                if (!locations.containsAnActor() && locations.canActorEnter(actor)) {
                    validLocations.add(locations);
                }
            }
            if (!validLocations.isEmpty()) {
                Location moveLocation = validLocations.get(new Random().nextInt(validLocations.size()));

                currentLocation.map().removeActor(actor);
                moveLocation.addActor(actor);
                deactivateSkills();
            }
        }
    }

    @Override
    public boolean canActivate(Status status) {
        return hasCapability(status);
    }
}
