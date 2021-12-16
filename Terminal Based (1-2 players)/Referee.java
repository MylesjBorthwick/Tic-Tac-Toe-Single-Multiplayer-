/**
 * Referee class sets opponents, and starts the game
 * @author Myles Borthwick
 * @version 1.0
 * Submission date: Oct 2nd 2020
 */
public class Referee {

    //Initialize variables
    private Player xPlayer;
    private Player oPlayer;
    private Board board;

    /**
     * Constructor for referee
     */
    public Referee(){
        setBoard(board);
    }

    /**
     * Run game sets apponent and starts game
     */
    public void runTheGame(){
        //Set opponents
        xPlayer.setOpponent(oPlayer);
        oPlayer.setOpponent(xPlayer);
        //Display empty board
        board.display();
        //Begin game with playerX
        xPlayer.play();

    }

    /**
     * @return Player return the xPlayer
     */
    public Player getxPlayer() {
        return xPlayer;
    }

    /**
     * @param xPlayer the xPlayer to set
     */
    public void setxPlayer(Player xPlayer) {
        this.xPlayer = xPlayer;
    }

    /**
     * @return Player return the oPlayer
     */
    public Player getoPlayer() {
        return oPlayer;
    }

    /**
     * @param oPlayer the oPlayer to set
     */
    public void setoPlayer(Player oPlayer) {
        this.oPlayer = oPlayer;
    }

    /**
     * @return Board return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * @param board the board to set
     */
    public void setBoard(Board board) {
        this.board = board;
    }

}
