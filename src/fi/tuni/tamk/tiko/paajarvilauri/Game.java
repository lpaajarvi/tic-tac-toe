package src.fi.tuni.tamk.tiko.paajarvilauri;

import java.util.Scanner;
import src.fi.tuni.tamk.tiko.paajarvilauri.util.Arrays;
import src.fi.tuni.tamk.tiko.paajarvilauri.util.Math;
import src.fi.tuni.tamk.tiko.paajarvilauri.util.Console;

/**
 * Contains most of the code used in TicTacToe, including Ai as inner class.
 * 
 * <p>
 * This class could be split into many different classes with different tasks.
 * The current version holds almost all functionality of the game.
 * <P>
 * Some of the aspects of this class, which could be separeted and made
 * a class of their own:
 * <P>
 * - The board data. Not sure what other things there could be though, than
 * the char[][] board , which holds the information about the tiles that have
 * been occupied by one of the players, and the empty tiles, and of course
 * the size of the board which is determined by the level length it takes
 * as parameter in its constructor.
 * <P>
 * - Checks made to the board. The following methods could be in this class:
 * <P>
 * boolean checkVictoryCase(..)<BR>
 * int checkCloseVictoryCase(..)<BR>
 * int toHowManyDirectionsIsThisMoveCloseToAwinningMove(..) <BR>
 * boolean isThisAwinningMove(..)<BR>
 * void checkIfThisPlayerWon()<BR>
 * voic checkIfDraw()<BR>
 * Also maybe the checks from the Game's inner class Ai.<BR>
 * <P>
 * - Everything that is printed during the game on console:
 * <P>
 * void clearScreen()<BR>
 * void printBoard()<BR>
 * void printButtons()<BR>
 * void printCursorPosition()<BR>
 *  <P>
 * - User interface, containing basically this wasd+some other keys, as in
 * char[] possibleInputs, and the functionality where it is used. the methods
 * this class could hold:
 * <P>
 * void move(..)<BR>
 * void moveToDirection(..)<BR>
 * void makeSelection(..)<BR>
 * char getDirection(..)<BR>
 * void askAboutExit()<BR>
 * <P>
 * - Coordinates/Cursor, all of the coordinates that are made using posY+posX,
 * curY,curX, compY,compX and some methods that return an array (int[]{ y, x}),
 * that could be isntance of this class as well.
 * It could probably hold a method that takes care of this code, that is used
 * as identical in 2 methods, returning the instance of the class, with the
 * coordinates (int y, int x) that are its attributes.
 * <P>
 *         if(dir==1) {<BR>
 *          dirY=1;<BR>
 *          dirX=0;<BR>
 *      } else if(dir==2) {<BR>
 *          dirY=0;<BR>
 *          dirX=1;<BR>
 *      } else if(dir==3) {<BR>
 *          dirY=1;<BR>
 *          dirX=1;<BR>
 *      } else {<BR>
 *          dirY=1;<BR>
 *          dirX=-1;<BR>
 *      }<BR>
 * <P>
 * - And finally the Game class itself would hold the methods that take care
 * of the game going on, so probably the following methods could stay here:
 * <P>
 * void startNewGame()<BR>
 * void startNewTurn()<BR>
 * void endCurrentPlayersTurn()<BR>
 * void setUpBoard()<BR>
 * void setUpPlayers()<BR>
 * void computerPlaysTheirTurn()<BR>
 * 
 * 
 * 
 * 
 * @author Lauri Paajarvi -lauri.paajarvi@tuni.fi-
 * @version 2019.1210
 * @since 1.8
 */

class Game {
    final static char EDGE='#';
    final static char CURSOR_ON_EMPTY='+';
    final static char CURSOR_ON_X='%';
    final static char CURSOR_ON_O='0';

    final static char UP='w';
    final static char RIGHT='d';
    final static char DOWN='s';
    final static char LEFT='a';

    final static char DOWN_ALTERNATIVE='x';

    final static char UPLEFT='q';
    final static char UPRIGHT='e';
    final static char DOWNLEFT='z';
    final static char DOWNRIGHT='c';
    
    final static char SELECT=' ';
    final static char EXIT='t';

    final static char CHAR_X='X';
    final static char CHAR_O='O';
    final static char EMPTY=' ';

    /**
     * Contains the characters that player can enter in console on their turn.
     */
    char[] possibleInputs = new char[]{UP, RIGHT, DOWN, LEFT, DOWN_ALTERNATIVE, UPLEFT, UPRIGHT, DOWNLEFT, DOWNRIGHT, SELECT, EXIT};

    /**
     * The next 4 variables values will be replaced in the constructor.
     * <P>

     */
    /**
     * Size of the board. N * N.
     */
    int levelLength=3;
    /**
     * How many samy tiles in a row are needed to win
     */
    int targetScore=3;
    /**
    * Keeps data of the situation in the game (tiles occupied by each player)
    */ 
    char[][] board = new char[3][3];
    /**
     * Whether player is CHAR_X or CHAR_O.
     */
    boolean isPlayerX;

    /**
     * Indicates whose turn it currently is.
     */
    boolean isItHumansTurn=true;
    
    /**
     * Holds information about each players character used etc (class Player)
     */
    Player human;
    Player computer;

    /**
     * Only instance of the huge inner class Ai that determines computer moves.
     */
    Ai ai;

    /**
     * Indicates whether the game is over. This info is used in startNewGame().
     */
    boolean roundEnded=false;

    /**
     * Player's cursor's Y position on the board.
     * <P>
     * Players cursor position on the board, that can be moved with WASD
     * keys and other keys in char[] possibleInputs Array. They will also
     * be used for checks that determine if the last put title brought
     * them a victory. NOTE: Cursor is never really something that is put to
     * replace any value in the char[][] board itself, only these coordinates
     * are used while the board is printed for user to see to show the cursor
     * position.
     */
    int curY=0;
        /**
     * Player's cursor's X position on the board.
     * <P>
     * Players cursor position on the board, that can be moved with WASD
     * keys and other keys in char[] possibleInputs Array. They will also
     * be used for checks that determine if the last put title brought
     * them a victory. NOTE: Cursor is never really something that is put to
     * replace any value in the char[][] board itself, only these coordinates
     * are used while the board is printed for user to see to show the cursor
     * position.
     */
    int curX=0;

    /**
     * Computer's move's (and potential move's) coordinate on the board
     * <P>
     * They are used a lot in inner class Ai determining computers future move.
     */
    int compY=0;
        /**
     * Computer's move's (and potential move's) coordinate on the board
     * <P>
     * They are used a lot in inner class Ai determining computers future move.
     */
    int compX=0;
    /**
     *  Keeps track of empty tiles on board.
     * <P>
     * Will be set according to levelSize that is determined in the constructor
     */ 
    int emptyTilesLeft=9;
    /**
     * Keeps track of how many games hvbn played (wins for either player+draws)
     */
    int gamesPlayed=0;

   /** 
    *  Shown to the player after a draw. loss or a victory.
    */
    String gameOverMessage = "";

   /**
    * Changes to false if player wants to quit the game after game has ended.
    * 
    * <p> Checked after a game is finished, after it is changed to false,
    * the game will end
    */ 
    boolean playerWantsToContinue=true;
    
    /**
     * Shown to user each time the refreshview() method is used.
     * <P>
     * Console will show these messages after each time refreshView() -method
     * is called, meaning they will be shown after player has made their move
     * or tried to put a tile on a place where there is already a tile, and that
     * is when errorMessage is needed. They will often be empty.
     */
    private String infoMessage="";
        /**
     * Shown to user each time the refreshview() method is used.
     * <P>
     * Console will show these messages after each time refreshView() -method
     * is called, meaning they will be shown after player has made their move
     * or tried to put a tile on a place where there is already a tile, and that
     * is when errorMessage is needed. They will often be empty.
     */
    private String errorMessage="";

