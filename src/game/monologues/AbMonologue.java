package game.monologues;

import edu.monash.fit2099.engine.actions.Action;
import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actions.DoNothingAction;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.items.Item;
import edu.monash.fit2099.engine.positions.GameMap;
import game.Status;
import game.actions.MonologueAction;
import game.actions.SellingAction;
import game.actions.ShoppingAction;
import game.actions.UpgradeAction;
import game.items.BloodBerry;
import game.items.HealingVial;
import game.items.RefreshingFlask;
import game.upgrades.Upgrade;
import game.weapons.BroadSword;
import game.weapons.GiantHammer;
import game.weapons.GreatKnife;

import java.util.*;


/**
 * Andrew Lee, Charlies Liu
 * Abstract Monologue class that is extendable to the actors that is capable of Monologue
 *
 *
 */
public abstract class AbMonologue extends Actor {

    public HashMap<Integer, String> monologue = new HashMap<Integer, String>();


    public BloodBerry bloodBerry = new BloodBerry();
    public BroadSword broadSword = new BroadSword();
    public HealingVial healingVial = new HealingVial();
    public RefreshingFlask refreshingFlask = new RefreshingFlask();
    public GreatKnife greatKnife = new GreatKnife();
    public GiantHammer giantHammer = new GiantHammer();



    public Map<Item, Integer> priceItemSell = new HashMap<>();
    public Map<String, Integer> priceItemBuy = new HashMap<>();
    /**
     * The constructor of the Actor class.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     * adding all the monoologue options that is found across all options for extensibility purposes
     *
     */
    public AbMonologue(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);


        monologue.put(1, "I used to be an adventurer like you, but then …. Nevermind, let’s get back to smithing.");
        monologue.put(2, "It’s dangerous to go alone. Take my creation with you on your adventure!\n" +
                "\n");
        monologue.put(3, "Ah, it’s you. Let’s get back to make your weapons stronger.");
        monologue.put(4, "Beyond the burial ground, you’ll come across the ancient woods ruled by Abxervyer. Use my creation to slay them and proceed further!");
        monologue.put(5, "Hey now, that’s a weapon from a foreign land that I have not seen for so long. I can upgrade it for you if you wish.");
        monologue.put(6, "Somebody once told me that a sacred tree rules the land beyond the ancient woods until this day.");
        monologue.put(7, "Of course, I will never give you up, valuable customer!");
        monologue.put(8, "I promise I will never let you down with the quality of the items that I sell.");
        monologue.put(9, "You can always find me here. I'm never gonna run around and desert you, dear customer!");
        monologue.put(10, "I'm never gonna make you cry with unfair prices.");
        monologue.put(11, "Trust is essential in this business. I promise I’m never gonna say goodbye to a valuable customer like you.");
        monologue.put(12, "Don't worry, I’m never gonna tell a lie and hurt you.");
        monologue.put(13, "Ooh, that’s a fascinating weapon you got there. I will pay a good price for it. You wouldn't get this price from any other guy.");
        monologue.put(14, "You know the rules of this world, and so do I. Each area is ruled by a lord. Defeat the lord of this area, Abxervyer, and you may proceed to the next area.");
        monologue.put(15, "Congratulations on defeating the lord of this area. I noticed you still hold on to that hammer. Why don’t you sell it to me? We've known each other for so long. I can tell you probably don’t need that weapon any longer.");


        this.addItemToInventory(bloodBerry);
        this.addItemToInventory(broadSword);
        this.addItemToInventory(healingVial);
        this.addItemToInventory(refreshingFlask);
        this.addItemToInventory(greatKnife);
        this.addItemToInventory(giantHammer);

    }
    @Override
    public ActionList allowableActions(Actor otherActor, String direction, GameMap map) {

        ActionList actions = new ActionList();
        actions.add(new MonologueAction(this));
        return actions;
    }

    @Override
    public Action playTurn(ActionList actions, Action lastAction, GameMap map, Display display) {

        return new DoNothingAction();

    }

    public int getPrice(Item item){
        return priceItemSell.getOrDefault(item, 0);
    }

    public int getBuyingPrice(Item item){
        return priceItemBuy.getOrDefault(item.toString(),0);
    }


    public abstract String getMonologue(Actor otherActor);
}
