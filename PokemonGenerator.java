import java.util.HashMap;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

public class PokemonGenerator{
    private HashMap<String, String> pokemon;
    private static PokemonGenerator instance = null;
    
    private PokemonGenerator() {
        try {
            File myObj = new File("PokemonList.txt");
            Scanner myReader = new Scanner(myObj);
            pokemon = new HashMap<>();
            while (myReader.hasNextLine()) {
              String data = myReader.nextLine();
              String[] pokemonType = data.split(",");
              pokemon.put(pokemonType[0], pokemonType[1]);
              
            }
            myReader.close();
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
        
        
    }
    
    
    /** 
     * @return PokemonGenerator
     */
    public static PokemonGenerator getinstance(){
        if(instance == null){
            instance = new PokemonGenerator();
        }
        return instance;
        
        
    }
    
    /** 
     * @param level
     * @return Pokemon
     */
    public Pokemon generateRandomPokemon(int level){
      int rand12 = (int)(Math.random()*pokemon.size());
      String name = pokemon.keySet().toArray()[rand12].toString();
      Pokemon randPoke = getPokemon(name);
      for (int i = 1; i< level; i++){
        randPoke = addRandomBuff(randPoke); 
      }
      return randPoke;
    }

    
    /** 
     * @param name
     * @return Pokemon
     */
    public Pokemon getPokemon(String name){
      String type = pokemon.get(name);
      int maxHp = (int)(Math.random()*4 + 20);
      if(type.equals("Fire")){
        return new Fire(name, maxHp, maxHp);
      }
      else if(type.equals("Water")){
        return new Water(name, maxHp, maxHp);
      }
      else{
        return new Grass(name, maxHp, maxHp);
      }
        
    }
    
    /** 
     * @param p
     * @return Pokemon
     */
    public Pokemon addRandomBuff(Pokemon p){
      int rand11 = (int)(Math.random()*2);
      if(rand11 == 0){
        return new AttackUp(p);
        // AttackUp
      }
      else{
        return new HpUp(p);
        // HpUp
      }
        
    }
    
    /** 
     * @param p
     * @return Pokemon
     */
    public Pokemon addRandomDebuff(Pokemon p){
      int rand11 = (int)(Math.random()*2);
      if(rand11 == 0){
        return new AttackDown(p);
        // AttackDown
      }
      else{
        return new HpDown(p);
        // HpDown
      }
        
    }
    
    
}
