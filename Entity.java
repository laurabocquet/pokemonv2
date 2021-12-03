public abstract class Entity {
    private String name;
    private int hp;
    private int maxHp;


    /**
     * Purpose: 
     * @param n - name of the user 
     * @param mHp - maximum hp the user can have 
     */
    public Entity(String n, int h, int m){
        this.name = n; 
        this.maxHp = m; 
        this.hp = h;

    }

    /**
     * Purpose: get the HP value
     * @return - hp value
     */
    public int getHp(){
    
        return hp; 

    }

    /**
     * Purpose: give the maximum hp 
     * @return 
     */
    public int getMaxHp(){

        return this.maxHp; 
    }

    /**
     * Purpose: take the damage value away from the current hp 
     * @param d - damage 
     */
    public void takeDamage(int d){

        int dtkn = hp - d; 

        if (dtkn > 0 ){
            hp = dtkn;
        }
        else {
            hp = 0; 
        }

    }

    /**
     * Purpose: set the hp to the max hp 
     */
    public void heal(){

        hp = this.maxHp; 

    }

    /**
     * Purpose: gets the name of the entity
     * @return
     */
    public String getName(){
        return this.name;

    }


    /**
     * Purpose: return ("Name HP: hp/maxHP")
     * @return - formated string 
     */
    public String toString(){

        return String.format(" %s HP: %d/%d Hp \n", this.name, hp, this.maxHp);
    }
    
}