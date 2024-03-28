package game.actors;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.actors.attributes.ActorAttributeOperations;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttribute;
import edu.monash.fit2099.engine.actors.attributes.BaseActorAttributes;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.displays.Menu;
import edu.monash.fit2099.engine.weapons.IntrinsicWeapon;
import edu.monash.fit2099.engine.weapons.Weapon;
import game.Ability;
import game.Status;
import game.actions.*;
import game.items.*;
import game.states.State;
import game.weapons.Skillable;

/**
 * Class representing the Player.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Player extends Actor {
    /**
     * Constructor.
     *
     * @param name        Name to call the player in the UI
     * @param displayChar Character to represent the player in the UI
     * @param hitPoints   Player's starting number of hitpoints
     */


    private State state;


    public Player(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
        this.addCapability(Status.HOSTILE_TO_ENEMY);
        this.addAttribute(BaseActorAttributes.STAMINA, new BaseActorAttribute(2000));
    }

    public String getName(){
        return this.name;
    }



    public Weapon getWeapon() {
        for (Item item : this.getItemInventory()) {
            if (item.hasCapability(Ability.CAN_ATTACK)){
                return (Weapon) item;
            }
        }
        return this.getIntrinsicWeapon();
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        generateStamina();

        for (Item item: getItemInventory()){

            if (item.hasCapability(Ability.CAN_ATTACK)){
                Skillable skillableWeapons = (Skillable) item;
                actions.add(skillableWeapons.getActionSpecific(this));
            }

            if (item.hasCapability(Status.CONSUMABLE)){
                Consumable consumableItems = (Consumable) item;
                actions.add(consumableItems.getActionSpecific(this));
            }

            if (item.hasCapability(Status.CURRENCY)){
                Rune rune = (Rune) item;
                actions.add(new AddToWalletAction(rune));
            }

        }

        // Handle multi-turn Actions
        displayStats(display);
        if (lastAction.getNextAction() != null)
            return lastAction.getNextAction();

        // return/print the console menu
        Menu menu = new Menu(actions);
        return menu.showMenu(this, display);

    }

    public void changeState(State state) {
        this.state = state;
    }

    private void displayStats(Display display){
        String name = this.getName();

        int health = this.getAttribute(BaseActorAttributes.HEALTH);
        int stamina = this.getAttribute(BaseActorAttributes.STAMINA);

        int healthMax = this.getAttributeMaximum(BaseActorAttributes.HEALTH);
        int staminaMax = this.getAttributeMaximum(BaseActorAttributes.STAMINA);

        display.println(name);
        display.println("HP:" + String.valueOf(health) + "/" + healthMax);
        display.println("Stamina:" + stamina + "/" + staminaMax);
        display.println("Runes" + " " + getBalance());
    }
    @Override
    public IntrinsicWeapon getIntrinsicWeapon() {
        return new IntrinsicWeapon(15,"bonks",80);
    }

    public void respawn(){
        this.state.onRespawn();
    }

    public void generateStamina(){
        int regenerate = this.getAttributeMaximum(BaseActorAttributes.STAMINA)/100;

        this.modifyAttribute(BaseActorAttributes.STAMINA, ActorAttributeOperations.INCREASE,regenerate);
        int curr = this.getAttribute(BaseActorAttributes.STAMINA);
        int max = this.getAttributeMaximum(BaseActorAttributes.STAMINA);
        if (curr> max){
            this.modifyAttribute(BaseActorAttributes.STAMINA,ActorAttributeOperations.UPDATE,max);
        }
    }
}
