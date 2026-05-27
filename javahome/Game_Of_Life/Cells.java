/*
 * Cells.java
 *
 * Created on November 25, 2003, 12:05 AM
 */

/**
 *
 * @author  Bill
 */
public class Cells {
    
    int row = 13;
    int col = 12;
    private String [][] cells = new String[row][col];
    
    /** Creates a new instance of Cells */
    public Cells(int setup) {
        if (setup == 1){
            for(int k = 0; k < col; k++){
                for(int i = 0; i < row; i++){
                    cells[i][k] = "1";
                }
            }
            for(int i = 1; i < col-1; i++)
                cells[6][i] = "0";
        }
        else if (setup == 2){}
        else if (setup == 3){}
        
    }
    public void nextGen(int cell_arr[][]){
        int live = 0;
        
        for(int k = 0; k < cell_arr.length; k++){
            for(int i = 0; i < cell_arr.length; i++){
                if(cell_arr[i][k] == 0){
                    if(cell_arr[i-1][k-1] == 0)
                        live++;
                    if(cell_arr[i][k-1] == 0)
                        live++;
                    if(cell_arr[i+1][k-1] == 0)
                        live++;
                    if(cell_arr[i-1][k] == 0)
                        live++;
                    if(cell_arr[i+1][k] == 0)
                        live++;
                    if(cell_arr[i-1][k+1] == 0)
                        live++;
                    if(cell_arr[i][k+1] == 0)
                        live++;
                    if(cell_arr[i+1][k+1] == 0)
                        live++;
                    
                }
                while(true){
                    switch(live){
                        case 2:
                            break;
                        case 3:
                            
                    }
                }
                
            }
        }
        
    }
    public String getCells(){
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < cells.length; i++){
            str.append("\t");
            for(int k = 0; k < cells.length-1; k++){
                str.append(cells[i][k]);
                
            }
            str.append("\n");
        }
        return str.toString();
        
    }
    
}
