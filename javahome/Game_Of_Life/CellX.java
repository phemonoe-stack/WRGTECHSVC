
public class CellX {
    int col = 12;
    int row = 13;
    int cells[][] = new int[row][col];
    int buffer[][] = new int[row][col];
    /** Creates a new instance of CellX */
    public CellX(int state) {
        
        for(int y = 0; y < col; y++){
            for(int x = 0; x < row; x++){
                cells[x][y] = 1;
            }
        }
        
        if(state == 1){ // 10 cell row
            for(int y = 1; y < 11; y++)
                cells[6][y] = 0;
        }
        else if(state == 2){ // small exploder
            
            cells[5][6] = 0;
            cells[6][5] = 0;
            cells[6][6] = 0;
            cells[6][7] = 0;
            cells[7][5] = 0;
            cells[7][7] = 0;
            cells[8][6] = 0;
        }
    }
    
    public void nextGen(){ // simulates the game of life
        int neighbor = 0;
        int count = 0;
        
        for(int i=0;i<12;i++) {
            for(int j=0;j<12;j++) {
                buffer[i][j] = 1;
            }
        }
        
        for(int x = 3; x<10; x++){
            for(int y = 1; y<11; y++){
                
                if(cells[x][y] == 0){ // living sim
                    neighbor = 0;
                    
                    if(cells[x-1][y-1] == 0)
                        neighbor++;
                    if(cells[x][y-1] == 0)
                        neighbor++;
                    if(cells[x+1][y-1] == 0)
                        neighbor++;
                    if(cells[x-1][y] == 0)
                        neighbor++;
                    if(cells[x+1][y] == 0)
                        neighbor++;
                    if(cells[x-1][y+1] == 0)
                        neighbor++;
                    if(cells[x][y+1] == 0)
                        neighbor++;
                    if(cells[x+1][y+1] == 0)
                        neighbor++;
                    
                    if (neighbor < 2 || neighbor > 3)
                        buffer[x][y] = 1;
                    else {
                        buffer[x][y] = 0;
                    }                    
                }
                else if(cells[x][y] == 1){ // dead sim
                    neighbor = 0;
                    
                    if(cells[x-1][y-1] == 0)
                        neighbor++;
                    if(cells[x][y-1] == 0)
                        neighbor++;
                    if(cells[x+1][y-1] == 0)
                        neighbor++;
                    if(cells[x-1][y] == 0)
                        neighbor++;
                    if(cells[x+1][y] == 0)
                        neighbor++;
                    if(cells[x-1][y+1] == 0)
                        neighbor++;
                    if(cells[x][y+1] == 0)
                        neighbor++;
                    if(cells[x+1][y+1] == 0)
                        neighbor++;
                    
                    if (neighbor == 3) {
                        buffer[x][y] = 0;
                    }
                    else
                        buffer[x][y] = 1;                    
                }                
            }
        }
        
        for(int i=0;i<12;i++) {
            for(int j=0;j<12;j++) {
                cells[i][j] = buffer[i][j];
            }
        }
        
    }
    
    public String getCells(){  // prints array to return string
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < row; i++){
            str.append("\t");
            for(int k = 0; k < col; k++){
                if(cells[i][k] == 1){
                    str.append(" ");
                }
                else{
                    str.append(cells[i][k]);
                }
            }
            str.append("\n");
        }
        return str.toString();
    }
}
