package src.fi.tuni.tamk.tiko.paajarvilauri;

/**
 * Contains information about one players used character, tiles and wins/draws.
 * 
 * <p>There will be 2 instances of Player. One for human and one for computer.
 * 
 * 
 * <P>
 * @author Lauri Paajarvi -lauri.paajarvi@tuni.fi-
 * @version 2019.1210
 * @since 1.8
 */

class Player {
    //side means if he's playing with X or O;

    private char side;
    private int tilesOccupied;
    private int wins;
    private int draws;

    public Player(char side) {
        this.side=side;
        tilesOccupied=0;
        wins=0;
        draws=0;
    }
    public Player() {
        /* Default constructor, this should not be used*/
    }
    public int wins() {
        return wins;
    }
    public void addWin() {
        wins++;
    }
    public int draws() {
        return draws;
    }
    public void addDraw() {
        draws++;
    }
    public char side() {
        return side;
    }
    public void addOccupiedTile() {
        tilesOccupied++;
    }
    public int tilesOccupied() {
        return tilesOccupied;
    }
}