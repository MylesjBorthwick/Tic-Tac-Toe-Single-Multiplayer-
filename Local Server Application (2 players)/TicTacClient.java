

import java.io.*;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * This class operates the backend for the client, handling the user inputs,
 * displayed messages, and error checking of inputs. This class will also
 * display the board for the client and manage the backend of the GUI.
 * 
 * ENSF 607 Lab 6
 * 
 * @author Myles Borthwick
 * @author Ken Loughery
 */

public class TicTacClient implements Constants {

	//TicTacClient Member variables
	private String name = null;
	private Board board;
	private char mark;
	private PrintWriter socketOut;
	private BufferedReader socketIn;
	private String response;
	private Socket thisSocket;
	private TicTacGUI userInterface;
	private int buttonCoordinates[][];

	/**
	 * Constructor that receives the server information to create a new socket to
	 * establish a connection to the server
	 * 
	 * @param serverName the name of the server to connect to
	 * @param portNumber the port number of the server to connect to
	 */
	public TicTacClient(String serverName, int portNumber) {
		try {
			thisSocket = new Socket(serverName, portNumber);
			socketIn = new BufferedReader(new InputStreamReader(thisSocket.getInputStream()));
			socketOut = new PrintWriter((thisSocket.getOutputStream()), true);
			buttonCoordinates = new int[9][2];
			populateButtonCoordinates();

		} catch (Exception e) {
			System.err.println(e.getStackTrace());
		}
	}
	/**
	 * helper method that will return the button label (one greater than in the coordinate array)
	 * @param row the row on the tic tac toe game that will be looked up (0-2)
	 * @param col the column on the tic tac toe game that will be looked up (0-2)
	 * @return the button label (from 1-9) that matches the coordinate
	 */
	private int buttonCoordinateLookup(int row, int col) {
		int lookup;
		for (lookup = 0; lookup < 9; lookup++) {
			if (buttonCoordinates[lookup][0] == row && buttonCoordinates[lookup][1] == col) {
				return lookup + 1;
			}
		}
		return -1;
	}
	
	/**
	 * this private helper method populates the buttonCoordinates array with coordinates that correspond 
	 * where the button is on the board. button 1 lines up with the upper left space, while button 2 is directly
	 * below it (one row down), and so on. 
	 */
	private void populateButtonCoordinates() {
		try {
			buttonCoordinates[0][0] = 0; //the row index
			buttonCoordinates[0][1] = 0; //the column index

			buttonCoordinates[1][0] = 1;
			buttonCoordinates[1][1] = 0;

			buttonCoordinates[2][0] = 2;
			buttonCoordinates[2][1] = 0;

			buttonCoordinates[3][0] = 0;
			buttonCoordinates[3][1] = 1;

			buttonCoordinates[4][0] = 1;
			buttonCoordinates[4][1] = 1;

			buttonCoordinates[5][0] = 2;
			buttonCoordinates[5][1] = 1;

			buttonCoordinates[6][0] = 0;
			buttonCoordinates[6][1] = 2;

			buttonCoordinates[7][0] = 1;
			buttonCoordinates[7][1] = 2;

			buttonCoordinates[8][0] = 2;
			buttonCoordinates[8][1] = 2;

		} catch (Exception e) {
			userInterface.updateMessage("Could not coordinate buttons");
		}
	}

	/**
	 * method that communicates to the server to update the client's copy of the
	 * board, receiving the location, and character of the other player's mark and
	 * placing it on the board. This method will also display the board for the
	 * client, and print out any waiting messages
	 */
	private void updateBoard() {

		this.response = null;
		try {
			if (!board.isFull() && !board.xWins() && !board.oWins()) {
				userInterface.updateMessage("Waiting for opponent to make their move...");
			}
			this.response = socketIn.readLine();

			if (this.response.contains("THE GAME IS OVER: ")) {
				userInterface.updateMessage(this.response);
				return;
			}
			try {
				int row = Integer.parseInt(this.response);
				this.response = socketIn.readLine();
				int col = Integer.parseInt(this.response);
				this.response = socketIn.readLine();
				char opposingMark = this.response.charAt(0);

				userInterface.updateMessage("Updated Opponent's move.");
				board.addMark(row, col, opposingMark);
				userInterface.setButtonText(buttonCoordinateLookup(row, col), Character.toString(opposingMark));

			} catch (Exception e) {
				userInterface.updateMessage("Error updating board: bad input.");
			}

		} catch (Exception e) {
			userInterface.updateMessage("Error updating board: connection issue. ");
		}

	}