    /**
     * Ends current players turn
     */
    public void endCurrentPlayersTurn() {
        isItHumansTurn = !isItHumansTurn;
    }

    /**
     * Checks if there are any tiles left on board. If not, then it's a draw.
     */
    public void checkIfDraw() {
        if (emptyTilesLeft == 0) {
            //actually probably not needed for both
            human.addDraw();
            computer.addDraw();
            gameOverMessage="It was a draw!";
            gameOver();
        }
    }
    /**
     * Prints the contents of the char[] board, while adding EDGE on edges.
     */
    public void printBoard() {
        //makes the boards both sides lengths 2 characters longer, so there is room for the EDGEs
        char[][] uiBoard = new char[board.length+2][board[0].length+2];

        for (int y=0; y<uiBoard.length; y++) {
            for (int x=0; x<uiBoard[0].length; x++) {
                if (y==0 || x==0 || y==uiBoard.length-1 || x==uiBoard.length-1) {
                    System.out.print(EDGE);
                } else {
                    if (y-1 != curY || x-1 != curX) {
                        System.out.print(board[y-1][x-1]);
                    } else {
                        if (board[curY][curX]==CHAR_X) {
                            System.out.print(CURSOR_ON_X);
                        } else if (board[curY][curX]==CHAR_O) {
                            System.out.print(CURSOR_ON_O);
                        } else {
                            System.out.print(CURSOR_ON_EMPTY);
                        }
                    }
                }
            }
            System.out.println();
        }
    /**
     * Makes some room just before the new situation on board is printed.
     */
    }
    public void clearScreen() {
        /*2 can be replaced by for example 45 to make this look a bit more like a real game and not just lines in a console.
        maybe clearScreen isn't that good name for this method anymore since i thought it's maybe better to just have 2 lines*/
        for(int i=0; i<2; i++) {
            System.out.println();
        }
    }
    /**
     * Prints buttons that player can use to play the game and some game stats.
     */
    public void printButtons() {
        /*this will print pressable buttons (possible inputs). If the final chars UP, UPRIGHT etc would be modified,
        this might need some tweaking too, but it is designed to stay up-to-date, using the final chars of this class.

        NOTE: Space Button is typed as [SPACE] here so if the final char SELECT in this class would be changed from ' '
        to something else, it would not be updated automatically here*/

        System.out.println("Move the cursor ("+CURSOR_ON_EMPTY+") or make a selection");
        System.out.println("by pressing one of these buttons and [ENTER]:");
        System.out.println();
        System.out.println(UPLEFT+"  "+UP+"  "+UPRIGHT+"     Select: [SPACE] | Scoreboard (Wins):");
        System.out.println(" \\   /                      |");
        System.out.println(LEFT+"-   -"+RIGHT+"     Exit Game: "+EXIT+"    |  Human    : "+human.wins() );
        System.out.println(" / "+DOWN+" \\                      |  Computer : "+computer.wins() );
        System.out.println(DOWNLEFT+"  "+DOWN_ALTERNATIVE+"  "+DOWNRIGHT+"                     |  Draws    : "+human.draws() );
    }
    /**
     * Calls a few methods to print the board and some other info.
     */
    public void refreshView() {
        clearScreen();
        printButtons();
        printBoard();
        
        printCursorPosition();
        //if there is errorMessage to be shown, it will be shown first on the left to catch the attention
        if (!errorMessage.equals("")) {
            System.out.println(" | "+errorMessage);
        }
        if (!infoMessage.equals("")) {
            System.out.println(" | "+infoMessage);
        }
    }
    /**
     * Prints the human player's "cursor's" coordinates.
     */
    public void printCursorPosition() {
        /*the reason I'm adding 1 to these attributes, is that I'd think people are more used to the coordinates that start from
        1 instead of 0. People who do coding might get more used to 0 being the first one and the last coordinate being 1 less than
        the actual length, since that's how indexes are in arrays, but for most people I'd think this way here is more easier to understand*/
        System.out.print("Position ["+(curY+1)+","+(curX+1)+"]");
        System.out.print(board[curY][curX]+" ");
    }
    /**
     * Returns the direction where player wants to move, or the task chosen.
     * <P>
     * @return character from char[] possibleInputs
     */
    //similar method with char[] array (possibleInputs) as its parameter might be good to put in Util.Console.java, but it's used only
    //once in this program so it can wait
    public char getDirection() {
        
        boolean validValue=false;
        //'-' is chosen here just because it's not something that can be really pressed. Any char that is not part of char[] possibleInputs would do.
        char dir='-';
        String userInput = "";
        while (!validValue) {
            Scanner inp = new Scanner(System.in);
            
            userInput = inp.nextLine();
            if (userInput.length()>0) {
                userInput=userInput.toLowerCase();
                dir=userInput.charAt(0);
            }
            if (Arrays.containsChar(dir, possibleInputs)) {
                validValue=true;
            } else {
                errorMessage="Invalid input. Instead of {"+Arrays.charArrayToString(possibleInputs)+"}, you typed "+dir;
                refreshView();
            }
        }
        return dir;
    }
    /**
     * Moves the cursor's position to the direction determined by params
     * <P>
     * @param amountY new Y coordinate compared to the old position
     * @param amountX new X coordinate compared to the old position
     */
    public void move(int amountY, int amountX) {
        // we will check if the move is valid, that is if the cursos would stay on board array if the move was completed
        if(curY+amountY >= 0 && curY+amountY < board.length && curX+amountX >= 0 && curX+amountX < board[0].length) {
            curY += amountY;
            curX += amountX;
            errorMessage="";
        } else {
            errorMessage="Oops. You tried to move the cursor out of the board.";
            infoMessage="";
        }
        refreshView();
    }
    /**
     * Calls the move(int,int) method or other task based on the "direction"
     * <P>
     * @param d Direction of the move, or the task performed
     */
    public void moveToDirection(char d) {
        //d is short for direction here
        switch(d) {
            case UP: move(-1,0);
            break;
            case RIGHT: move(0,+1);
            break;
            case DOWN:
            case DOWN_ALTERNATIVE: move(+1,0);
            break;
            case LEFT: move(0,-1);
            break;
            case UPRIGHT: move(-1,+1);
            break;
            case DOWNRIGHT: move(+1,+1);
            break;
            case DOWNLEFT: move(+1,-1);
            break;
            case UPLEFT: move(-1,-1);
            break;
            case SELECT: makeSelection();
            break;
            case EXIT: askAboutExit();
            break;
        }
    }
    /**
     * Default constructor, not intended for use.
     */
    Game() {
        /*does nothing, should not be used, needs parameters*/
    }

