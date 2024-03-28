package game.actions;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actors.BlackSmith;
import game.actors.TheIsolatedTraveller;
import game.upgrades.Upgradable;
import game.upgrades.Upgrade;


/**
 *
 *
 *
 */
public class UpgradeAction extends Action {

    private final Upgrade upgradable;


    /**
     * Andrew Lee
     * @param upgradable
     */
    public UpgradeAction(Upgrade upgradable) {
        this.upgradable = upgradable;
    }


    /**
     *  Andrew Lee
     * @param Player The actor performing the action.
     * @param currMap The map the actor is on.
     * @return whether the operation of upgrading has been performed successfuly.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        
        if (upgradable.hasCapability(Status.CAN_UPGRAD)){
            upgradable.canUpgrade(upgradable);
            return actor.toString() + "has upgraded" + " " + upgradable;

        }
        return actor.toString() + "Upgraded successfully";
    }

    /**
     *  Andrew Lee
     * @param actor The actor performing the action.
     * @return menu description for upgradable when player wants to upgrade it
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + "want to upgrade" + " " + upgradable +"?";
    }
}
