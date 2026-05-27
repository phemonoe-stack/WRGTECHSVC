/*
 * CellLife.java
 *
 * Created on December 1, 2003, 7:19 PM
 */

/**
 *
 * @author  Bill
 */
public class CellLife {
    int row = 0;
    int col = 0;
    boolean cells[][] = new boolean[row][col];
    int cell_buffer[][] = new int[row][col];
    /** Creates a new instance of CellLife */
    public CellLife(int setup) {
        if (setup == 1){
            for(int k = 0; k < col; k++){
                for(int i = 0; i < row; i++){
                    cells[i][k] = false;
                }
            }
            for(int i = 1; i < col-1; i++)
                cells[6][i] = true;
        }
        else if (setup == 2){}
        else if (setup == 3){}
        
    }
    
    public void nextGen(){
        for(int x=0; x < col; x++){
            for(int y=0; y < row; y++){
                cell_buffer[x][y] = 0;
            }
        }
        for(int x=1; x<col; x++){
            for(int y=1; y<row; y++){
                if(cells[x][y]){
                    cell_buffer[x-1][y-1]++;
                    cell_buffer[x][y-1]++;
                    cell_buffer[x+1][y-1]++;
                    cell_buffer[x-1][y]++;
                    cell_buffer[x+1][y]++;
                    cell_buffer[x-1][y+1]++;
                    cell_buffer[x][y+1]++;
                    cell_buffer[x+1][y+1]++;
                    
                }
            }
        }
        for(int x=0; x < col; x++){
            for(int y=0; y < row; y++){
                switch(cell_buffer[x][y]){
                    case 2:
                        break;
                    case 3:
                        cells[x][y] = true;
                        break;
                    default:
                        cells[x][y] = false;
                        break;
                        
                }
            }
        }
    }
    public String getCells(){
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < cells.length; i++){
            str.append("\t");
            for(int k = 0; k < cells.length-1; k++){
                str.append(cell_buffer[i][k]);
                
            }
            str.append("\n");
        }
        return str.toString();
        
    }
    public void clear(){
    
    }
    
}
