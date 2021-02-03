package src.fi.tuni.tamk.tiko.paajarvilauri;

/**
 * Contains the main-method. This class is used to run TicTacToe program.
 * <P>
 * @author Lauri Paajarvi -lauri.paajarvi@tuni.fi-
 * @version 2019.1210
 * @since 1.8
 */

public class TicTacToe {

    /**
     * Main-method. The game will start here.
     */
    public static void main (String[] args) {
        Menu menu = new Menu();
        Game game = new Game(menu.levelLength, menu.targetScore, menu.isPlayerX);
    }
}
