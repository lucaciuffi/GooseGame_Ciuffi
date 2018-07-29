/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package goosegame;

import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;
import logic.Dice;
import scenario.Billboard;
import scenario.Player;

/**
 *
 * @author LUCA
 */
public class GooseGame_Ciuffi {

    private static Scanner scanner = new Scanner(System.in);
    private static TreeMap<String, Player> players = new TreeMap<>();
    private static Billboard billboard = Billboard.getInstance();
    private static boolean gameContinue;
    private static String prankString = new String("");

    private static void setGameContinue(boolean gameContinue) {
        GooseGame_Ciuffi.gameContinue = gameContinue;
    }

    /**
     * ;
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        billboard.initBillboard();

        setGameContinue(true);
        while (gameContinue) {

            // Prompt 
            System.out.print("Type data command for Goose: ");

            String input = scanner.nextLine();

            String lowerInput = input.toLowerCase();
            if (lowerInput.startsWith("add player ")) {
                String[] words = input.split("\\s+");
                String playername = "";

                for (int i = 2; i < words.length; i++) {

                    playername = playername.concat(words[i]);

                }
                Player p = new Player(playername);
                if (players.containsKey(p.getName())) {
                    System.out.println(playername + " : already existing");
                } else {
                    players.put(playername, p);
                    System.out.println("Players: \n");
                    for (Map.Entry<String, Player> entrySet : players.entrySet()) {
                        String key = entrySet.getKey();
                        Player value = entrySet.getValue();
                        System.out.println("" + value.getName());

                    }
                }
            } else if (input.startsWith("move")) {
                String[] words = input.split("\\s+");
                String playername = "";

                for (int i = 1; i < words.length; i++) {
                    playername = playername.concat(words[i]);
                }
                if (players.containsKey(playername)) {

                    movePlayer(playername, gameContinue);

                } else {
                    System.out.println("Big fingers you moved a not existing player");
                }
            } else {
                System.out.println("Big fingers , you should write correctly the commands");
            }
        }

    }

    private static void movePlayer(String playername, boolean gameContinue) {
        Dice dice = new Dice();
        dice.diceLaunch();

        int n = players.get(playername).getPosition() + dice.getLaunch1() + dice.getLaunch2();
        if (n == billboard.getDimension()) {

            players.get(playername).setPosition(n);
            System.out.println(playername + " rolls " + dice.getLaunch1() + ", " + dice.getLaunch2() + ". " + playername + " moves from " + players.get(playername).getPosition() + " to " + n + ". " + playername + " Wins!!!\n. END GAME");
            //setgame
            setGameContinue(false);
        } else if (n > billboard.getDimension()) {
            int x = billboard.getDimension() - (n - billboard.getDimension());
            if (prankCheck(playername, x, dice, prankString)) {
                int temp = players.get(playername).getPosition() - billboard.getDimension() + dice.getLaunch1() + dice.getLaunch2();
                int a = billboard.getDimension() - temp;
                System.out.println(playername + " rolls " + dice.getLaunch1() + ", " + dice.getLaunch2() + ". " + playername + " moves from " + a + " to " + billboard.getDimension() + ". " + playername + " bounce! " + playername + " returns to " + x + ". " + prankString);

            } else {
                int temp = players.get(playername).getPosition() - billboard.getDimension() + dice.getLaunch1() + dice.getLaunch2();
                int a = billboard.getDimension() - temp;
                System.out.println(playername + " rolls " + dice.getLaunch1() + ", " + dice.getLaunch2() + ". " + playername + " moves from " + a + " to " + billboard.getDimension() + ". " + playername + " bounce! " + playername + " returns to " + x + ".");

            }

        } else if (n < billboard.getDimension()) {
            if (n == billboard.getBridge()) {
                int toTheBridge = 12;
                if (prankCheck(playername, toTheBridge, dice, prankString)) {
                    int a = billboard.getBridge() - dice.getLaunch1() - dice.getLaunch2();
                    System.out.println(playername + " rolls " + dice.getLaunch1() + ", " + dice.getLaunch2() + ". " + playername + " moves from " + a + " to The Bridge. " + playername + " jumps to " + toTheBridge + ". " + prankString);
                } else {
                    int a = billboard.getBridge() - dice.getLaunch1() - dice.getLaunch2();
                    System.out.println(playername + " rolls " + dice.getLaunch1() + ", " + dice.getLaunch2() + ". " + playername + " moves from " + a + " to The Bridge. " + playername + " jumps to " + toTheBridge + ".");
                }
            } else if (billboard.getGooseArray().contains(n)) {
                if (billboard.getGooseArray().contains(n + dice.getLaunch1() + dice.getLaunch2())) {
                    int z = n + dice.getLaunch1() + dice.getLaunch2();
                    int k = z + dice.getLaunch1() + dice.getLaunch2();

                    if (prankCheck(playername, k, dice, prankString)) {
                        int a = n - dice.getLaunch1() - dice.getLaunch2();
                        System.out.println(playername + " rolls " + dice.getLaunch1() + ", " + dice.getLaunch2() + ". " + playername + " moves from " + a + " to " + n + " The Goose. " + playername + " moves again and goes to " + z + " The Goose. " + playername + " moves again and goes to " + k + ". " + prankString);
                    } else {
                        int a = n - dice.getLaunch1() - dice.getLaunch2();
                        System.out.println(playername + " rolls " + dice.getLaunch1() + ", " + dice.getLaunch2() + ". " + playername + " moves from " + a + " to " + n + " The Goose. " + playername + " moves again and goes to " + z + " The Goose. " + playername + " moves again and goes to " + k + ".");
                    }
                } else {
                    int z = n + dice.getLaunch1() + dice.getLaunch2();
                    if (prankCheck(playername, z, dice, prankString)) {
                        int a = n - dice.getLaunch1() - dice.getLaunch2();
                        System.out.println(playername + " rolls " + dice.getLaunch1() + ", " + dice.getLaunch2() + ". " + playername + "moves from " + a + " to " + n + " The Goose. " + playername + " moves again and goes to " + z + ". " + prankString);
                    } else {
                        int a = n - dice.getLaunch1() - dice.getLaunch2();
                        System.out.println(playername + " rolls " + dice.getLaunch1() + ", " + dice.getLaunch2() + ". " + playername + "moves from " + a + " to " + n + " The Goose. " + playername + " moves again and goes to " + z + ".");
                    }
                }
                //Per fare un metodo generico avrei richiamato la funzione movePlayer(), ma in questo contesto è possibile fare solo un doppio salto.

            } else {
                if (prankCheck(playername, n, dice, prankString)) {
                    int a = players.get(playername).getPosition() - dice.getLaunch1() - dice.getLaunch2();
                    System.out.println(playername + " rolls " + dice.getLaunch1() + ", " + dice.getLaunch2() + ". " + playername + " moves from " + a + " to " + n + ". " + prankString);
                } else {
                    int a = players.get(playername).getPosition() - dice.getLaunch1() - dice.getLaunch2();
                    System.out.println(playername + " rolls " + dice.getLaunch1() + ", " + dice.getLaunch2() + ". " + playername + " moves from " + a + " to " + n + ".");
                }
            }

        }
    }

    public static void setPrankString(String prankString) {
        GooseGame_Ciuffi.prankString = prankString;
    }

    private static boolean prankCheck(String playername, int n, Dice dice, String prankString1) {
        boolean prank = billboard.checkBox(n);
        int busy=0;

        if (billboard.checkBox(n)) {
            for (Map.Entry<String, Player> entrySet : players.entrySet()) {
                String key = entrySet.getKey();
                Player value = entrySet.getValue();
                if (value.getPosition() == n && value.getName().equals(playername)) {
                    prank=false;
                } else if (value.getPosition() == n && !value.getName().equals(playername)) {
                    billboard.getBillboard().get(value.getPosition()).setBusy(false);
                    value.setPosition(players.get(playername).getPosition());
                    billboard.getBillboard().get(value.getPosition()).setBusy(true);
                    players.get(playername).setPosition(n);
                    billboard.getBillboard().get(n).setBusy(true);
                    prankString1 = "On " + n + " there is " + value.getName() + " who returns to " + value.getPosition() + ".";
                    prankString = prankString1;
                    prank=true;
                }
            }
            if (busy==1){
            prank=true;
            }

        } else {
            billboard.getBillboard().get(players.get(playername).getPosition()).setBusy(false);
            players.get(playername).setPosition(n);
            billboard.getBillboard().get(n).setBusy(true);

        }
        return prank;
    }

}