    /**
     * Constructor that is used to make the game ready.
     * <P>
     * @param levelLength Size of the board (N * N)
     * @param targetScore How many in a row are needed to win the game
     * @param isPlayerX Does player use the CHAR_X or CHAR_O
     */
    Game(int levelLength, int targetScore, boolean isPlayerX) {
        this.levelLength=levelLength;
        this.targetScore=targetScore;
        this.isPlayerX=isPlayerX;

        ai = new Ai();

        startNewGame();
    }
    /**
     * Creates the instances of Player class
     */
    public void setUpPlayers() {
        if(isPlayerX) {
            human = new Player(CHAR_X);
            computer = new Player(CHAR_O);
        } else {
            human = new Player(CHAR_O);
            computer = new Player(CHAR_X);
        }
    }
    /**
     * Tries to put a player's tile in the position of the cursor.
     */
    public void makeSelection() {
        infoMessage="";
        char currentTile=board[curY][curX];
        //if the currentTile is not empty, it can not be used for selection
        if (currentTile != EMPTY) {
            errorMessage = "Oops. ";
            if (currentTile==human.side()) {
                errorMessage += "You have already occupied this tile!";
            } else {
                errorMessage += "This tile is already occupied by this horrible Player "+currentTile+".";
            }
            refreshView();
        // we will go to this loop if the tile is empty
        } else {
            board[curY][curX]=human.side();

            human.addOccupiedTile();
            emptyTilesLeft--;
            if(human.tilesOccupied() >= targetScore) {
                checkIfThisPlayerWon();
            }
            checkIfDraw();
            endCurrentPlayersTurn();
        }
    }
    /**
     * Checks if a tile put here is bringing a victory for the playe putting it.
     * <P>
     * The method is also used in Ai-classes methods to determine whether there
     * is a potential victory in that given position.
     * <P>
     * @param posY Y-position of the place where the check takes place
     * @param posX X-position of the place where the check takes place
     * @return true/false whether it would bring a victory
     */
    public boolean isThisAwinningMove(int posY, int posX) {
         if (checkVictoryCase(1, posY, posX)) {
            return true;
        } else if (checkVictoryCase(2, posY, posX)) {
            return true;
        } else if (checkVictoryCase(3, posY, posX)) {
            return true;
        } else if (checkVictoryCase(4, posY, posX)) {
            return true;
        } else {
            return false;
        }
    }
    /**
     * Calls new methods if the victory just took place after putting a tile.
     */
    public void checkIfThisPlayerWon() {
        /*Goes around the latest tile that was added and checks if the victory conditions are met.
        Needs to check it for 4 different cases (top-down, left-right, topleft-bottomright, topright-bottomleft)*/

        /*newY and newX will be the starting position for our checks, since this is the location where the newest tile
        was added for this current player*/
        int newY=curY;
        int newX=curX;

        //they will be changed in the case it's actually computers turn and not humans turn
        if (!isItHumansTurn) {
            newY=compY;
            newX=compX;
        }
        if (isThisAwinningMove(newY, newX)) {
            refreshView();
            thisPlayerWon();
        }
    }
    public void gameOver() {
        gamesPlayed++;
        refreshView();
        System.out.println();
        System.out.println("Game Over. "+gameOverMessage);
        roundEnded=true;
    }
    public void thisPlayerWon() {
        if (isItHumansTurn) {
            human.addWin();
            gameOverMessage="You won this one.";
        } else {
            computer.addWin();
            gameOverMessage="Computer won!";
        }
        gameOver();
    }
    /**
     * Used for testing. This method should never be called in a bug-free game.
     * <P>
     * @param array Which boards contents are printed on console
     */
    public void print2dArray(int [][] array) {
        for (int y=0; y<array.length; y++) {
            for (int x=0; x<array[0].length; x++) {
                System.out.print("[");
                System.out.print(Integer.toString(array[y][x]));
                System.out.print("]");
            }
            System.out.println();
        }
    }
    /**
     * Used for testing. This method should never be called in a bug-free game.
     * <P>
     * @param testMessage Message to be shown
     */
    public void test(String testMessage) {
        for (int i=4; i>0; i--) {
            System.out.println(testMessage);
        }
    }
    /**
     * Starts a new game. Is called only once during game. In the constructor.
     */
    public void startNewGame() {
        setUpPlayers();
        while (playerWantsToContinue) {
            setUpBoard();
            while (!roundEnded) {
                startNewTurn();
            }
        playerWantsToContinue=Console.readBooleanYorN(("Do you want to start a new game?"));
        }
        System.out.println("Have a nice day!");
        System.exit(0);
    }
    /**
     * Sets up the board that holds the situation of the game.
     */
    public void setUpBoard() {
        board = new char[levelLength][levelLength];
        emptyTilesLeft=levelLength*levelLength;

        roundEnded=false;
        resetBoard();
        
    }
    /**
     * Starts a new turn for either human or the computer.
     */
    public void startNewTurn() {
        if (isItHumansTurn) {
            refreshView();
            moveToDirection(getDirection());
        } else {
            computerPlaysTheirTurn();
        }
    }
    /**
     * Checks if the tile put in the position of the coordinates brought a win.
     * <P>
     * The method is always called 4 times in a row in isThisAwinningMove()
     * method. Once for each direction, and its opposidte direction at the
     * same time, since there might be tiles in both of the directions that
     * give a winning position in a center tile. How many in a row is needed
     * for win is determined in the variable int targetScore in Game class.
     * <P>
     * @param dir Which of the 4 directions check takes place
     * @param newY Coordinate Y of the center tile of the check
     * @param newX Coordinate X of the center tile of the check
     * @return true if there was target score reached in the checks.
     */
    public boolean checkVictoryCase(int dir, int newY, int newX) {
        /*dir (direction) 1 = top-bottom
                          2 = left-right
                          3 = topleft-bottomright
                          4 = topright-bottomleft
                          */
        //side determines which type of character the method tries to find more of in a row
        char side=board[newY][newX];
        /*posY and posX determine the posiion where the checking takes place. They will keep changing
        depending on the direction towards the checking takes place*/
        int posY=newY;
        int posX=newX;
        /*dirY and dirX are direction the method will first start going towards, at least one of them
        will be turned into 1 or -1, before the checking actually starts*/
        int dirY=0;
        int dirX=0;
        /*method will return this boolean. The only way it will turn into true, is if there are
        number equal to (int targetScore) subsequent tiles occupied by the same (current) player*/
        boolean victory=false;
        /*correctTiles means how many tiles it has found already, it will start from 1 since the
        starting point is always occupied by the current player after just placing the tile there*/
        int correctTiles=1;
        
        /*if the checker doesnt find enough correct tiles from its direction, it will go back to the
        to the starting position and try to find enough missing ones from the total opposite direction.
        The reason why it's important to keep check of it, is so the checker will not keep changing
        directions endlessly.
        
        Note, that the word "turn" here doesn't have anything to do with which players
        turn it is, but instead the turn of the direction as described above.
        */
        int turnsLeft=1;

        if(dir==1) {
            dirY=1;
            dirX=0;
        } else if(dir==2) {
            dirY=0;
            dirX=1;
        } else if(dir==3) {
            dirY=1;
            dirX=1;
        } else {
            dirY=1;
            dirX=-1;
        }
        while(correctTiles<=targetScore && turnsLeft >= 0) {
            posY+=dirY;
            posX+=dirX;
            //we will use try-catch here, since often it would try to check an index out of the board otherwise.
            try {
                if (board[posY][posX]==side) {
                    correctTiles++;
                    if (correctTiles==targetScore) {
                        victory=true;
                    }
                /*this else-loop will be performed, if there wasn't a correct tile in the current direction
                   ok copy of code to catch(Exception e), could be used as a new method but would need
                   all these attributes and parameters for it so I just think this is probably better
                   to leave as it is */
                } else {
                    turnsLeft--;
                    if (turnsLeft>=0) {
                        posY=newY;
                        posX=newX;
                        dirY=0-dirY;
                        dirX=0-dirX;
                    }
                }
            /*it will treat the index out of range the same way as if there was a tile occuped by another player
            there. meaning it will go to starting point and turn back if it hasnt made the turn yet*/
            } catch (Exception e) {
                turnsLeft--;
                if (turnsLeft>=0) {
                    posY=newY;
                    posX=newX;
                    dirY=0-dirY;
                    dirX=0-dirX;
                }
            }
        }
        return victory;
    }
    /**
     * Computer makes their move.
     * <P>
     * ai.MakeMove() method will always return coordinates that have an empty
     * tile, so the move will always be valid. Makes the checks if the game
     * ends after the move is made or if it is the human's turn next.
     */
    public void computerPlaysTheirTurn() {
        
        int [] aiMoveCoordinates = ai.AiMakeMove();

        compY=aiMoveCoordinates[0];
        compX=aiMoveCoordinates[1];

        if (board[compY][compX]==EMPTY) {
            board[compY][compX]=computer.side();
            computer.addOccupiedTile();
            emptyTilesLeft--;
            infoMessage="Computer put their tile on ["+(compY+1)+","+(compX+1)+"]";
            if (computer.tilesOccupied() >= targetScore) {
                checkIfThisPlayerWon();
            }
            checkIfDraw();
            endCurrentPlayersTurn();
        } else {
            //just for testing purposes, this should never happen as stated
            test("ERROR IN CODE. THIS SHOULD HAVE NOT HAPPENED COMPUTER TRIED TO PUT A TILE IN ALREADY OCCUPIED SPACE PROBABLY");
        }
    }
    /**
     * Asks if user wants to exit the game and stops the program if so.
     */
    public void askAboutExit() {
        boolean answerGiven=false;
        boolean wantsToExit=false;
        while (!answerGiven) {
            wantsToExit = Console.readBooleanYorN("Are you sure you want to exit the game?");
            if (wantsToExit) {
                System.exit(0);
                answerGiven=true;
            } else {
                answerGiven=true;
            }
        }
        
    }
    /**
     * Fills the char[][] board with EMPTY characters (empty tiles) etc.
     * <P>
     * Is called every time if the player wants to continue after a game has
     * ended. Determines the starter of the game, resets some info Strings,
     * and makes the players cursor position to start around center.
     */
    public void resetBoard() {
        for (int y=0; y<board.length; y++) {
            for (int x=0; x<board[0].length; x++) {
                board[y][x]=EMPTY;
            }
        }
        
        curY=levelLength/2;
        curX=levelLength/2;

        //X starts the first game so it's simple to figure out the starter by this. O will start every other game (on evens).
        if (gamesPlayed % 2 == 0) {
            isItHumansTurn=isPlayerX;
        } else {
            isItHumansTurn=!isPlayerX;
        }
        infoMessage="First one to reach "+targetScore+" in a row will win. Good luck!";
        errorMessage="";
    }
    /**
     * Inner class, that has purpose of determining the computer's next move.
     */
    class Ai {
        /**
         * "Legacy" variable, not actually used in this version.
         * <P>
         * There might be some comments still referring to it so i'm still
         * leaving it here. Basically its function was to determine the
         * probablitiy (100 being totally sure as in 100percent) of Ai making
         * the best possible move it can. Lower difficulty ratings would have
         * had AiRating lower, where it would make some other move, but I
         * abandoned the idea haflway cosing the AiMakeMove() method because
         * the task would have been surprisigly difficult to maintain
         * throughout the different directions Ai could take their move
         * towards.
         */
        int AiRating=100;

