public abstract class PokemonDecorator extends Pokemon {
    private Pokemon pokemon;

    public PokemonDecorator(Pokemon p, String extraName, int extraHp){
        super(p.getName() + extraName, p.getHp() + extraHp, p.getMaxHp() + extraHp);
        pokemon = p;



    }
    
    /** 
     * @param atkType
     * @return String
     */
    public String getAttackMenu(int atkType){
        return pokemon.getAttackMenu(atkType);

    }

    
    /** 
     * @param atkType
     * @return int
     */
    public int getNumAttackMenuItems(int atkType){
        return pokemon.getNumAttackMenuItems(atkType);

    }

    
    /** 
     * @param atkType
     * @param move
     * @return String
     */
    public String getAttackString(int atkType, int move){
        return pokemon.getAttackString(atkType, move);

    }

    
    /** 
     * @param atkType
     * @param move
     * @return int
     */
    public int getAttackDamage(int atkType, int move){
        return pokemon.getAttackDamage(atkType, move);

    }

    
    /** 
     * @param p
     * @param type
     * @return int
     */
    public int getAttackMultiplier(Pokemon p, int type){
        return pokemon.getAttackMultiplier(p, type);

    }
    
    /** 
     * @param atkType
     * @return int
     */
    public int getAttackBonus(int atkType){
        return pokemon.getAttackBonus(atkType);

    }
    
    /** 
     * @return int
     */
    public int getType(){
        return pokemon.getType();
    }

    
}
