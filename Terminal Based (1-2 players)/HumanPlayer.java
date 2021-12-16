import java.util.Scanner;
/**
 * Human Player Class: Asks human player for input and adds marks to board accordingly
 * Subclass of Player
 * Created by: Myles Borthwick and Ken Loughery
 */
public class HumanPlayer extends Player {
    //Constructor
    public HumanPlayer(String name, char mark) {
        super(name, mark);
    }
    //Handles win conditions of board
    protected void play() {

        while(board.isFull()!=true){
            //First player makes move
            makeMove();
            board.display();
            //Check for X win
            if(board.xWins() == true){
                //Declare end and winner
                System.out.println("Game Over");
                System.out.println(this.getName() + " Wins!");
                //End program
                break;
            }
            //Check for tie
            if(board.isFull()==true){
                //Declare end and tie
                System.out.println("Game Over");
                System.out.println("Tie Game!");
                break;
            }
            //Opponent move
            opponent.makeMove();
            board.display();
            //Check for O Win
            if (board.oWins() == true){
                System.out.println("Game Over");
                System.out.println(this.getOpponent().getName() + " Wins!");
                break;
            }
        }
    }

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


}
