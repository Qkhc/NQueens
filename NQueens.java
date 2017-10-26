//@author Elijah Holmberg && Robert Mushkot
import java.util.ArrayList;
import java.io.*;

public class NQueens {

    public static int n;
    public static int row;
    public static int col;
    public static boolean [][] board;
    public static ArrayList <Queen> queens = new ArrayList <>();
    public static PrintWriter writer;
    
    //Generates a new Queen and sets its position on board to be true
    public static void placeQueen(int col, int row){
       queens.add(new Queen(col, row));
       board[col][row] = true;
       checkBoard();
    }
    
    // Sets appropriate squares to be open or closed
    public static void checkBoard(){
        for(int i=n; i>0; i--){
            for(int j=1; j<=n; j++){
                for(int k=0; k<queens.size(); k++){
                    if (queens.get(k).col== j || queens.get(k).row== i)
                        board[j][i] = true;
                    else if (Math.abs(queens.get(k).col - j) == Math.abs(queens.get(k).row - i))
                        board[j][i] = true;
                }
            }
        }  
    }
    
    //Sets entire board to be false
    public static void setBoard(){
        for(int i=n; i>0; i--){
            for(int j=1; j<=n; j++){
                board[j][i] = false;
            }   
        }
    }
    
    //Prints out the variable board
    public static void printBoard() throws FileNotFoundException{
        for(int i=n; i>0; i--){
            for(int j=1; j<=n; j++){
               if(board[j][i]==true) 
                System.out.print("X ");
               else
                System.out.print("O ");
            }
            System.out.println();
        }
        System.out.println();
        printQueens();
    }
    
    //Prints out the coordinates of the queens to 
    public static void printQueens() throws FileNotFoundException{
        for (int i=0; i<queens.size(); i++){
            writer.println(queens.get(i).col+" "+queens.get(i).row);
            writer.flush();
        }
    }
    
    //Solves the NQueens problem
    public static boolean NQueens(int num){
      
        //Base Case, prevent infinite run
        if(num == 0 || queens.size() == n)
            return true;
        
        //searches for a open square 
        for(int i=num+1; i>0; i--){
            for(int j=1; j<=n; j++){
                
                //if no open square, returns false
                if(board[j][i] == true && (j==n && i==1))
                    return false;
                
                //if square closed, moves on
                else if(board[j][i] == true)
                    continue;
                
                //places the queen and eliminates contacted squares
                placeQueen(i, j);
                
                // recursively calls NQueens
                if(NQueens(num-1) == false){
                    queens.remove(queens.size()-1);
                    setBoard();
                    checkBoard();
                }
                else
                    return true;
            }
        }
        return false;
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException, FileNotFoundException, IOException {
        n = Integer.parseInt(args[0]);
        row = Integer.parseInt(args[1]);
        col = Integer.parseInt(args[2]);
        board = new boolean [n+1][n+1];
        
        //generates and sets "solution.txt to be the ouput file
        File outputFile;
        outputFile = new File("solution.txt");
        outputFile.createNewFile();
        writer = new PrintWriter(outputFile);
        
        //runs the complete program
        setBoard();
        placeQueen(col, row);
        if(NQueens(n-1)== true)
            printQueens();
        else{
            writer.println("No Solution");
            writer.flush();
        }
    }
}