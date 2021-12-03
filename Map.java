import java.awt.Point;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
public class Map {
    private char [][] map;
    private boolean [][] revealed;
    private static Map instance = null;
    
    private Map(){  
    }
    
    
    /** 
     * @return Map
     */
    public static Map getInstance(){
        if(instance == null){
            instance = new Map();
        }
        return instance;
    }
    /** 
    * @param mapNum
    */
    public void loadMap(int mapNum){
        try {
            File area = new File("Area"+ mapNum +".txt");
            Scanner readArea = new Scanner(area);
            map = new char[5][5];
            revealed = new boolean [5][5];
            int j = 0;
            
            while (readArea.hasNextLine()){
                String currentLine = readArea.nextLine();
                String[] stringArray = currentLine.split(" ");
                char[] ch = new char[stringArray.length];
                // Copy character by character into array
                for (int i = 0; i < stringArray.length; i++) {
                    ch[i] = stringArray[i].charAt(0);
                }
                map[j] = ch;
                revealed [j] = new boolean[]{false, false, false, false, false};
                j++;   
            }
            readArea.close();
        }
        catch (FileNotFoundException ex){
            System.out.println("File not found");
        }
    }
    
    /** 
    * @param p
    * @return char
    */
    public char getCharAtLoc(Point p){
        int x = (int) p.getX();
        int y = (int) p.getY();
        return map[x][y];
        
    }
    
    /** 
    * @param p
    * @return String
    */
    public String mapToString(Point p){
        // ajouter point later
        String mapInString = "";
        String line;
        for(int i = 0; i < map.length; i++){
            line = "";
            for( int j =0; j < map[i].length; j++ ){
                if(i == p.getX() && j == p.getY()){
                    line = line + "*";
                }
                else if(revealed[i][j]){
                    line = line + map[i][j];

                }
                else {
                    line = line + "x";
                }
                line = line + " ";
                
            }
            mapInString = mapInString + line.trim() + "\n";
        }
        return mapInString;
    }
    
    /** 
    * @return Point
    */
    public Point findStart(){
        Point start = new Point(0,0);
        for(int i = 0; i < map.length; i++){
            for( int j =0; j < map[i].length; j++ ){
                if (map[i][j] == 's'){
                    start.setLocation(i, j);
                }
            }
        }
        return start;

        
    }
    
    /** 
    * @param p
    */
    public void reveal(Point p){
        int x = (int) p.getX();
        int y = (int) p.getY();
        revealed[x][y] = true;
    }
    
    /** 
    * @param p
    */
    void removeCharAtLoc(Point p){
        int x = (int) p.getX();
        int y = (int) p.getY();
        map[x][y] = 'n';
        
    }
}