        /**
         * Holds information about human's near victories.
         * <P>
         * Identical to the size of char[][] board. Empty tiles that
         * have victory ahead if 2 more tiles are put, will have a value of 1.
         * Or more, depending the amount of directions that there will be a
         * close victory like that. All the other y,x coordinates will hold the
         * value of 0.
         */
        int [][] humanCloseVictoryBoard;
        /**
         * Same as humanCloseVictoryBoard but for the computer.
         */
        int [][] computerCloseVictoryBoard;
        /**
         * Holds information about what tiles Ai thinks are good to occupy.
         * <P>
         * Doesn't take into account strategic moves but checks if there is
         * good spots for enemy's next move. Can be often used mixed with
         * other boards initiated here, to determine the definitive best move.
         * The higher the score in the position, the better move Ai thinks it
         * is. NOTE: As of version 2019-1210 this information is great on 4
         * and 3 target scored games, but makes computers default moves a bit
         * worse on higher target scored games. 
         */
        int [][] priorityCaseBoard;

        /**
         * Gathers information different to other boards initiated here.
         * <P>
         * This board was originally created to support the AiRating's full
         * functionality, where computer might choose some of the next best
         * moves instead of always the best one. That is why it will hold
         * the best move that can be made with the value 1, next best move
         * as 2 and so on. There might not be many numbers on it though,
         * since now computer just tries to find that 1 definitive move.
         * 0s in this board would be something that computer don't
         * consider good moves to pursue at all, except when there is not
         * even one positive number on board.
         */
        int [][] hiPriorityBoard = new int[levelLength][levelLength];
        /**
         * Usually the final board that determines the move.
         * <P>
         * Holds the information from the mixed results when applying
         * other boards contents together, based on the priority order in
         * AiMakeMove() -method.
         */
        int [][] modifiedPriorityBoard;
        /**
         * Whether computer found one move that is better than others.
         * <P>
         * If not, then it will be random generated among the positions that
         * ai can't decide if one is better than the other.
         */
        boolean modifiedPriorityBoardSuccess = false;
        /**
         * The default and the only constructor, since AiRating was abandoned.
         */
        public Ai() {
        }

        /* IMPORTANT! 
        
        the method AiMakeMove() was originally planned to have more functionality to have int priorityTarget given as a parameter,
        when I thought I will implent Ai-Rating (difficulty rating you can choose in the menu before game), and
        the method could then return some other moves coordinates than the best possible ones. Because of that
        there is still some code left from this. PriorityCounter, PriorityCounterBeforeNextCheck are these kinds of variables
        that are not very wise anymore but they will hold this lump of chaos together.

        IMPORTANT

        Some variables originated from this idea and it could be made simpler by losing those, but I was just happy that I actually got it to work
        so I don't want to touch it.

        What is important that this method will check these cases in this order, and make a move from the first of the priorities they can reach:

        - FIRST PRIORITY MOVE (INSTANT WIN FOR COMPUTER):
          instant win for the computer. They will put a tile in this winning position.

        - SECOND PRIORITY MOVE (BLOCKING HUMANS NEXT TURNS WIN):
          there is no possibility for computer win right now, but there is one for human, so computer will put his own tile
          in this place instead. If there is more than one of these places, then he can (and will) stop only one of them. Again, if multiple places like this,
          Ai will choose a tile that will help him win the game easier on his next turns, or if there are multiple but none of them will help computer have a
          future win more than the other, then the tile from those few will be chosen at random.

        - THIRD PRIORITY MOVE: (OFFENSIVE MOVE, 2 TILES FROM COMPUTER VICTORY)
          No instant wins upcoming for either player, but if there is a move that will help computer win AFTER putting one other tile, then computer will
          push to do this. If there is a place that has more that holds more than 1 upcoming victory, they will put a tile in there and should win soon after
          that. If not, Computer will check if they can stop humans "close victory case" like this with the same
          move and proceeds to do a move like that. If they can stop more than 1 directions of humans victory, they will do the one with the biggest
          possibilities. If there is not a humans future case to be blocked but there are multiple "close victory cases" for computer, they will choose the
          one that computer thinks is the best according to "Priority Case Board". If they think none of them are better than the other, they will randomize
          one.

        - FOURTH PRIORITY MOVE: (DEFENSIVE MOVE, BLOCKING HUMAN'S VICTORY THAT IS 2 TILES AWAY)
          No instant win moves for either player, and not even 2 tiles away victory for computer, but there is one or more cases like that for human.
          Again, will try to make a move that will block as many human close victories as possible, if there is more than 1 cases in the tiles. Other
          than that, tries to put the defensive move in as good place as possible as determined in the "priority case board". If they come back
          equal, it will be randomized.

          FIFTH PRIORITY MOVE: (DEFAULT MOVE)
          None of the cases listed above apply, so will try to build a victory from some central position, as found in priority case board. It will
          most likely be randomized among many potential tiles, depending on the size and the progression on the board. If there are some other computers
          tiles already on the board, that have potential to lead to victory, victory case board should find tiles to build up to that.

          NOTE: changes in the priority board can tweak this default move to be different if needed. Changes in it will also change some of the other
          moves if they have multiple potential moves.

        
        
        the method void AiMakeMove() will put possible moves in order of how wise they would be to perform.*/
        
