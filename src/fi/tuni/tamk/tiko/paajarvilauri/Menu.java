package src.fi.tuni.tamk.tiko.paajarvilauri;

import src.fi.tuni.tamk.tiko.paajarvilauri.util.Console;

/**
 * Contains the game settings, that user can accept, or change them.
 * 
 * <p>With the help of fi.tuni.tamk.tiko.paajarvilauri.util.Console class,
 * user is allowed to choose Level Length, Target Score and whether they're
 * playing as 'X' or 'O'. Variables int targetScore, boolean isPlayerX and
 * int levelLength will be used as parameters in the constructor of Game
 * class.
 * 
 * @author Lauri Paajarvi -lauri.paajarvi@tuni.fi-
 * @version 2019.1210
 * @since 1.8
 */

class Menu {
    final static int MINLENGTH=3;
    final static int MAXLENGTH=24;

    //These should be initiated only once in some class, but don't know how it's done
    final static char CHAR_X='X';
    final static char CHAR_O='O';
    final static char EMPTY=' ';
    
    int targetScore=4;
    boolean isPlayerX=true;
    int levelLength=9;

    
    /**
     * Constructor, which also holds the functionality of the class.
     * 
     * <p>Lets user choose the settings they want, using console. While the
     * method doesn't really return anything, the information that
     * is used later are int targetScore, boolean isPlayerX and
     * int levelLength.
     * 
     */
    public Menu() {

        int minTargetScore;
        int maxTargetScore;

        boolean acceptSettings=false;

        while (!acceptSettings) {
            printSettings();
            acceptSettings = Console.readBooleanYorN("Do you want to start the game with these settings?");
            if (!acceptSettings) {
                System.out.println("How many tiles long do you want the level to be? Min: "+MINLENGTH+", Max: "+MAXLENGTH+".");
                levelLength = Console.readInt(MINLENGTH, MAXLENGTH, "Please type a number", "The number must be between "+MINLENGTH+" and "+MAXLENGTH+"!");
                if (levelLength==3) {
                    System.out.println("Ok, classic Tic-Tac-Toe for you then, target score will be 3 as well.");
                    targetScore=3;
                } else {
                    if (levelLength < 10) {
                        minTargetScore=3;
                        if (levelLength < 5) {
                            maxTargetScore = levelLength;
                        } else {
                            maxTargetScore = 5;
                        }
                    } else {
                        minTargetScore=5;
                        maxTargetScore=7;
                    }
                    System.out.println("How many tiles in a row do you want to be needed for a victory?");
                    System.out.println("With a level length of "+levelLength+", you are required to choose a number between "+minTargetScore+" and "+maxTargetScore+".");
                    targetScore = Console.readInt(minTargetScore, maxTargetScore, "Please type a number", "The target score must be between "+minTargetScore+" and "+maxTargetScore+".");
                }
                isPlayerX=Console.readBooleanYorN("Finally, do you want to play with '"+CHAR_X+"' as your character?");
                if (isPlayerX) {
                    System.out.println("Ok, the computer will play with '"+CHAR_O+"'.");
                } else {
                    System.out.println("Fine, '"+CHAR_O+"' it is for you then instead.");
                }
            }
        }
    }

     /**
     * Prints the settings, that are currently chosen, to the user.
     */

    public void printSettings() {

        char humanChar=CHAR_X;
        char computerChar=CHAR_O;

        if (isPlayerX) {
            humanChar=CHAR_X;
            computerChar=CHAR_O;
        } else {
            humanChar=CHAR_O;
            computerChar=CHAR_X;
        }
        for (int i=0; i<3; i++) {
            System.out.println();
        }
        System.out.println("Level Length:      "+levelLength+" x "+levelLength+".");
        System.out.println("Target Score:      "+targetScore+" in a row needed to win.");
        System.out.println("Player Character:  '" +humanChar+"' (computer uses '"+computerChar+"').");

        System.out.println();
    }
}