

import java.io.*;
import java.net.Socket;


public class Game implements Runnable, Constants {
	

	private PrintWriter player1SocketOut;
	private BufferedReader player1SocketIn;
	private PrintWriter player2SocketOut;
	private BufferedReader player2SocketIn;
	private String player1Name;
	private String player2Name;
	private Board board;
	
	

	/**
	 * Constructor that receives two sockets, one for each player, and initiates the player names 
	 * from them to complete the player classes
	 * @param player1 the socket for the connections from the first player 
	 * @param player2 the socket for the connections from the second player
	 */
	public Game(Socket player1, Socket player2) {
		this.board = new Board();
	
		try {
			player1SocketIn = new BufferedReader(new InputStreamReader(player1.getInputStream()));
			player1SocketOut = new PrintWriter(player1.getOutputStream(),true);
			player2SocketIn = new BufferedReader(new InputStreamReader(player2.getInputStream()));
			player2SocketOut = new PrintWriter(player2.getOutputStream(),true);
			this.player1Name = player1SocketIn.readLine();
			this.player2Name = player2SocketIn.readLine();


		} catch (Exception e) {
			e.printStackTrace();
		}		


	}
	
	/**
	 * Method that will run to organize the gameplay, sending messages to each client when it is their turn,
	 * as well as with end-game conditions, as needed. 
	 */
	
	@Override
	public void run() {
				
		while(!board.isFull() && !board.xWins() && !board.oWins() ) {
			

			player1SocketOut.println(player1Name + " it is your turn to make a move.");
			updateBoard(true);
			
			if(board.isFull()|| board.xWins()||board.oWins()) {
				break;
			}	
			
			player2SocketOut.println(player2Name + " it is your turn to make a move.");
			updateBoard(false);

		} //end of loop
		
		if(board.isFull()) {
			player1SocketOut.println("THE GAME IS OVER: it is a tie!");
			player2SocketOut.println("THE GAME IS OVER: it is a tie!");
		}
		
		else if(board.xWins()) {
			player1SocketOut.println("THE GAME IS OVER: "+ player1Name + " is the winner!");
			player2SocketOut.println("THE GAME IS OVER: "+ player1Name + " is the winner!");
		}
		
		else if(board.oWins() ) {
			player1SocketOut.println("THE GAME IS OVER: "+ player2Name + " is the winner!");
			player2SocketOut.println("THE GAME IS OVER: "+ player2Name + " is the winner!");
		}

	

	}

	
	/**
	 * Method that updates the game's board with the clients', relaying the information made during a specified
	 * clients' turn to the one that has not made a turn, in addition to updating itself. Note the client class
	 * is responsible for error checking the entered markings.
	 * @param firstPlayer a boolean of whether the player who has just made the move is player 1
	 */

	private synchronized void updateBoard(boolean firstPlayer)   {

		if(firstPlayer) {
			try {
				int row = Integer.parseInt(player1SocketIn.readLine());
				int col = Integer.parseInt(player1SocketIn.readLine());		
						
				board.addMark(row, col, LETTER_X);

				player2SocketOut.println(row);
				player2SocketOut.println(col);
				player2SocketOut.println(LETTER_X);

			} catch (Exception e) {
				e.printStackTrace();
			}
		
        }
        else {
			try {
				int row = Integer.parseInt(player2SocketIn.readLine());
				int col = Integer.parseInt(player2SocketIn.readLine());
				board.addMark(row, col, LETTER_O);

				player1SocketOut.println(row);
				player1SocketOut.println(col);
				player1SocketOut.println(LETTER_O);

			} catch (Exception e) {
				e.printStackTrace();
			}
        }	//end of else
	} //end of method
} // end of class
