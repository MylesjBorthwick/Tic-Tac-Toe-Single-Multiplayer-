/**
 * Random Player: Subclass of Player. AI that adds marks randomly
 * 
 * Created by: Myles Borthwick and Ken Loughery
 */


public class RandomPlayer extends Player {
    //Random gen member variable
    protected RandomGenerator rgen;
    //Constructor
    public RandomPlayer(String name, char mark){
        super(name, mark);
        rgen = new RandomGenerator();
    }
    //Checks board conditions, and calls play functions
    protected void play(){

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


    @Override
	protected void makeMove() {

		
		int x = rgen.discrete(0, 2);
		int y = rgen.discrete(0, 2);
		
		while (super.getBoard().getMark(x, y) !=' '){
			x = rgen.discrete(0, 2);
			y = rgen.discrete(0, 2);
		}
	
	
		//add mark and display board
		board.addMark(x, y, mark);

	}

       

    
}

    

