

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class TicTacServer implements Constants{
	
	private Socket theSocket;
	private ServerSocket serverSocket; 

	private Socket player1Socket;
	private Socket player2Socket;
	
	private ExecutorService playGame;

	
	/**
	 * constructor that starts the server
	 */
	public TicTacServer(int port) {
		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server is running...");
			playGame = Executors.newFixedThreadPool(10);
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	/**
	 * method that waits for two players to connect. This method assigns the player
	 * marks, and appropriate sockets, based on connection order
	 */
	private void getTwoPlayers() {
		
		int playerCount = 0;
		while (playerCount < 2) {
			connectNewSocket();

			try {
				PrintWriter socketOut  = new PrintWriter(this.theSocket.getOutputStream(),true);

				if(playerCount ==0) {
					socketOut.println(LETTER_X);
					player1Socket = theSocket;
				   	playerCount++;
				}
				else {
					socketOut.println(LETTER_O);
					player2Socket = theSocket;
					playerCount++;					
				}
				
			}catch (Exception e) {
				e.printStackTrace();
			}
		
		}
	}
		
	
	/**
	* method that runs the server - does not return, continues to wait for new
	* players and starts games until externally stopped
	*/
	public void runServer () {

		while (true) {
			getTwoPlayers();
			
			playGame.execute(new Game(player1Socket, player2Socket));
	        	        
		}
	}

	
	/**
	 * method that waits for a new connection, then sets theSocket to the new connection
	*/
	private void connectNewSocket() {
		
		try {
			this.theSocket = this.serverSocket.accept();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * closes all server connections
	 */
	public void disconnect() {

		try {
			theSocket.close();
			player1Socket.close();		
			player2Socket.close();
			serverSocket.close();		

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Driver function for server
	 * @param args external arguments passed on run (not used)
	 */
	public static void main (String [] args) {
	
			TicTacServer myServer = new TicTacServer(8099); //same port as for client
			myServer.runServer();
			myServer.disconnect();
	}
}