	/**
	 * method that communicates with the server and user to establish the client's
	 * name, mark, and sets a new board
	 */
	private void setupGame() {
		userInterface = new TicTacGUI();
		this.userInterface.updateMessage("WELCOME TO THE GAME!");
		board = new Board();
		try {
			response = socketIn.readLine();
		} catch (Exception e1) {
			userInterface.updateMessage("Error receiving the mark character.");
		}
		this.mark = response.charAt(0);
		this.userInterface.updateMark(this.mark);

		userInterface.updateMessage("\nPlease enter the name of the " + this.mark + " player: ");
		getName();

		// board.display();
	}

	/**
	 * method that will get the client's name, calling itself recursively if
	 * presented with bad input
	 */
	private void getName() {
		while (userInterface.getPlayerName() == null) {
			name = userInterface.getPlayerName();
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		name = userInterface.getPlayerName();
	}
	
	/**
	 * method that will run the game for the client, awaiting turns from each player
	 */
	public void playGame()  {
		this.response  = null;
		setupGame();

		socketOut.println(this.name);

		if(this.mark == LETTER_X) {
			userInterface.updateMessage("Waiting for opponent to connect");
		}
		else {
			updateBoard();
		}
		
		while (true) {
			try {
				if(response.contains("THE GAME IS OVER: ")) {
					return;
				}	
				response = socketIn.readLine();
				userInterface.updateMessage(response);
				if(response.contains("THE GAME IS OVER: ")) {
					return;
				}
				
				makeMove();
				
				updateBoard();
				
				
			} catch (Exception e) {
				userInterface.updateMessage("Sending error: " + e.getMessage());
			}
		}
	}

	

	/**
	 * Method that runs the operation that allows the client to make a move on the board. This method works recursively to 
	 * receive input from the user that is within the boundaries of the board, and not in an occupied space. Once this input is
	 * received, it is sent back to the server thread
	 */
	private void makeMove(){
		//Initialize scanner
		userInterface.setButtonPressed(-1);
		userInterface.setCanUpdate(true);
		userInterface.updateMessage(this.name+ " please press a button for your next move: ");
		int button =0;
		while(userInterface.getButtonPressed() <0 || userInterface.getButtonPressed() > 9){
			try {
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (Exception e) {
				e.printStackTrace();
			}
			button = userInterface.getButtonPressed();
		}


		button = userInterface.getButtonPressed();
		int row = buttonCoordinates[button-1][0];
		int col = buttonCoordinates[button-1][1];
        //Check for existing marks 
        if(board.getMark(row, col)!=' '){
            userInterface.updateMessage("Please pick an empty space");
            makeMove();
        }
        //If there is no mark, add mark
        else{
			//add mark and display board
			userInterface.setButtonText(button, Character.toString(mark));
			board.addMark(row, col, this.mark);
			socketOut.println(row);
			socketOut.println(col);	
		}	
		userInterface.setCanUpdate(false);
	}
	
	/**
	 * closes all connections
	 */
	public void disconnect() {
		try {
			socketIn.close();
			socketOut.close();
			thisSocket.close();
		} catch (Exception e) {
			userInterface.updateMessage("Closing error: " + e.getMessage());
		}
		
	}
	
	/**
	 * main function to run the client
	 */
	public static void main(String[] args)  {
		TicTacClient aClient = new TicTacClient("localhost", 8099);
		aClient.playGame();
		aClient.disconnect();
	}
}