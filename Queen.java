//@author Elijah Holmberg
public class Queen {
    
    public  int row; 
    public  int col;
          
    public Queen(int row, int col){
        this.row = row;
        this.col = col;
    }
    
    public boolean isAttacking(Queen q){
        if (row == q.row || col == q.col)
            return true;
        else if (Math.abs(q.col - col) == Math.abs(q.row - row))
            return true;
        return false;  
    }
}
