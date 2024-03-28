package game.actions;
import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Ability;
import game.Status;
import game.grounds.Gate;

public class OpenGateAction extends Action {


    /**
     * GateAction used for gate
     *
     */
    private Gate gate;

    public OpenGateAction(Gate gate) {
        this.gate = gate;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        if (actor.hasCapability(Status.HOSTILE_TO_ENEMY)){
            //iterate through player inventory
            for (Item item : actor.getItemInventory()){
                if (item.hasCapability(Ability.CAN_UNLOCK_GATE)){
                    gate.removeCapability(Status.IS_LOCKED);

                    return("Gate unlocked");
                }
            }
            return ("Gate is still locked, you do not have the key");
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor.toString() + " opens gate";
    }
}
