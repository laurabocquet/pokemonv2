public class Fire extends Pokemon{
    
    // String specialMenu = "1. Ember\n2. Fire Blast\n3. Fire Punch";
    public Fire(String n, int h, int m){
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
            return "Choose an attack:\n1. Ember\n2. Fire Blast\n3. Fire Punch";  
        }
        //est ce que c'est que le special menu que ca retourne??? 
        
        
    }
    
    /** 
     * @param atkType
     * @return int
     */
    public int getNumAttackMenuItems(int atkType){
        return 3;
        // ducoup ca reste 3 pour l'instant
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
                attackChoice = "EMBERED";
            }
            else if(move == 2){
                attackChoice = "FIRE BLASTED";
            }
            else if(move == 3){
                attackChoice = "FIRE PUNCHED";
            }
            else{
                attackChoice = "There is pb in AttackString";
            }
            return attackChoice;  
        }
        
        // to be overidden comment dire c'est overidden dans une classe specifique?
        
    }
    
    /** 
     * @param atkType
     * @param move
     * @return int
     */
    public int getAttackDamage(int atkType, int move){
        // TOUS LES POKEMONS FIRE N'ONT PAS LES MEMES DAMAGES GIVEN.../// QUOI QUE?
        if(atkType == 1){
            return super.getAttackDamage(atkType, move);
        }
        else{
            int dam1 = 0;
            if(move == 1){
                // EMBERED
                dam1 = (int)(Math.random()*5);
            }
            else if (move == 2){
                // FIRE BLASTED
                dam1 = (int)(Math.random()*4 + 2);
            }
            else if(move == 3){
                // FIRE PUNCHED
                dam1 = (int)(Math.random()*4 + 1);
            }
            else{
                System.out.println("Wrong input");
            }
            return dam1;  
        }
        
        // to be overridden 
        
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
            return (int)battleTable[0][p.getType()];  
        }
        

        
    }
    
    
    
}
