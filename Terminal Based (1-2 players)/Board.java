
/**
 * Board class creates board display, defines winning conditions
 * and adds marks to board
 * @author Dr.Moshirpour
 * Commented by: Myles Borthwick
 */
public class Board implements Constants {
	//Initialize variables
	private char theBoard[][];
	private int markCount;
	//Construct board
	public Board() {
		//Set mark count to zero
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}
	/**
	 * Returns mark at specified point on board
	 * @param row
	 * @param col
	 * @return stored mark
	 */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}

	/**
	 * Checks for full board
	 * @return true if markCount is 9
	 */
	public boolean isFull() {
		return markCount == 9;
	}

	/**
	 * Check if x has won
	 * @return true or false
	 */
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}

	/**
	 * Check if o has won
	 * @return true or false
	 */
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1)
			return true;
		else
			return false;
	}

	/**
	 * Displays board by printing symbols and spaces
	 * Constructs visual representation of board
	 */
	public void display() {
		displayColumnHeaders();
		addHyphens();
		for (int row = 0; row < 3; row++) {
			addSpaces();
			//For each row print board row
			System.out.print("    row " + row + ' ');
			for (int col = 0; col < 3; col++)
				System.out.print("|  " + getMark(row, col) + "  ");
			System.out.println("|");
			addSpaces();
			addHyphens();
		}
	}
	/**
	 * Adds mark to board
	 * @param row loaction
	 * @param col location
	 * @param mark type (X,O)
	 */
	public void addMark(int row, int col, char mark) {
		//Set position to mark
		theBoard[row][col] = mark;
		//Increase mark count
		markCount++;
	}
	/**
	 * Clears board
	 */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				//Set all positions to space
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;	//Reset mark counter
	}
	/**
	 * Check winner determines player winner based on line of 3 marks
	 * @param mark
	 * @return
	 */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;
		//Checks for horizontal wins
		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		//Checks for vertical wins
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}
		//Checks first diagonal win condition
		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		//Checks second diagonal win condition
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}
	/**
	 * Creates visual representation of column headers
	 */
	void displayColumnHeaders() {
		System.out.print("          ");
		//Print col and colnum for each col
		for (int j = 0; j < 3; j++)
			System.out.print("|col " + j);
		System.out.println();
	}
	/**
	 * Creates location dividers for visual board representation
	 */
	void addHyphens() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("+-----");
		System.out.println("+");
	}
	/**
	 * Adds spaces to visual representation of board
	 */
	void addSpaces() {
		System.out.print("          ");
		for (int j = 0; j < 3; j++)
			System.out.print("|     ");
		System.out.println("|");
	}
}
