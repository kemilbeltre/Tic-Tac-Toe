package tictactoe;

import java.awt.Color;
import java.awt.Component;

public class GameLogic {
    int shift, pX, pO; // Shift of the gamer
    boolean enable; // Enable and Unable the board

    /**
     * We will initiate the game with the following variables_
     * @param turn (It will indicate the player's turn: 0 - X, 1 - O)
     * @param pX (contains the total value of this player's wins)
     * @param pO (contains the total value of this player's wins)
     */
    public GameLogic(int shift, int pX, int pO) {
        this.shift = shift;
        this.pX = pX;
        this.pO = pO;
    }

    /**
     * Get shift
     * @return 
     */
    public int getShift() {
        return shift;
    }

    /**
     * Insert shift
     * @param shift 
     */
    public void setShift(int shift) {
        this.shift = shift;
    }

    public int getpX() {
        return pX;
    }

    public void setpX(int pX) {
        this.pX = pX;
    }

    public int getpO() {
        return pO;
    }

    public void setpO(int pO) {
        this.pO = pO;
    }
    
    /**
     * We will call this method of shift change
     */
    public void changeShift(){
        if(getShift()==0){
            setShift(1);
        }else{
            setShift(0);
        }
    }
    
    /**
     * Check if a three in line has been achieved, 
     * in case it has been achieved it will return 1, otherwise it returns 0 and continues the game
     * @masterboard (Game board)
     * @return 
     */
    public int checkGame(int matrix[][]){        
        // Check if there are three in a row
        
        // Check horizontal
        if( matrix[0][0] == matrix[0][1] && matrix[0][0] == matrix[0][2]){return 1;}
        else if (matrix[1][0] == matrix[1][1] && matrix[1][0] == matrix[1][2]){return 1;}
        else if (matrix[2][0] == matrix[2][1] && matrix[2][0] == matrix[2][2]){return 1;}
        
        //Check vertical
        else if (matrix[0][0] == matrix[1][0] && matrix[0][0] == matrix[2][0]){return 1;}
        else if (matrix[0][1] == matrix[1][1] && matrix[0][1] == matrix[2][1]){return 1;}
        else if (matrix[0][2] == matrix[1][2] && matrix[0][2] == matrix[2][2]){return 1;}
        
        //Check diagonal
        else if (matrix[0][0] == matrix[1][1] && matrix[0][0] == matrix[2][2]){return 1;}
        else if (matrix[2][0] == matrix[1][1] && matrix[2][0] == matrix[0][2]){return 1;}
        // Si no hay tres en raya
        else return 0;
    }
    
    /**
     * It will disable the button to prevent a chip from being repositioned in that slot
     * @param bt (Button selected)
     * @param x (Position x on the board)
     * @param y (Position and on the board)
     * @param matrix (Game board)
     * @param jp (Panel where the game board is located)
     * @param lX (Player X's JLabel)
     * @param lO (Player O's JLabel)
     * @return 
     */
    public int playerRoll(javax.swing.JButton bt, int x, int y, int matrix[][], javax.swing.JPanel jp, javax.swing.JLabel lX, javax.swing.JLabel lO){
        
        bt.setEnabled(false); // Deshabilita el botón
        
        // Insertar la ficha en el botón
               placeCard(matrix, x, y, bt);
               if (checkGame(matrix) !=0){       // Check if the game have been won
                   winner(lX, lO);
                   
                   // Disable the board
         enable = false;
         enableBoard(jp);
         return 1;
               }         
         changeShift();
         return 0;
    }
    
    /**
     * Update each player's score when they win the game
     * At the end of the score increase it is necessary to change the shift
     * @param lX (Player X's JLabel)
     * @param lO (Player O's JLabel)
     */
    public void winner(javax.swing.JLabel lX, javax.swing.JLabel lO){              
        // Increase winning player and insert result in jLabel  
        if(getShift() == 0){
            pX++;
            lX.setText(""+pX);
        }else{
            pO++;
            lO.setText(""+pO);
        }
        changeShift();
 
    }
    
    /**
     * It will enable or disable the board depending on the state of the enabled variable
     * @param jp (Panel where the game board is located)
     */
    public void enableBoard( javax.swing.JPanel jp){        
        // Blocks all elements of the JPanel
        for (int i = 0; i < jp.getComponents().length; i++){
            jp.getComponent(i).setEnabled(enable);
        }       
    }
    
    /**
     * We will insert the card in the corresponding position of the matrix
     * We will call the method paintCard
     * @param matrix (Game board)
     * @param t (Shift)
     * @param x (Position 'x' on the board)
     * @param y (Position 'y' on the board)
     * @param bt (Press Button)
     */
    public void placeCard(int matrix[][], int x, int y, javax.swing.JButton bt){
        // Insert card in the matrix position
        matrix[x][y]= getShift();
        paintCard(bt);       
        
    }
    
    /**
     * It will paint the chip on the visual game board, i.e. on the
     * @param bt (press button)
     */
    private void paintCard(javax.swing.JButton bt){
        // If the turn is 0 it will paint an X in red
        if(getShift() == 0){
            bt.setForeground(Color.red);
            bt.setText("X");
            // If the turn is 1, it will paint an O in blue
        }else{
            bt.setForeground(Color.blue);
            bt.setText("O");
        }          
    }
    
    /**
     * Initializes a new game, resets the matrix (Game board) and enables the board
     * @param matrix (Game Board)
     * @param jp (Panel where the game board is located)
     */
     
    public void startGame(int matrix[][], javax.swing.JPanel jp){
        // We fill in the matrix for the first time, avoiding repetition of the numbers
        for (int x = 0; x < 3; x++){
            for (int y = 0; y < 3; y++){
                matrix[x][y]=(x+10)*(y+10);
            }
        }

        // Enable Board
         enable = true;
         enableBoard(jp);
    }
}
