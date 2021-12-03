public class AttackUp extends PokemonDecorator {
    public AttackUp(Pokemon p){
        super(p, "+ATK",0);

    }
    
    
    /** 
     * @param atkType
     * @return int
     */
    public int getAttackBonus(int atkType){
        return (int)(Math.random()*2 + 1);

    }
    
}