        /**
         * Determines the computers next move's coordinates.
         * <P>
         * There is lot of information in the java codes comments. Never
         * returns coordinates that do not hold an empty tile.
         * <P>
         * @return coordinates as int[]{y,x} array that computer's next move
         * is gonna take place in.
         */
        public int[] AiMakeMove() {

            /*later in the method these 3 arrays will be initalized if needed.
            */
            /*humanCloseVictoryBoard will hold information about tiles that would give human a victory a round after if they would put a tile
            in that position. If the index position is 0, this tile is not suitable for that. If it's 1, it will have possibility to do that for 1
            direction, if 2, then in 2 directions etc
            */
            humanCloseVictoryBoard = new int[levelLength][levelLength];
            //computerCloseVictoryBoard will hold the same information about the computers possible victories that are close to obtain
            computerCloseVictoryBoard = new int[levelLength][levelLength];
            /*this will try to mimic the best possible places on the board but it will notice a winning or a good defending/offending move on its own.
            That's why It can be used in addition to closeVictoryBoards. This will hold much bigger scores, but that doesn't mean the computer is going
            to make a move that has a highest score, if it finds out there are victories to be stopped, proceeded or instant victories possible*/
            priorityCaseBoard = new int[levelLength][levelLength];

            modifiedPriorityBoardSuccess = false;

            /* int[][] hiPriorityBoard has similar size that the actual game board (char[][] board), but instead of
            occupied and empty tiles, it will hold information about the best possible moves AI could make, where
            1 is the best, and each number after that (2, 3, 4 etc) is the next best possible move. priorityCounter
            will hold information about that.

            IMPORTANT
            It will not actually do this for all possible moves (see the Important comment above this method)
            
            */
            hiPriorityBoard = new int[levelLength][levelLength];
            // modifiedPriorityBoard will hold info about hiPriorityBoard and CloseVictoryBoards and/or PriorityCaseBoards combined, if needed
            modifiedPriorityBoard = new int[levelLength][levelLength];

            //IMPORTANT These 2 variables will not do what theiy're supposed to for whole method, but they are needed for the top parts
            int priorityCounter=0;
            int priorityCounterBeforeNextCheck = 0;
            /*method is designed to stop searching for best possible tiles, after priorityCounter hits a score equal to the
            priorityTarget given as parameter.
            That means that if there was 4 empty tiles (emptyTiles==4) in the game when it's computer's turn and this method
            is executed, numbers 1, 2, 3 and 4 will be added to the indexes found by method AiMakeMove()
            */

            /*first priority: Will check if there are empty tiles to choose that would give computer the win immediately.
            But there is no need to do the check if there are not enough tiles placed for it to be even possible.
            */
            if (computer.tilesOccupied() >= (targetScore-1) ) {
                for (int y=0; y<board.length; y++) {
                    for (int x=0; x<board[0].length; x++) {
                        /*for each tile that is empty, we will (temporiraly) place computers tile and then we will
                        check if they would win with a move like that using a part of code from checkIfThisPlayerWon() method,
                        which I made a method of its own called boolean isThisAWinningMove(...).
                        */
                        if (board[y][x]==EMPTY) {
                            board[y][x]=computer.side();
                            if (isThisAwinningMove(y, x)) {
                                board[y][x]=EMPTY;
                                int [] moveToMake = new int[]{y,x};
                                return moveToMake;
                            }
                        /*it is important to change it back to EMPTY right afterwards, since this move hasn't actually
                        been made just yet*/
                        board[y][x]=EMPTY;

                        }
                    }
                }
            }

            //if we found the move AI is going to make already, no need to check the rest of the cases.
            
            //we will put 1 in it since AIRating functionality was not implemented in the final version
            int coordinates[]=(coordinatesOfPriorityNo(1));
            if (coordinates[0] != -1 && coordinates[1] != -1) {
                return coordinates;
            }

            /*SECOND PRIORITY: (DEFENSIVE MOVE): Will check if there is a move, that would allow human player to win the game on their next turn, if
            computer doesn't put a tile in that exact place now to prevent that.

            */
            if ( human.tilesOccupied() >= (targetScore-1)) {
                for (int y=0; y<board.length; y++) {
                    for (int x=0; x<board[0].length; x++) {
                        /*for each tile that is empty, we will (temporiraly) place computers tile and then we will
                        check if they would win with a move like that using a part of code from checkIfThisPlayerWon() method,
                        which I made a method of its own called boolean isThisAWinningMove(...).
                        */
                        if (board[y][x]==EMPTY) {
                            board[y][x]=human.side();
                            if (isThisAwinningMove(y, x)) {
                                hiPriorityBoard[y][x]=++priorityCounter;
                            }
                        /*it is important to change it back to EMPTY right afterwards, since this move hasn't actually
                        been made*/
                        board[y][x]=EMPTY;

                        }
                    }
                }

            }
            //we will check again if ai has found a move he's going to make, but in this case we need to look further which one
            //is actually the best move, and not just the first one that the for-loops found
            coordinates=(coordinatesOfPriorityNo(1));
            if (coordinates[0] != -1 && coordinates[1] != -1) {
                //in this case we don't need to check further, there is only one option here
                if (priorityCounter == 1) {
                    return coordinates;
                } else {

                    /*This else-loop will be performed if there are more than 1 possible move to do from "second priority" case.
                    It will try to determine which one is the best. if best is not found, it will randomize one of them.*/

                    //-1,-1 are just for testing purposes, and it should never really return those if working properly
                    int [] moveToMake=new int[]{-1, -1};

                    buildCloseBoards();
                    buildPriorityCaseBoard();

                    if (areThereNonZeroes(computerCloseVictoryBoard)) {
                        modifiedPriorityBoard = applyAnotherBoard(hiPriorityBoard, computerCloseVictoryBoard);
                    }

                    //we will continue to do more checks if the right move wasnt found yet
                    if (!modifiedPriorityBoardSuccess) {
                        if (areThereNonZeroes(humanCloseVictoryBoard)) {
                            modifiedPriorityBoard = applyAnotherBoard(modifiedPriorityBoard, humanCloseVictoryBoard);
                        }
                    }
                    if (!modifiedPriorityBoardSuccess) {
                        modifiedPriorityBoard = applyAnotherBoard(modifiedPriorityBoard, priorityCaseBoard);
                    }
                    if (!modifiedPriorityBoardSuccess) {
                        //changed to hiPriorityBoard so it won't crash (rarely) for some reason
                        /* IMPORTANT THIS ISN'T IDEAL THAT I HAD TO DO THIS, since in a case like this it will ignore
                        couple of those last used boards, so it might end up putting a tile in almost-the-best-but-not-quite
                        the best move that AI would otherwise find, but it should be quite rare case. */
                        moveToMake = getCoordinatesOfRandomMove(hiPriorityBoard);
                    }
                    if (modifiedPriorityBoardSuccess) {
                        System.out.println();
                        print2dArray(modifiedPriorityBoard);
                        System.out.println();
                        moveToMake=getCoordinates(modifiedPriorityBoard);
                    }
                    return moveToMake;
                }
            }
            /* THIRD PRIORITY (OFFENSIVE MOVE): if there is something in the computers close victory board, this is something that he wants to do next,
            because
                - there have not been found winning move for either player, then after this move, human is forced to be in a situation where
                computer will have a guaranteed win unless they can be stopped by placing a tile in a winning position.
            */
            buildCloseBoards();
            buildPriorityCaseBoard();
            if (areThereNonZeroes(computerCloseVictoryBoard)) {
                int [] moveToMake=new int[]{-1, -1};
                //hiPriorityBoard is empty right now, but we will fill it to have its possible moves from computerCloseVictoryBoard
                hiPriorityBoard=computerCloseVictoryBoard;
                /*working as intended even though may look ridicolous. This time we know:
                    - there is the next move to make found, but only way we can know which one it is, if there was only one value
                    that was higher than the others. (on a sidenote: 
                    if there was at least one that was higher than 1, computer is going to win this game
                    in their next turn)
                */
                modifiedPriorityBoard = applyAnotherBoard(hiPriorityBoard, computerCloseVictoryBoard);
                if (!modifiedPriorityBoardSuccess) {
                    /* in this case we don't know yet which is the best move to make, so we will check if one of the moves
                    would block humans possible future victories best */
                    
                    if (areThereNonZeroes(humanCloseVictoryBoard)) {
                        modifiedPriorityBoard = applyAnotherBoard(modifiedPriorityBoard, humanCloseVictoryBoard);
                    }
                }
                if (!modifiedPriorityBoardSuccess) {
                    modifiedPriorityBoard = applyAnotherBoard(modifiedPriorityBoard, priorityCaseBoard);
                }
                if (!modifiedPriorityBoardSuccess) {
                    moveToMake = getCoordinatesOfRandomMove(modifiedPriorityBoard);
                }
                if (modifiedPriorityBoardSuccess) {
                    moveToMake=getCoordinates(modifiedPriorityBoard);
                }
                return moveToMake;
            }

            /* FOURTH-PRIORITY (DEFENSIVE MOVE AGAINST UPCOMING HUMAN VICTORY)

            In this case
            - there was no victory to be made or stopped for either player
            - Computer didnt have a move where he could force human in the defensive move or lose the round after
            - but there is still a move human could pursue to win the game a turn after, so it's wise for computer to stop
            it before it happens, since computer can't push offensively to win before that happens, as in third-priority case above

            */

            if (areThereNonZeroes(humanCloseVictoryBoard)) {

                int [] moveToMake=new int[]{-1, -1};

                hiPriorityBoard=humanCloseVictoryBoard;

                modifiedPriorityBoard = applyAnotherBoard(hiPriorityBoard, humanCloseVictoryBoard);

                if (!modifiedPriorityBoardSuccess) {
                    modifiedPriorityBoard = applyAnotherBoard(modifiedPriorityBoard, priorityCaseBoard);
                }
                if (!modifiedPriorityBoardSuccess) {
                    moveToMake = getCoordinatesOfRandomMove(modifiedPriorityBoard);
                }
        
                if (modifiedPriorityBoardSuccess) {
                    moveToMake=getCoordinates(modifiedPriorityBoard);
                }

                return moveToMake;
            }

            /*FIFTH-PRIORITY: default move to make: there was no victory or even close victory possible for either player, so time to find the
            move as good as possible otherwise */

            int [] moveToMake=new int[]{-1, -1};

            hiPriorityBoard=priorityCaseBoard;
            modifiedPriorityBoard = applyAnotherBoard(hiPriorityBoard, priorityCaseBoard);

            if (!modifiedPriorityBoardSuccess) {
                moveToMake = getCoordinatesOfRandomMove(modifiedPriorityBoard);
            }

            if (modifiedPriorityBoardSuccess) {
                moveToMake=getCoordinates(modifiedPriorityBoard);
            }

            return moveToMake;
            
        }

