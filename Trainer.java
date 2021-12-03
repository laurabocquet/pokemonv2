import java.util.ArrayList;
import java.awt.Point;

public class Trainer extends Entity {
    private int money;
    // change to private potions and 
    private int potions;
    private int pokeballs;
    private Point loc;
    private ArrayList<Pokemon>pokemon = new ArrayList<>();
    
    public Trainer(String n, Pokemon p){
        super(n, 25, 25);
        pokemon.add(p);
        money = (int)(Math.random()*5 + 20);
        potions = (int)(Math.random()*5 + 4);
        pokeballs = (int)(Math.random()*5 + 5);
        Map m = Map.getInstance();
        loc = m.findStart();
        
    }
    
    /** 
     * 
    * @return int
    */
    public int getMoney(){
        return this.money;
        
    }
    
    /** 
    * @param amt
    * @return boolean
    */
    public boolean spendMoney(int amt){
        boolean spendMoney = false;
        if (amt <= money){
            spendMoney = true;
            money = money - amt;
        }
        else {
            spendMoney = false;
        }
        return spendMoney;
        
    }
    
    /** 
    * @return boolean
    */
    public boolean hasPotion(){
        boolean hasPotion;
        if (potions > 0){
            hasPotion = true;
        } 
        else{
            hasPotion = false;
        }
        return hasPotion;
        
        
    }
    public void receivePotion(){
        potions++;
        System.out.println("You just received a potion ");
        
    }
    
    /** 
    * @param pokeIndex
    */
    public void usePotion(int pokeIndex){
        Pokemon pokemonHeal = getPokemon(pokeIndex);
        pokemonHeal.heal();
        pokemon.set(pokeIndex, PokemonGenerator.getinstance().addRandomBuff(pokemonHeal));
    }
    
    /** 
    * @return boolean
    */
    public boolean hasPokeball(){
        boolean hasPokeball;
        if (pokeballs > 0){
            hasPokeball = true;
        } 
        else{
            hasPokeball = false;
        }
        return hasPokeball;
        
        
    }
    public void receivePokeball(){
        pokeballs++;
        System.out.println("You just received a pokeball ");
        
    }
    
    /** 
    * @param p
    * @return boolean
    */
    public boolean catchPokemon(Pokemon p){
        
        boolean catchPoke;
        if(hasPokeball()){
            pokeballs--;
            int percentPoke = (int)(Math.random()*100 ); // 75
            double percentCatch = ((float)p.getHp()/p.getMaxHp())*100; // 5/25 = 0.2 * 100 = 20
            if (percentPoke >= percentCatch){// 75 >20
                catchPoke = true;
                pokemon.add(p);
                System.out.println("You caught the Pokemon!");
            }
            else{
                catchPoke = false;
                System.out.println("The Pokemon came back out!");
            }      
            
        }
        else{
            System.out.println("You don't have any pokeballs.");
            catchPoke = false; 
        }
        return catchPoke;
        
    }
    /** 
    * @return Point
    */
    public Point getLocation(){
        return loc;
        
        
    }
    
    /** 
    * @return char
    */
    public char goNorth(){
        int x = (int) loc.getX();
        int y = (int) loc.getY();
        char goN;
        if (x == 0){
            System.out.println("You cannot go north. It is outside of the map");
            goN = 'o';
            
        }
        else{
            loc.setLocation(x-1,y);
            goN = Map.getInstance().getCharAtLoc(loc);
        }
        return goN;  
    }
    
    /** 
    * @return char
    */
    public char goSouth(){
        int x = (int) loc.getX();
        int y = (int) loc.getY();
        char goS;
        if (x == 4){
            System.out.println("You cannot go south. It is outside of the map");
            goS = 'o';
        }
        else{
            loc.setLocation(x+1,y);
            goS = Map.getInstance().getCharAtLoc(loc);
        }
        return goS;
    }
    
    /** 
    * @return char
    */
    public char goEast(){
        int x = (int) loc.getX();
        int y = (int) loc.getY();
        char goE;
        if (y == 4){
            System.out.println("You cannot go East. It is outside of the map");
            goE = 'o';
        }
        else{
            loc.setLocation(x,y+1);
            goE = Map.getInstance().getCharAtLoc(loc);
        }
        return goE;
        
    }
    
    /** 
    * @return char
    */
    public char goWest(){
        int x = (int) loc.getX();
        int y = (int) loc.getY();
        char goW;
        if (y == 0){
            System.out.println("You cannot go West. It is outside of the map");
            goW = 'o';
        }
        else{
            loc.setLocation(x,y-1);
            goW = Map.getInstance().getCharAtLoc(loc);
        }
        return goW;
        
    }
    
    /** 
    * @return int
    */
    public int getNumPokemon(){
        return pokemon.size();
        
    }
    public void healAllPokemon(){
        for (int i = 0; i< pokemon.size(); i++){
            getPokemon(i).heal();
        }
        
    }
    
    public void buffAllPokemon(){
        for (int i = 0; i< pokemon.size(); i++){
            pokemon.set(i, PokemonGenerator.getinstance().addRandomBuff(pokemon.get(i)));        
        }
    }
    
    public void debuffAllPokemon(){
        for (int i = 0; i< pokemon.size(); i++){
            pokemon.set(i, PokemonGenerator.getinstance().addRandomDebuff(pokemon.get(i)));       
        }
    }
    
    
    /** 
     * @param index
     */
    public void debuffPokemon(int index){
        pokemon.set(index, PokemonGenerator.getinstance().addRandomDebuff(pokemon.get(index)));
    }
    
    /** 
    * @param index
    * @return Pokemon
    */
    public Pokemon getPokemon(int index){
        return pokemon.get(index);
    }
    
    /** 
    * @return String
    */
    public String getPokemonList(){
        String pokemonList = "";
        for (int i = 0; i< pokemon.size(); i++){
            pokemonList += i + ". " + getPokemon(i) + "\n";
        } 
        return pokemonList;
    }
    
    /** 
     * @param index
     * @return Pokemon
     */
    public Pokemon removePokemon (int index){
        return pokemon.remove(index);
    }
    
    /** 
    * @return String
    */
    public String toString(){
        String toStringTrainer = this.getName() + ": " + this.getHp() + "/" + this.getMaxHp() + " HP \n";
        toStringTrainer +=  "Money: " +this.money + "\n";
        toStringTrainer +=  "Potions: " + potions + "\n";
        toStringTrainer += "Pokeballs: " + pokeballs + "\n";
        toStringTrainer +=  "Pokemon(s): " + this.getNumPokemon() + "\n";
        for (int i = 0; i< pokemon.size(); i++){
            toStringTrainer = toStringTrainer + getPokemon(i).toString();
        }
        return toStringTrainer;
    }
}
