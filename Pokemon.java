import java.lang.NumberFormatException;
public abstract class Pokemon extends Entity{
    
    static final double [][] battleTable = {{1,0.5,2},{2,1,0.5},{0.5,2,1}};
    
    
    
    public Pokemon(String n, int h, int m){
        super(n,h,m);
        // (int)(Math.random()*4+20)
        
    }
    
    
    /** 
     * @return String
     */
    public String getAttackTypeMenu(){
        String attackMenu = "Do you want to choose an attack from:\n1. Basic Menu\n2. Special Menu";
        return attackMenu;
    }

    
    /** 
     * @return int
     */
    public int getNumAttackTypeMenuItems(){
        return 2;

    }

    
    /** 
     * @param atkType
     * @return String
     */
    public String getAttackMenu(int atkType){
        String basicMenu = "Choose an attack:\n1. slam\n2. tackle\n3. punch";
        return basicMenu;
        // to be overridden comment dire que c'est overidden dans fire par example?

    }

    
    /** 
     * @param atkType
     * @return int
     */
    public int getNumAttackMenuItems(int atkType){
        return 3;
        // to be overridden
    }

    
    /** 
     * @param p
     * @param atkType
     * @param move
     * @return String
     */
    public String attack(Pokemon p, int atkType, int move){
        int dam2 = (int)(getAttackDamage(atkType, move)*getAttackMultiplier(p, atkType) + getAttackBonus(atkType));
        p.takeDamage(dam2);
        String attackedPoke = p.getName() + " is " + this.getAttackString(atkType, move) + " and takes " + dam2 + " HP of damage.";
        return attackedPoke;
    }

    
    /** 
     * @param atkType
     * @param move
     * @return String
     */
    public String getAttackString(int atkType, int move){
        String attackChoice = "none";
        if (move == 1){
            attackChoice = "SLAMMED";
        }
        else if(move == 2){
            attackChoice = "TACKLED";
        }
        else if(move == 3){
            attackChoice = "PUNCHED";
        }
        else{
            attackChoice = "There is pb in AttackString";
        }
        return attackChoice;
        // to be overidden comment dire c'est overidden dans une classe specifique?

    }

    
    /** 
     * @param atkType
     * @param move
     * @return int
     */
    public int getAttackDamage(int atkType, int move){
        int dam1 = 0;
        if(move == 1){
            dam1 = (int)(Math.random()*6);
        }
        else if (move == 2){
            dam1 = (int)(Math.random()*2 + 2);
        }
        else if(move == 3){
            dam1 = (int)(Math.random()*4 + 1);
        }
        else{
            System.out.println("Wrong input");
        }
        return dam1;
        // to be overridden 

    }

    
    /** 
     * @param p
     * @param atkType
     * @return int
     */
    public int getAttackMultiplier(Pokemon p, int atkType){
        return 1;
        // return 1 when basic attack?
        // to be overridden in fire.. and in buff debuff classes???

    }

    
    /** 
     * @param atkType
     * @return int
     */
    public int getAttackBonus(int atkType){
        return 0;
        // to be overridden in buff debuff; the pokemon when buffed or debuffed get a random bonus 1/2 or malus -1 on his attacks
        // idk if i should do it here or not  

    }

    
    /** 
     * @return int
     */
    public int getType(){
        int type = -1;
        try{
            if (this instanceof Fire) {
                type = 0;
            }
            else if (this instanceof Water) {
                type = 1;
            }
            else if (this instanceof Grass) {
                type = 2;
            }
            else if (type == -1 ){
                System.out.println("There is a problem in getType");
            }
        }catch(NumberFormatException ex){
            System.out.println("Can't read the type");
        }
        return type;

    }


}
