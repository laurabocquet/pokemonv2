public class Water extends Pokemon{
    
    public Water(String n, int h, int m){
        super(n, h, m);
    }
    
    /** 
    * @param atkType
    * @return String
    */
    public String getAttackMenu(int atkType){
        if(atkType == 1){
            return super.getAttackMenu(atkType);
        }
        else{
            return "Choose an attack:\n1. water gun\n2. bubble beam\n3. water fall";  
        }
        
    }
    
    /** 
    * @param atkType
    * @return int
    */
    public int getNumAttackMenuItems(int atkType){
        return 3;
    }
    
    /** 
    * @param atkType
    * @param move
    * @return String
    */
    public String getAttackString(int atkType, int move){
        if(atkType == 1){
            return super.getAttackString(atkType,move);
        }
        else{
            String attackChoice = "none";
            if (move == 1){
                attackChoice = "WATER GUNNED";
            }
            else if(move == 2){
                attackChoice = "BUBBLE BEAMED";
            }
            else if(move == 3){
                attackChoice = "WATER FALLED";
            }
            else{
                attackChoice = "There is pb in AttackString";
            }
            return attackChoice;  
        }
        
        
    }
    
    /** 
    * @param atkType
    * @param move
    * @return int
    */
    public int getAttackDamage(int atkType, int move){
        if(atkType == 1){
            return super.getAttackDamage(atkType, move);
        }
        else{
            int dam1 = 0;
            if(move == 1){
                // WATER GUNNED
                dam1 = (int)(Math.random()*5 + 1);
            }
            else if (move == 2){
                // BUBBLE BEAMED
                dam1 = (int)(Math.random()*3 + 1);
            }
            else if(move == 3){
                // WATER FALLED
                dam1 = (int)(Math.random()*4 + 1);
            }
            else{
                System.out.println("Wrong input");
            }
            return dam1; 
        }
        
        
    }
    
    /** 
    * @param p
    * @param atkType
    * @return int
    */
    public int getAttackMultiplier(Pokemon p, int atkType){
        if(atkType == 1){
            return super.getAttackMultiplier(p, atkType);
        }
        else{
            return (int)battleTable[1][p.getType()];  
        }
        
        
    }
    
    
    
}