        /**
         * Mixes an existing "Ai"-board, like HiPriorityBoard with another one.
         * <P>
         * Applies changes to first board (boardToModify) based on the values
         * in the second bord (cvBoard). If there are no mutual coordinates in
         * both boards, the boardToModify remains unchanged. If there are
         * multiple values in the second player that have higher rating than
         * all the other mutual coordinates, only they will remain in the
         * board that is returned, and all other values will be 0. If there
         * is only one mutual tile left, it will be the move the Ai is going
         * to make.
         * <P>
         * @param boardToModify board that determines the possible coordinates
         * @param cvBoard the board that is applied on to boardToModify
         * @return board that has the changes made to boardToModify.
         */
        public int[][] applyAnotherBoard (int[][] boardToModify, int[][] cvBoard) {

            int highestVictoryScore=0;
            int noOfHighestVictoryScores=0;
    
            int[][] newBoard = new int[levelLength][levelLength];
    
            for (int y=0; y<board.length; y++) {
                for (int x = 0; x<board[0].length; x++) {
                    if ((boardToModify[y][x] > 0) && (cvBoard[y][x] > 0)) {
                        newBoard[y][x]=cvBoard[y][x];
                        if (cvBoard[y][x] > highestVictoryScore) {
                            highestVictoryScore=cvBoard[y][x];
                            noOfHighestVictoryScores=1;
                        } else if (cvBoard[y][x] == highestVictoryScore) {
                            noOfHighestVictoryScores++;
                        } else {
                            newBoard[y][x]=0;
                        }
                    }
                }
            }
            // multiple possible cases remain, but lower scores (worse moves) might be filtered out
            if (noOfHighestVictoryScores > 1) {
                for (int y=0; y < board.length; y++) {
                    for (int x = 0; x < board[0].length; x++) {
                        if (newBoard[y][x] != highestVictoryScore) {
                            newBoard[y][x] = 0;
                        }
                    }
                }
            } else if (noOfHighestVictoryScores == 1) {
                // here they found the move they are going to make
                for (int y=0; y < board.length; y++) {
                    for (int x = 0; x < board[0].length; x++) {
                        if (newBoard[y][x] != highestVictoryScore) {
                            newBoard[y][x] = 0;
                        }
                    }
                }
                modifiedPriorityBoardSuccess=true;
            } else {
                //didnt find any matches so the method didnt really change the board and find out other than this fact
                newBoard = boardToModify;
            }
            return newBoard;
        }
        /**
         * Fills up humanCloseVictoryBoard and computerCloseVictoryBoard.
         * <P>
         * More info on these variables that are initiated in Ai class.
         */
        public void buildCloseBoards() {

            for (int y=0; y<board.length; y++) {
                for (int x=0; x<board[0].length; x++) {
                    if (board[y][x] == EMPTY) {
                        computerCloseVictoryBoard[y][x]=toHowManyDirectionsIsThisMoveCloseToAwinningMove(computer.side(), y, x);
                        humanCloseVictoryBoard[y][x]=toHowManyDirectionsIsThisMoveCloseToAwinningMove(human.side(), y, x);
                    }
                }
            }
        }
        /**
         * Fills up priorityCaseBoard.
         * <P>
         * More info on Ai Class where the array is initiated.
         */
        public void buildPriorityCaseBoard() {
            for (int y=0; y<board.length; y++) {
                for (int x=0; x<board[0].length; x++) {
                    if (board[y][x] == EMPTY) {
                        priorityCaseBoard[y][x]=priorityCaseScore(y, x);
                    }
                }
            }
        }
        /**
         * Returns the coordiantes of the only possible move Ai thinks is best.
         * <P>
         * Method should only take a board that has only one coordinate left on
         * it. If there are multiple, getCoordiantesOfRandomMove(int [][])
         * should be used instead.
         * <P>
         * @param coBoard ModifiedPriorityBoard with only one value other than
         * 0.
         * @return int[]{y,x} coordinates of the next move
         */
        public int[] getCoordinates(int [][] coBoard) {
            //this method should only be used for a board that has only 1 index that is non-zero
            //just to make sure this method isnt doing any mistakes, we are doing counter
            int counter=0;

            //we will reset the coordinates with impossible values just for testing purposes
            int[] coordinates = new int[] {-1,-1};

            for (int y=0; y < board.length; y++) {
                for (int x = 0; x < board[0].length; x++) {
                    if (coBoard[y][x] > 0) {
                        counter++;
                        coordinates[0]=y;
                        coordinates[1]=x;
                        //again just for testing purposes, this should never happen
                        if (counter > 1) {
                            System.out.println("this happened there was more than 1 actually ERROR, see next board");
                            print2dArray(coBoard);
                            System.out.println();
                            coordinates[0]=-1;
                            coordinates[1]=-1;
                        }
                    }
                }
            }
            return coordinates;
        }
        /**
         * Returns one random moves coordinates based on options in param board
         * <P>
         * This method should used on a board that has multiple indexes that
         * are other than 0. It has equal chance to choose any one of those
         * options.
         * <P>
         * @param raBoard modifiedPriorityBoard or hiPriorityBoard
         * @return int[]{y,x} coordinates of next move
         */
        public int[] getCoordinatesOfRandomMove(int [][] raBoard) {
            //we will reset the coordinates with impossible values just for testing purposes
            int[] coordinates = new int[] {-1,-1};
            //first we will go through the board to know how many possibilities we have, since not using ArrayList here
            int possibilities=0;
            for (int y=0; y<board.length; y++) {
                for (int x = 0; x<board[0].length; x++) {
                    if (raBoard[y][x] > 0) {
                        possibilities++;
                    }
                }
            }
            int random = Math.getRandom(1,possibilities);
            possibilities=0;
            for (int y=0; y<board.length; y++) {
                for (int x = 0; x<board[0].length; x++) {
                    if (raBoard[y][x] > 0) {
                        possibilities++;
                        if (random==possibilities) {
                            coordinates[0]=y;
                            coordinates[1]=x;
                            return coordinates;
                        }
                    }
                }
            }
            return coordinates;
        }
        /**
         * Returns an integer that is added in the coordinates in priorityBoard
         * <P>
         * All 4 directions are actually always added to the score.
         * NOTE: this method's scores could be edited to abtain better
         * Ai for the program. Maybe it could be added to have different types
         * of calculating the score, since right now, as in version 2019-1012
         * it is best used in games that have 4 or 3 tiles as their target
         * score. This is because it doesn't manage to favor building a high
         * number of tiles in a row as easily as putting them all chunked up
         * in one center place next to the other. Only then closeVictoryBoard
         * check will force Ai to start making more offensive moves.
         * <P>
         * @param newY Y-coordinates of the tile whose score is calculated
         * @param newX X-coordinates of the tile whose score is calculated
         * @return score based on how good Ai thinks the move is based on one
         * of the four directions, and their opposite directions.
         */
        public int priorityCaseScore (int newY, int newX) {
            int score=0;
            for (int i = 1; i <= 4; i++) {
                score += priorityCaseScoreDirection(i, newY, newX);
            }
            return score;
        }

