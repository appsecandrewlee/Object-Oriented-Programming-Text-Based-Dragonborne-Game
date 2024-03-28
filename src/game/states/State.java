package game.states;

import game.actors.Player;


/**
 * State machine for keeping track of current player state
 * Andrew Lee
 */
public abstract class State {
    Player player;

    public State(Player player) {
        this.player = player;
    }

    /**
     * onRespawn method for setting up behaviour that is upon respawning for the player
     */
    public abstract void onRespawn();
}