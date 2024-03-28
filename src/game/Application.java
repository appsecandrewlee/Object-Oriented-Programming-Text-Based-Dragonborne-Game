package game;

import java.util.Arrays;
import java.util.List;

import edu.monash.fit2099.engine.displays.Display;
import edu.monash.fit2099.engine.positions.FancyGroundFactory;
import edu.monash.fit2099.engine.positions.GameMap;
import edu.monash.fit2099.engine.positions.World;
import game.actors.*;
import game.grounds.*;
import game.items.BloodBerry;
import game.items.HealingVial;
import game.items.OldKey;
import game.weapons.BroadSword;
import game.weapons.GiantHammer;
import game.weapons.GreatKnife;

/**
 * The main class to start the game.
 * Created by:
 * @author Adrian Kristanto
 * Modified by:
 *
 */
public class Application {


    private static GameMap burialGround;

    public static void main(String[] args) {

        World world = new World(new Display());

        FancyGroundFactory groundFactory = new FancyGroundFactory(new Dirt(),
                new Wall(), new Floor(), new Puddle(),new Pitt(),new Graveyard(), new Hut(),new Bush());


        List<String> map = Arrays.asList(
                "....+++..............................+++++++++....~~~....~~~",
                "+...+++.................m............++++++++.....~~~.....~~",
                "++........n......#######..............++++.........~~.......",
                "++...............#_____#....................m......~~~......",
                "+................#_____#............................~~......",
                ".................###_###.......+....~...............~~.....~",
                "......n........................~.+++~~..............~~....~~",
                ".....................~........~~+++++...............~~~...~~",
                "........h...........~~~.........++++............~~~~~~~...~~",
                "....................~~~~.~~~~..........~...h....~~~~~~.....~",
                "++++...............~~~~~~~~~~~........~~~.......~~~~~~......",
                "+++++..............~~~~~~~~~~~........~~~........~~~~~......");

        List<String> burialGround = Arrays.asList(
                "...........+++++++........~~~~~~++....~~",
                "...........++++++.........~~~~~~+.....~~",
                "............++++...........~~~~~......++",
                "............+.+.............~~~.......++",
                "..........++~~~.......................++",
                ".........+++~~~....#######...........+++",
                ".........++++~.....#_____#.........+++++",
                "..........+++......#_____#........++++++",
                "..........+++......###_###.......~~+++++",
                "..........~~..........n..........~~...++",
                "..........~~~..................++.......",
                "...........~~....~~~~~.........++.......",
                "......~~....++..~~~~~~~~~~~......~......",
                "....+~~~~..++++++++~~~~~~~~~....~~~.....",
                "....+~~~~..++++++++~~~..~~~~~..~~~~~....");

        List<String> AbxervyerBattleMap = Arrays.asList(
                "~~~~.......+++......~+++++..............",
                "~~~~.......+++.......+++++..............",
                "~~~++......+++........++++..............",
                "~~~++......++...........+..............+",
                "~~~~~~...........+.......~~~++........++",
                "~~~~~~..........++++....~~~~++++......++",
                "~~~~~~...........+++++++~~~~.++++.....++",
                "~~~~~..............++++++~~...+++.....++",
                "......................+++......++.....++",
                ".......................+~~............++",
                ".......................~~~~...........++",
                "........................~~++...........+",
                ".....++++...............+++++...........",
                ".....++++~..............+++++...........",
                "......+++~~.............++++...........~",
                ".......++..++++.......................~~",
                "...........+++++......................~~",
                "...........++++++.....................~~",
                "..........~~+++++......................~",
                ".........~~~~++++..................~~..~");

        List<String> OvergrownSanctuaryMap = Arrays.asList(
                "++++.....++++........++++~~~~~.......~~~..........",
                "++++......++.........++++~~~~.........~...........",
                "+++..................+++++~~.......+++............",
                "....................++++++......++++++............",
                "...................++++........++++++~~...........",
                "...................+++.........+++..~~~...........",
                "..................+++..........++...~~~...........",
                "~~~...........................~~~..~~~~...........",
                "~~~~............+++..........~~~~~~~~~~...........",
                "~~~~............+++.........~~~~~~~~~~~~..........",
                "++~..............+++.......+~~........~~..........",
                "+++..............+++......+++..........~~.........",
                "+++..............+++......+++..........~~.........",
                "~~~..............+++......+++..........~~~........",
                "~~~~.............+++......+++..........~~~........");


        GameMap gameMap = new GameMap(groundFactory, map);
        GameMap newMap = new GameMap(groundFactory, burialGround);
        GameMap BossMap = new GameMap(groundFactory, AbxervyerBattleMap);
        GameMap SanctuaryMap = new GameMap(groundFactory, OvergrownSanctuaryMap);

        world.addGameMap(gameMap);
        world.addGameMap(newMap);
        world.addGameMap(BossMap);
        world.addGameMap(SanctuaryMap);


        for (String line : FancyMessage.TITLE.split("\n")) {
            new Display().println(line);
            try {
                Thread.sleep(200);
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        gameMap.at(23, 10).addActor(new WanderingUndead());
        gameMap.at(10, 5).addActor(new WanderingUndead());
        gameMap.at(29,5).addItem(new BroadSword());
        gameMap.at(29,9).setGround(new Gate(gameMap, newMap,BossMap,SanctuaryMap));
        newMap.at(11,9).setGround(new Gate(newMap,BossMap,gameMap,SanctuaryMap));
        BossMap.at(11,9).setGround(new Gate(BossMap,SanctuaryMap,gameMap,SanctuaryMap));
        BossMap.at(11,9).setGround(new Gate(BossMap,gameMap,newMap,SanctuaryMap));
        SanctuaryMap.at(10,4).setGround(new Gate(SanctuaryMap,BossMap,gameMap,newMap));
        gameMap.at(29,6).addItem(new HealingVial());
        gameMap.at(29,7).addItem(new BloodBerry());
        gameMap.at(28,5).addItem(new GreatKnife());
        gameMap.at(27,6).addActor(new TheIsolatedTraveller());
        gameMap.at(28,6).addActor(new BlackSmith());
        gameMap.at(28,4).addItem(new OldKey());
        gameMap.at(28,5).addItem(new GiantHammer());



        Player player = new Player("The Abstracted One", '@', 100000);
        world.addPlayer(player, gameMap.at(28, 5));
        world.run();
    }
}
