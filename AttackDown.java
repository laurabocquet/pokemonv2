public class AttackDown extends PokemonDecorator {
    public AttackDown(Pokemon p){
        super(p, "-ATK",0);

    }
    
    
    /** 
     * @param atkType
     * @return int
     */
    public int getAttackBonus(int atkType){
        return -1;

    }
    
}