        /* IMPORTANT the following method can have its score__ variables tweaked to easily make the AI different

        /*the method will return integer. The higher the integer, the better it would be for computer to
        occupy the tile in these coordinates given as parameters. The first parameter determines which one
        of the 4 directions this score is based on. Then again, the method will always be called for all 4
        directions in a row, but it's better to keep them still separeted, and added to the score after one another.
        
        NOTE: we will never have to add any score for center tile, because that tile is determined here, it is
        always empty. this method should never be used on a tile that is not empty.
        */
        public int priorityCaseScoreDirection(int dir, int newY, int newX) {
    
            //to be returned
            int score=0;
    
            /*this counter goes down to 1 when the first edge or enemy tile is reached, and then again when it does
            the same in the opposite direction */
            int endOfRoutesLeft=2;
            
            /*the method could be tweaked to help player as well, but we don't plan to show this kind of help
            to player to ruin the fun, so we will just check it always for computer's side of things.

            Post-Note: Actually best possible score would be the one that in case of equals, it would also be among humans best scores, but
            that would make things even more complex so I won't do it at least yet
            */
            char side=computer.side();
            /*ok we have to be careful there, enemySide means human in this case since we're writing this method
            to try to help ai make good move*/
            char enemySide=human.side();
            
            int scoreForOwnTile = 13;
            int scoreForEmptyTile = 2;
            int scoreForEnemyTile = 1;
            int scoreForEdge = 0;
    
            /*a little bonus but it shouldnt be too big so AI won't end up putting the tile always next to each other
            even if there is no future (possiblity to reach target score) in that place in many (if any) directions*/
            int scoreForOwnNearbyTile = 1;
            
            /*should be major bonus but it can't be too big so Ai won't think central position in all directions is always better than
            continuing a case that has some of its directions blocked but lots of tiles in some direction
            */
            int scoreForTargetScore = 7;
            int scoreForExceedingTargetScore = 3;
    
            int counterForTargetScore = 0;
            //also if the targetScore is lower there is very little point in putting a tile there so lots of penalty
    
            //this must be reseted after the turn
            int distanceFromCenter = 1;

            int posY=newY;
            int posX=newX;
    
            int dirY=0;
            int dirX=0;
    
            if(dir==1) {
                dirY=1;
                dirX=0;
            } else if(dir==2) {
                dirY=0;
                dirX=1;
            } else if(dir==3) {
                dirY=1;
                dirX=1;
            } else {
                dirY=1;
                dirX=-1;
            }
            while(endOfRoutesLeft > 0  && distanceFromCenter <= targetScore) {
                posY+=dirY;
                posX+=dirX;
    
                /*we will use try-catch here, since it's important to know how close we are to the edge
                (index out of range that is) in case edge is closer in that direction than a tile
                occupied by the human.*/
                try {
                    if (board[posY][posX]==side) {
                        //coutneFortargetScore goes up by 1 point, distance must be added in the end so it won't end the loop yet
                        counterForTargetScore++;
                        //always happening
                        score += scoreForOwnTile;
                        /*if counter reaches target score, the move is going to be something that has potential
                        to lead to a victory. Also each tile after that is a bonus too since it gives extra potential */
                        if(counterForTargetScore == targetScore) {
                            score += scoreForTargetScore;
                        } else if (counterForTargetScore > targetScore) {
                            score += scoreForExceedingTargetScore;
                        }
                        distanceFromCenter++;
                        //special bonus case
                        if(distanceFromCenter==1) {
                            score += scoreForOwnNearbyTile;
                        } else if (distanceFromCenter == targetScore) {
                            endOfRoutesLeft--;
                            if (endOfRoutesLeft > 0) {
                                distanceFromCenter=1;
                                posY=newY;
                                posX=newX;
                                dirY=0-dirY;
                                dirX=0-dirX;
                            }
                        }
                } else if (board[posY][posX]==EMPTY) {
                    counterForTargetScore++;
                    score += scoreForEmptyTile;
                    /*if counter reaches target score, the move is going to be something that has potential
                        to lead to a victory. Also each tile after that is a bonus too since it gives extra potential */
                    if(counterForTargetScore == targetScore) {
                        score += scoreForTargetScore;
                    } else if (counterForTargetScore > targetScore) {
                        score += scoreForExceedingTargetScore;
                    }
                    distanceFromCenter++;
                    if (distanceFromCenter == targetScore) {
                        endOfRoutesLeft--;
                        if (endOfRoutesLeft > 0) {
                            distanceFromCenter=1;
                            posY=newY;
                            posX=newX;
                            dirY=0-dirY;
                            dirX=0-dirX;
                        }
                    }
                //this is the case of opposingSides tile
                } else {
                    score += scoreForEnemyTile;
                    endOfRoutesLeft--;
                    if (endOfRoutesLeft > 0) {
                        distanceFromCenter=1;
                        posY=newY;
                        posX=newX;
                        dirY=0-dirY;
                        dirX=0-dirX;
                    }
                }
                //this is the case of edge of the board
                } catch (Exception e) {
                    score += scoreForEdge;
                    endOfRoutesLeft--;
                    if (endOfRoutesLeft > 0) {
                        distanceFromCenter=1;
                        posY=newY;
                        posX=newX;
                        dirY=0-dirY;
                        dirX=0-dirX;
                    }
                }
            }
            return score;
        }
        /**
         * Checks if 2d int array has any other values than zeroes in it.
         * <P>
         * Will go through the array and return true in case there is a number
         * other than the default 0 in any of its indexes. It is used to check
         * if there is need to take humanVictoryBoard or ComputerVictoryBoard
         * into account when determining best possible ai-moves.
         * <P>
         * @param array one "victoryBoard" will be given as a parameter
         * @return true, if there are any values other than 0 in it.
         */
        public boolean areThereNonZeroes (int[][] array) {

            for (int y=0; y<array.length; y++) {
                for (int x=0; x<array[0].length; x++) {
                    if (array[y][x] != 0) {
                        return true;
                    }
                }
            }
            return false;
        }
        /**
         * Uses checkCloseVictoryCase() in all possible directions to get score
         * <P>
         * Will calculate a score, that will be put into priorityBoards index.
         * All coordinates in that board, which are not empty in char[][] board
         * will be given a score. The bigger the score, the better move Ai
         * thinks it would be for this player.
         * <P>
         * @param ownSide Player's tile, whose priorities are being determined.
         * @param posY Coordinate-y of the position to be scored
         * @param posX Coordinate-x of the position to be scored
         * @return score that is total of all directions in the method
         * checkCloseVictoryCase(). 
         */
        public int toHowManyDirectionsIsThisMoveCloseToAwinningMove (char ownSide, int posY, int posX) {
            int howManyDirections = 0;
            /*we will put a tile of this player (given as parameter char ownSide), whose winning chances we are trying to check,
            in the position given as parameters. Later in the method it will be changed back to empty, since this is not a real
            move. Instead this is a check that will try to see IF this player put a tile in this place, could he win the game
            the following round. The counter goes up for each direction that it is true. Naturally, if none of the directions could
            give the win the following round, the counter will be returned as 0. */
            board[posY][posX]=ownSide;
    
            for (int i=1; i<=4; i++) {
                howManyDirections += checkCloseToVictoryCase(i, posY, posX);
            }
            //must be changed back to EMPTY because the move is not actually made
            board[posY][posX]=EMPTY;
            return howManyDirections;
        }
        /**
         * Checks in how many directions there is a close victory case.(2-tile)
         * <P>
         * "Close victory case" means a case, where there is a victory not yet
         * achieved, and can't be achieved on the next move, but CAN be
         * achieved a move after that if not stopped by the other player.
         * This method will be always called in 4 directions after one another.
         * <P>
         * @param dir Direction where the check starts to take place. After
         * that it will continue to check it in the opposite direction.
         * @param newY CoordinateY of the tile that is checked
         * @param newX CoordinateX of the tile that is checked
         * @return true if there is a (targetScore-2) tiles in a row.
         */
        public int checkCloseToVictoryCase (int dir, int newY, int newX) {
            /*direction numbers (1-4) also include their opposite direction, so that's why there can be 0, 1 or 2 victory
            cases in each direction */
            int victoryCasesToThisDirection=0;
            char side=board[newY][newX];
            
            int tileCounterToThisDirection=1;
            
            /*if the checker doesnt find enough correct tiles from its direction, it will go back to the
            to the starting position and try to find enough missing ones from the total opposite direction.
            The reason why it's important to keep check of it, is so the checker will not keep changing
            directions endlessly.
            
            Note, that the word "turn" here doesn't have anything to do with which players
            turn it is, but instead the turn of the direction as described above.
            */
            int turnsLeft=1;
    
            /*newY and newX determine the "center" where the directions are determined from, and posY and posX are positions
            where we go from here, checking if there was another tile putted on an empty tile, would it be a winning move*/
            
            int posY=newY;
            int posX=newX;
    
            int dirY=0;
            int dirX=0;
    
            if(dir==1) {
                dirY=1;
                dirX=0;
            } else if(dir==2) {
                dirY=0;
                dirX=1;
            } else if(dir==3) {
                dirY=1;
                dirX=1;
            } else {
                dirY=1;
                dirX=-1;
            }
    
            while(tileCounterToThisDirection <= targetScore && turnsLeft >= 0) {
                posY+=dirY;
                posX+=dirX;
                //we will use try-catch here, since often it would try to check an index out of the board otherwise.
                try {
                    if (board[posY][posX]==side) {
                        tileCounterToThisDirection++;
                        //this actually should never even happen, since win should have been found before
                        if(tileCounterToThisDirection==targetScore+1) {
                            test("ERROR IN CODE, VICTORY CASE SHOULD HAVE BEEN FOUND BEFORE");
                        }
                    } else if (board[posY][posX]==EMPTY) {
                        //we will temporiraly put a tile in here to help check if it's a winning move
                        board[posY][posX]=side;
                        //we will check the tiles victory case ONLY for the same direction(and the opposite)
                        if (checkVictoryCase(dir, posY, posX)) {
                            victoryCasesToThisDirection++;
                        }
                        board[posY][posX]=EMPTY;
    
                        turnsLeft--;
                        if (turnsLeft>=0) {
                            posY=newY;
                            posX=newX;
                            tileCounterToThisDirection=1;
                            dirY=0-dirY;
                            dirX=0-dirX;
                        }
                    /*this last else case is for the case there is an opponents tile before there is empty tile, so
                    there is no need to check for a winning case, there can't be one, but the opposite direction
                    might still have to be checked*/
                    } else {
                        turnsLeft--;
                        if (turnsLeft>=0) {
                            posY=newY;
                            posX=newX;
                            tileCounterToThisDirection=1;
                            dirY=0-dirY;
                            dirX=0-dirX;
                        }
                    }
                /*it will treat the index out of range the same way as if there was a tile occuped by another player
                there. meaning it will turn back if it hasnt made the turn yet*/
                } catch (Exception e) {
                    turnsLeft--;
                    if (turnsLeft>=0) {
                        posY=newY;
                        posX=newX;
                        tileCounterToThisDirection=1;
                        dirY=0-dirY;
                        dirX=0-dirX;
                    }
                }
            }
            return victoryCasesToThisDirection;
        }
        /**
         * Originally made to give possiblity to obtain other coordinate than
         * the best possible move, but since AiRating idea was abandoned, this
         * remained as a version, where it is used only to get the indexValue 1
         * from a board, if available. If there is no 1 in the board, it will
         * return -1,-1, which can not be used and should be caught in later
         * checks.
         * <P>
         * @param indexValue should always be 1 at the programs current state.
         * @return coordinates int[]{y,x} which will be ai:s next move.
         */
        public int[] coordinatesOfPriorityNo (int indexValue) {
            int [] coordinates = new int[]{-1,-1};
            for (int y = 0; y<hiPriorityBoard.length; y++) {
                for (int x = 0; x<hiPriorityBoard[0].length; x++) {
                    if (hiPriorityBoard[y][x]==indexValue) {
                        coordinates[0]=y;
                        coordinates[1]=x;
                    }
                }
            }
            return coordinates;
        }
    }
}

