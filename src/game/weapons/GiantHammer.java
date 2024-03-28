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
import game.actions.GreatSlamAction;
import game.actors.Player;

import java.util.ArrayList;
import java.util.List;

public class GiantHammer extends WeaponItem implements Skillable {

    Display display = new Display();
    /**
     * Constructor.
     *
     * @param GiantHammer name of the item
     * @param P character to use for display when item is on the ground
     * @param 160 amount of damage this weapon does
     * @param Slams verb to use for this weapon, e.g. "hits", "zaps"
     * @param 90 the probability/chance to hit the target.
     */
    public GiantHammer() {
        super("Giant Hammer", 'P', 160, "Slams", 90);
        this.addCapability(Ability.CAN_ATTACK);

    }

    @Override
    public List<Action> getActionSpecific(Actor actor) {

        List<Action> actions = new ArrayList<>();
        Player player = (Player) actor;

        if (actor.getAttribute(BaseActorAttributes.STAMINA) >= actor.getAttribute(BaseActorAttributes.STAMINA) * 0.25){
            actions.add(new GreatSlamAction(this));
        }
        return actions;
    }

    @Override
    public void activateSkills(Actor actor, GameMap map) {
        if (!this.hasCapability(Status.SLAMMED)) {
            this.addCapability(Status.SLAMMED);
        }

    }

    @Override
    public void deactivateSkills() {
        this.removeCapability(Status.SLAMMED);
    }

    @Override
    public void enemyAffected(Actor actor, GameMap map) {

        Location currentLocation = map.locationOf(actor);

        if (this.hasCapability(Status.SLAMMED)) {
            currentLocation.getActor().hurt((int) (160 * 0.50));
            display.println(currentLocation.getActor() + " " + "has been hit by the Great Hammer Ability");
            List<Exit> exits = currentLocation.getExits();
            for (Exit exit : exits) {
                Location locations = exit.getDestination();

                if (locations.containsAnActor()) {
                        locations.getActor().hurt((int) (160 * 0.50));
                        display.println("Actor" + locations.getActor() + " " + "has been hit by Great Hammer Ability ");
                    }
                deactivateSkills();
            }
        }
    }
    @Override
    public boolean canActivate(Status status) {
        return hasCapability(status);
    }
}
