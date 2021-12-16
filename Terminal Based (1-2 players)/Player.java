import java.util.Scanner;

/**
 * Player class handles player input, progresses the game 
 * and monitors board win/tie conditions
 * 
 * @author Myles Borthwick & Ken Loughery
 * @version 1.0
 * Submission date: Oct,2nd 2020
 */
abstract class Player {
    
    //Initialize variables
    protected String name;
    protected Board board;
    protected Player opponent;
    protected char mark;
    
    /**
     * Constructor for player
     * @param name of player (String)
     * @param mark of player (char)
     */
    public Player(String name, char mark){
        
        setName(name);
        setMark(mark);
    
    }

    /**
     * Play function checks win conditions on board,
     * play calls makeMove to progress game
     */
    protected abstract void play();

    /**
     * makeMove handles player move input and calls addMark 
     * to populate the board
     */
    protected void makeMove(){
        //Initialize scanner
        Scanner scan = new Scanner(System.in);
        //Row prompt + input
        System.out.println(this.getName()+ " please enter row number for your move: ");
        int row = scan.nextInt();
        //Check for valid row input
        if(row<0 || row>2){
            System.out.println("Please enter integer 0,1 or 2");
            makeMove();
            return;
        }
        //Column prompt + input
        System.out.println(this.getName()+" please enter column number for your move: ");
        int col = scan.nextInt();
        //Check for valid colinput
        if(col<0 || col>2){
            System.out.println("Please enter integer 0,1 or 2");
            makeMove();
            return;
        }
        //Check for existing marks 
        if(board.getMark(row, col)=='X' || board.getMark(row, col)=='O'){
            System.out.println("Please pick an empty space");
            makeMove();
        }
        //If there is no mark, add mark
        else{
            board.addMark(row, col, mark);
        }
    }

    /**
     * Get player name
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Set player name
     * @param name Set's player name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets opponent
     * @return opponent
     */
    public Player getOpponent() {
        return opponent;
    }

    /**
     * Sets opponent
     * @param opponent
     */
    public void setOpponent(Player opponent) {
        this.opponent = opponent;
    }

    /**
     * Gets mark
     * @return char return the mark
     */
    public char getMark() {
        return mark;
    }

    /**
     * Sets mark
     * @param mark the mark to set
     */
    public void setMark(char mark) {
        this.mark = mark;
    }

    /**
     * Gets board
     * @return Board return the board
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Sets board
     * @param board the board to set
     */
    public void setBoard(Board board) {
        this.board = board;
    }

}
