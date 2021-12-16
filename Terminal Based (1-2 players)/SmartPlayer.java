/**
 * SmartPlayer Class: Inherts blocking capability and adds
 * check its own win conditions.
 * Subclass of Blocking Player
 * 
 * Created By: Myles Borthwick and Ken Loughery
 */
public class SmartPlayer extends BlockingPlayer{
    //Constructor
    public SmartPlayer(String name, char mark) {
        super(name, mark);
    }

    //Check for own win conditions
    protected boolean check_for_win(int row, int column) {
        int mark_count = 0;
        
        //Horizontal win condition
        for (row = 0; row < 3; row++) {
            for (column = 0; column < 3; column++){
                //Counts own marks
				if (board.getMark(row, column) == mark && board.getMark(row, column)!=' '){
                    mark_count++;
                }

                else if(board.getMark(row, column)!=' '){
                    break;
                }
            }
            //Add mark if counter is 2
            if(mark_count==2){
                for (column = 0; column < 3; column++){
                    if(board.getMark(row, column)==' '){
                        board.addMark(row, column, mark);
                        return true;
                    }
                }
            }
            mark_count = 0;		
		
        }
        //Vertical win condition
        for (column = 0; column< 3; column++) {
			
            for (row = 0; row < 3; row++){
                
				if (board.getMark(row, column) == mark && board.getMark(row, column)!=' '){
                    mark_count++;
                }
                else if(board.getMark(row, column)!=' '){
                    break;
                }
            }

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
        
        //1st diagonal win condition
        for (row = 0; row < 3; row++){
                
            if (board.getMark(row, row) == mark && board.getMark(row, row)!=' '){
                mark_count++;
            }
            else if(board.getMark(row, row)!=' '){
                break;
            }
        }

        if(mark_count==2){
            for (row = 0; row < 3; row++){
                if(board.getMark(row, row)==' '){
                    board.addMark(row, row, mark);
                    return true;
                }
                
            }
            
        }
        mark_count = 0;	

        //2nd diagonal win condition
        for (row = 0; row < 3; row++){
                
            if (board.getMark(row, 2-row) == mark && board.getMark(row, 2-row)!=' '){
                mark_count++;
            }
            else if(board.getMark(row, 2-row)!=' '){
                break;
            }
        }
        
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

    //Makes move for player
    protected void makeMove(){
        int x = rgen.discrete(0, 2);
        int y = rgen.discrete(0, 2);
        //Check self win condition
        if(check_for_win(x, y)){
            return;
        }
        //Check for blocks
        if(super.check_for_block(x, y)){
            return;
        }
		//Add random marker
		while (super.getBoard().getMark(x, y) !=' '){
			x = rgen.discrete(0, 2);
			y = rgen.discrete(0, 2);
		}
	
		board.addMark(x, y, mark);

    }
    
}