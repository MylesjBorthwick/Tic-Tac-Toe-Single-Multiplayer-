
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class creates the GUI layout and populates
 * with GUI components used for the tictactoe program.
 * Action handling for buttons and textfields can be found here.
 * 
 * ENSF 607 Lab 6
 * @author Myles Borthwick
 * @author Ken Loughery
 */

public class TicTacGUI {

    private boolean canUpdate = false;
    private int buttonPressed = -1;
    private String playerName = null;
    
    //Create new 9 new buttons
    private JButton b1=new JButton("");
    private JButton b2=new JButton("");
    private JButton b3=new JButton("");
    private JButton b4=new JButton("");
    private JButton b5=new JButton("");
    private JButton b6=new JButton("");
    private JButton b7=new JButton("");
    private JButton b8=new JButton("");
    private JButton b9=new JButton("");

    //Create GUI frame and add app title
    private JFrame f=new JFrame("Tic-Tac-Toe");

    //Create labels and textfield components
    private JTextField username = new JTextField(20);
    private JLabel character = new JLabel("Player:");
    private JLabel name = new JLabel("Username:");
    private JLabel status = new JLabel("Game Status");
    private JTextArea message = new JTextArea();
    private JTextField symbol = new JTextField(2);

    /**
     * GUI constructor. Sets layout of components and adds components
     * to JFrame
     */
    public TicTacGUI(){
        
        //Position labels/text and handle settings
        status.setBounds(275, 50, 100, 25);
        character.setBounds(50, 225, 100, 50);
        name.setBounds(50, 265, 100, 50);
        username.setBounds(125, 280, 100, 25);
        username.setEditable(true);

        //Listener for name input
        username.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(playerName == null){
                    playerName = username.getText();
                    updateMessage("Player name entered: "+ playerName);
                    username.setEditable(false);
                }
            }});

        symbol.setBounds(110, 240, 25, 25);
        symbol.setEditable(false);
        message.setEditable(false);

        JScrollPane jsp = new JScrollPane(message);
        jsp.setBounds(230, 75, 275, 75);
        
        //Position Buttons in JFrame
        b1.setBounds(50,50,50, 50);
        b2.setBounds(50,100,50, 50);
        b3.setBounds(50,150,50, 50);
        b4.setBounds(100,50,50, 50);
        b5.setBounds(100,100,50, 50);
        b6.setBounds(100,150,50, 50);
        b7.setBounds(150,50,50, 50);
        b8.setBounds(150,100,50, 50);
        b9.setBounds(150,150,50, 50);
        addButtonListeners();
        
        //Add all elements to jframe
        f.add(b1); 
        f.add(b2);
        f.add(b3);
        f.add(b4);
        f.add(b5);
        f.add(b6);
        f.add(b7);
        f.add(b8);
        f.add(b9);
        f.add(name);
        f.add(character);
        f.add(symbol);
        f.add(username);
        f.add(status);
        f.add(jsp);

        //Handle settings for JFrame
        f.setSize(500,400);  
        f.setLayout(null);
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    /**
     * Function for changing button text to specifified text
     * @param button to be changed
     * @param text text to be changed to
     */
    public void setButtonText(int button, String text){
        if(button == 1){
            b1.setText(text);
        }else if(button == 2){
            b2.setText(text);
        }else if(button ==3){
            b3.setText(text);
        }else if(button ==4){
            b4.setText(text);
        }else if(button ==5){
            b5.setText(text);
        }else if(button ==6){
            b6.setText(text);
        }else if(button ==7){
            b7.setText(text);
        }else if(button ==8){
            b8.setText(text);
        }else if(button ==9){
            b9.setText(text);
        }
    }
    
    /**
     * Function to set updatable status
     * @param set the boolean of the status to set to
     */
    public void setCanUpdate(boolean set){
        canUpdate = set;
    }

    /**
     * Sets value of button pressed, which is updated when canUpdate is true and a button is pressed by 
     * the user
     * @param number the number to set the button pressed index to
     */
    public void setButtonPressed(int number){
        buttonPressed = number;
    }
    /**
     * Getter for button pressed index for the backend functionality
     * @return button pressed
     */
    public int getButtonPressed(){
        return buttonPressed;
    }

    /**
     * Function to return player name
     * @return a string of the player's name (null if not set)
     */
    public String getPlayerName(){
        return playerName;
    }

    /**
     * Adds action listeners to all 9 buttons
     */
    private void addButtonListeners(){
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(canUpdate){  buttonPressed = 1;}
                else{updateMessage("Please wait until your turn to update the board");}
            }});
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(canUpdate){  buttonPressed = 2;}
                else{updateMessage("Please wait until your turn to update the board");}
            } });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(canUpdate){  buttonPressed = 3;}
                else{updateMessage("Please wait until your turn to update the board");}
            } });
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(canUpdate){  buttonPressed = 4;}
                else{updateMessage("Please wait until your turn to update the board");}
            } });
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(canUpdate){  buttonPressed = 5;}
                else{updateMessage("Please wait until your turn to update the board");}
            } });
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(canUpdate){  buttonPressed = 6;}
                else{updateMessage("Please wait until your turn to update the board");}
            } });
        b7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(canUpdate){  buttonPressed = 7;}
                else{updateMessage("Please wait until your turn to update the board");}
            } });
        b8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(canUpdate){  buttonPressed = 8;}
                else{updateMessage("Please wait until your turn to update the board");}
            } });
        b9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                if(canUpdate){  buttonPressed = 9;}
                else{updateMessage("Please wait until your turn to update the board");}
            } });
    }

    /**
     * Updates the message in the message box textfield with input
     * @param string to set the message box to
     */
    public void updateMessage(String input){
        this.message.setText(input);
    }

    /**
     * Sets sybmol texfield to player mark
     * @param in mark
     */
    public void updateMark(char in){
        this.symbol.setText(Character.toString(in));
    }
 
}