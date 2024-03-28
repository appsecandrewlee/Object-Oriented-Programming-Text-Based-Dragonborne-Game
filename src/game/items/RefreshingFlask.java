package game.items;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.items.Item;
import game.Status;
import game.actions.StaminaRegenAction;
import game.actors.Player;
import game.upgrades.Upgradable;

import java.util.ArrayList;
import java.util.List;

public class RefreshingFlask extends Item implements Consumable, Upgradable {
    /***
     * Constructor.
     *  @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public RefreshingFlask() {
        super("refreshing Flask",'u',true);
        this.addCapability(Status.CONSUMABLE);
    }

    @Override
    public void canConsume(Actor actor) {
        int replenishedStamina = (int) (actor.getAttributeMaximum(BaseActorAttributes.STAMINA) * 0.20);
        int newStamina = actor.getAttribute(BaseActorAttributes.STAMINA) + replenishedStamina;
        actor.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.UPDATE,newStamina);
    }

    @Override
    public List<Action> getActionSpecific(Actor actor) {
        List<Action> actions = new ArrayList<>();
        actions.add(new StaminaRegenAction(this));

        return actions;
    }

    @Override
    public void canUpgrade(Item item) {
    }
}
