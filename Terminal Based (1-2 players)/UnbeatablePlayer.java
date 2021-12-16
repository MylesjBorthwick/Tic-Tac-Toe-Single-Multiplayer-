/**
 * UnbeatablePlayer Class: BONUS BONUS AI player type
 * Cannot be beaten! Only tied against
 * Not needed for assignment, but fun to implement!
 * 
 * Subclass of SmartPlayer
 * 
 * Created By: Myles Borthwick and Ken Loughery
 */

public class UnbeatablePlayer extends SmartPlayer {

    public UnbeatablePlayer(String name, char mark) {
        super(name, mark);
    }
    //Checks if middle position is taken
    protected boolean check_for_mid() {
        if (board.getMark(1, 1) == ' '){
            return true;
        }
        else{
            return false;
        }
    }

    //Checks if corners are taken, if not adds a mark
    protected boolean check_for_corner(){
        //Top left
        if(board.getMark(0, 0)==' '){
            board.addMark(0,0,mark);
            return true;
        }
        //Bottom right
        if(board.getMark(2, 2)==' '){
            board.addMark(2,2,mark);
            return true;
        }
        //Top right
        if(board.getMark(0, 2)==' '){
            board.addMark(0,2,mark);
            return true;
        }
        //Bottom left
        if(board.getMark(2, 0)==' '){
            board.addMark(2,0,mark);
            return true;
        }
        return false;
    }

    // Make unbeatable moves
    protected void makeMove(){
        int x = rgen.discrete(0, 2);
        int y = rgen.discrete(0, 2);

        //Checks for win state first
        if(super.check_for_win(x, y)){
            return;
        }
        //If no win state, check for block
        if(super.check_for_block(x, y)){
            return;
        }
        //Positions itself for win/tie condition
        if(check_for_mid()){
            board.addMark(1, 1, mark);
            return;
        }

        if(check_for_corner()){
            return;
        }

		while (super.getBoard().getMark(x, y) !=' '){
			x = rgen.discrete(0, 2);
			y = rgen.discrete(0, 2);
		}
	
		board.addMark(x, y, mark);

    }
    
}
