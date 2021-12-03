// public interface Grass {
    //     String specialMenu = "1. Vine Whip\n2. Razor Leaf\n3. Solar Beam";
    //     int getNumSpecialMenuItems = 3;
    //     public String vineWhip (Pokemon p);
    //     public String razorLeaf (Pokemon p);
    //     public String solarBeam (Pokemon p);
    // }
    public class Grass extends Pokemon{
        
        // String specialMenu = "1. Ember\n2. Fire Blast\n3. Fire Punch";
        public Grass(String n, int h, int m){
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
                return "Choose an attack:\n1. vine whip\n2. razor leaf\n3. solar beam";  
            }
            
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
                    attackChoice = "VINE WHIPED";
                }
                else if(move == 2){
                    attackChoice = "RAZOR LEAFED";
                }
                else if(move == 3){
                    attackChoice = "SOLAR BEAMED";
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
                    // VINE WHIPED
                    dam1 = (int)(Math.random()*3 + 1);
                }
                else if (move == 2){
                    // RAZOR LEAFED
                    dam1 = (int)(Math.random()*3 + 2);
                }
                else if(move == 3){
                    // SOLAR BEAMED
                    dam1 = (int)(Math.random()*6);
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
                return (int)battleTable[2][p.getType()]; 
            }
            
            // return 1 when basic attack?
            // to be overridden in fire.. and in buff debuff classes???
            
        }
        
        
        
    }
    