/**
 * Blocking player class: AI player that blocks other players
 * wins
 * 
 * Created by: Myles Borthwick and Ken Loughery
 */

public class BlockingPlayer extends RandomPlayer {
    
    public BlockingPlayer(String name, char mark) {
        super(name, mark);
    }
    //Blocks 2 mark lines (checks for opponent win conditions)
    protected boolean check_for_block(int row, int column) {
        //Mark counter to check condition
        int mark_count = 0;
        
        //Check horizontal condition
        for (row = 0; row < 3; row++) {
            for (column = 0; column < 3; column++){
                //Counts opponents marks
                if (board.getMark(row, column) != mark && board.getMark(row, column)!=' '){
                    mark_count++;
                }

                else if(board.getMark(row, column)!=' '){
                    break;
                }
            }
            //If two marks exist horizonally, block
            if(mark_count==2){
                for (column = 0; column < 3; column++){
                    if(board.getMark(row, column)==' '){
                        board.addMark(row, column, mark);
                        return true;
                    }
                    
                }
                
            }
            //Reset mark counter
            mark_count = 0;		
        }
        //Check Veritcal Condition
        for (column = 0; column< 3; column++) {
			
            for (row = 0; row < 3; row++){
                
				if (board.getMark(row, column) != mark && board.getMark(row, column)!=' '){
                    mark_count++;
                }
                else if(board.getMark(row, column)!=' '){
                    break;
                }
            }
            //If two marks exist vertically, block
            if(mark_count==2){
                for (row = 0; row < 3; row++){
                    if(board.getMark(row, column)==' '){
                        board.addMark(row, column, mark);
                        return true;
                    }
                    
                }
                
            }
            mark_count = 0;		
		
        }
        
        //Check 1st diagonal condition
        for (row = 0; row < 3; row++){
                
            if (board.getMark(row, row) != mark && board.getMark(row, row)!=' '){
                mark_count++;
            }
            else if(board.getMark(row, row)!=' '){
                break;
            }
        }
        //If two marks exist on diag1, block
        if(mark_count==2){
            for (row = 0; row < 3; row++){
                if(board.getMark(row, row)==' '){
                    board.addMark(row, row, mark);
                    return true;
                }
                
            }
            
        }
        mark_count = 0;	

        //Check 2nd diagonal condition
        for (row = 0; row < 3; row++){
                
            if (board.getMark(row, 2-row) != mark && board.getMark(row, 2-row)!=' '){
                mark_count++;
            }
            else if(board.getMark(row, 2-row)!=' '){
                break;
            }
        }
        //If two marks exist on diag2, block
        if(mark_count==2){
            for (row = 0; row < 3; row++){
                if(board.getMark(row, 2-row)==' '){
                    board.addMark(row, 2-row, mark);
                    return true;
                }
                
            }
            
        }
       return false;
		
        }
    
    //Make move checks for blocks then adds marker to board
    protected void makeMove() {
        //Random numbers 0-2
        int x = rgen.discrete(0, 2);
        int y = rgen.discrete(0, 2);
        
        //Check for blocks
        if(check_for_block(x, y)){
            return;
        }
		//Add random mark
		while (super.getBoard().getMark(x, y) !=' '){
			x = rgen.discrete(0, 2);
			y = rgen.discrete(0, 2);
		}
		board.addMark(x, y, mark);

	}

}
