import java.util.Scanner;

public class Main {
    
    /** 
    * @param args
    */
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        String Ascii1 = "              _                              ";
        Ascii1 = Ascii1 + "\n" + "  _ __   ___ | | _____ _ __ ___   ___  _ __  ";
        Ascii1 = Ascii1 + "\n" + " | '_ \\ / _ \\| |/ / _ \\ '_ ` _ \\ / _ \\| '_ \\";
        Ascii1 = Ascii1 + "\n" + " | |_) | (_) |   <  __/ | | | | | (_) | | | |";
        Ascii1 = Ascii1 + "\n" + " | .__/ \\___/|_|\\_\\___|_| |_| |_|\\___/|_| |_|";
        Ascii1 = Ascii1 + "\n" + " |_|                                         ";
        System.out.println(Ascii1);
        System.out.println("What is your Name?");
        String name = in.nextLine();
        Trainer player;
        // add an effect on the print that will make it print letter per letter like in a real pokemon game
        System.out.println(name + ", it is time for you to choose your Pokemon!");
        System.out.println("Today, we only have 3 Pokemons available, I hope you will like them");
        System.out.println("Here are the 3 options:");
        System.out.println("1. Charmander");
        System.out.println("Charmander is a small, bipedal, dinosaur like Pokemon.");
        System.out.println("This Pokemon has a flame that is constantly burning on the end of its tail.");
        System.out.println("It is a Fire Pokemon.");
        System.out.println("2. Squirtle");
        System.out.println("Squirtle is known as the Tiny Turtle Pokemon, are turtle Pokemon with large eyes and chubby cheeks.");
        System.out.println("He is capable of moving either on two feet or on all fours.");
        System.out.println("It is a Water Pokemon.");
        System.out.println("3. Bulbasaur");
        System.out.println("Bulbasaur is small, squat amphibian and plant Pokemon.");
        System.out.println("He has a light blue-green body with darker blue-green spots. He is known for his hidden abilities.");
        System.out.println("It is a Grass Pokemon.");
        System.out.println("Please enter your Pokemon the same way (ex: Charmander)");
        Pokemon pokemon = null;
        PokemonGenerator.getinstance();
        do{
            String choice = in.nextLine();
            if (choice.equals("Charmander")||choice.equals("Squirtle")||choice.equals("Bulbasaur")){
                pokemon = PokemonGenerator.getinstance().getPokemon(choice);
            }
            else{
                System.out.println("The name you entered is invalid, please enter the name of your chosen Pokemon.");    
            }
        }while (pokemon == null);
        Map map = Map.getInstance();
        int mapNum = 1;
        map.loadMap(mapNum);
        player = new Trainer(name, pokemon);
        map.reveal(map.findStart());
        System.out.println("You are represented by the little star *");
        System.out.println("You know where you are in the map now, you can explore new Areas seeking for adventures!");
        System.out.println("Pokemons are awaiting to be captured and trained by a trainer like you.");
        System.out.println("Although, if you want to leave us and end the game now you can.");
        char present;
        int direction;
        
        // implement trainer get location which will return point loc to use it 
        do {
            
            boolean switch1;
            do{
                System.out.println(map.mapToString(player.getLocation()));
                direction = mainMenu();
                present = 'o';
                switch1 = true;
                switch(direction){
                    case 1:
                    System.out.println("You chose to go North");
                    present = player.goNorth();
                    map.mapToString(player.getLocation());
                    break;
                    case 2:
                    System.out.println("You chose to go West");
                    // move the * to the west(implement go north in trainer)
                    present = player.goWest();
                    map.mapToString(player.getLocation());
                    break;
                    case 3:
                    System.out.println("You chose to go South");
                    // move the * to the south(implement go north in trainer)
                    present = player.goSouth();
                    map.mapToString(player.getLocation());
                    break;
                    case 4:
                    System.out.println("You chose to go East");
                    // move the * to the east(implement go north in trainer)
                    present = player.goEast();
                    map.mapToString(player.getLocation());
                    break;
                    case 5:
                    break;
                    default:
                    switch1 = false;
                    System.out.println("Invalid Input");
                    break;
                    
                }
            }while(!switch1);
            if(present == 'n'){
                System.out.println("Unfortunately there is nothing here, but try to move to a new zone!");
                map.reveal(player.getLocation());
                
            }
            else if( present == 'f'){
                System.out.println("You found the entrance to a new zone! But to get to it, you have to win against the gym leader.");
                System.out.println("To defeat him you have to knock out one of his pokemon.");
                Pokemon gymPoke = PokemonGenerator.getinstance().generateRandomPokemon(mapNum + 2);
                trainerAttack(player, gymPoke);
                if(gymPoke.getHp()<=0){
                    System.out.println("Congrats! You just won against the gym leader!");
                    System.out.println("You just discovered a new zone of level 2");
                    if (mapNum == 1){
                        mapNum ++;
                    }
                    else if (mapNum == 2){
                        mapNum ++;
                    }
                    else if (mapNum == 3){
                        mapNum = 1;
                    }
                    map.loadMap(mapNum);
                    map.mapToString(player.getLocation());
                    player.buffAllPokemon();
                    System.out.println("All of your pokemon got buffed.");
                    
                }
                
                
                map.reveal(player.getLocation());
                
            }
            else if (present == 'i'){
                int rand1 = (int) (Math.random() * 2) + 1;
                switch(rand1){
                    case 1:
                    player.receivePokeball();
                    break;
                    
                    case 2:
                    player.receivePotion();
                    break;
                    
                }
                map.reveal(player.getLocation());
                map.removeCharAtLoc(player.getLocation());
            }
            else if ( present == 'w'){
                System.out.println("You have encountered a wild pokemom");
                System.out.println("Let's find out what Pokemon it is!");
                Pokemon wild = PokemonGenerator.getinstance().generateRandomPokemon(mapNum);
                System.out.println("It is a wild " + wild.getName() +"!");
                map.reveal(player.getLocation());
                double xBefore = player.getLocation().getX();
                double yBefore = player.getLocation().getY();
                trainerAttack(player, wild);
                if(player.getLocation().getX() == xBefore && player.getLocation().getY() == yBefore){
                    map.removeCharAtLoc(player.getLocation());
                }
                
            }
            else if (present == 'p'){
                System.out.println("Oh no, you encounter Team Rocket, they were hidden and attack you, then disappeared");
                int rand9 = (int)(Math.random()*4 + 1);
                System.out.println("You take " + rand9 + " hp of damage!");
                player.takeDamage(rand9);
                map.removeCharAtLoc(player.getLocation());
                map.reveal(player.getLocation());
            }
            else if (present == 'c'){
                boolean switch8;
                int city;
                do{
                    System.out.println("You are in a city, you have two choices:");
                    System.out.println("You can either choose to go to");
                    System.out.println("1. Pokemon hospital, where all pokemon will be healed.");
                    System.out.println("2. The store where you can buy potions and pokeballs.");
                    System.out.println("3. Leave the city.");
                    city = CheckInput.getInt();
                    switch8 = true;
                    switch(city){
                        case 1:
                        System.out.println("All of your pokemons are getting healed:" );
                        System.out.println(player.getPokemonList());
                        player.healAllPokemon();
                        break;
                        case 2:
                        store(player);
                        break;
                        case 3:
                        switch8 = false;
                        break;
                    }
                }while(switch8);
                
                map.reveal(player.getLocation());
            }
            else if (present == 'o'){
                System.out.println("You chose to leave us.");
            }
            else {
                System.out.println("Il y a un probleme avec la lecture des caracteres");
            }
            
            System.out.println(player);
        }while(direction != 5||player.getHp() == 0);
        in.close();
        System.out.println("Thank you for playing to our Pokemon game!");
        System.out.println("We hope you liked it, it was made from the heart by the CrazyThings corporation.");
        System.out.println("Have a nice day!");
        
        
    }
    
    /** 
    * @return int
    */
    public static int mainMenu(){
        System.out.println("Here are the choices you have: pay attention the directions might not apply at all times!");
        System.out.println("1. Go North which is above you ");
        System.out.println("2. Go West which is to your left");
        System.out.println("3. Go South which is below you");
        System.out.println("4. Go East which is to your right");
        System.out.println("5. Exit the Pokemon game");
        int direction = CheckInput.getInt();
        return direction;
    }
    
    
    
    /** 
    * @param t
    * @param wild
    */
    public static void trainerAttack(Trainer t, Pokemon wild){
        Boolean combatEnded = false;
        Boolean isGymFight = Map.getInstance().getCharAtLoc(t.getLocation()) == 'f';
        Pokemon wildDouble = wild;
        
        do{
            System.out.println(wild);
            System.out.println("Here are the choices that you are facing:");
            System.out.println("1. You can fight, choose a pokemon to fight with.");
            System.out.println("2. You can use a potion and heal one of your pokemon");
            if(!isGymFight){
                System.out.println("3. You can try and catch the Pokemon, you will have more chances to catch it if its hp are lower.");
                System.out.println("4. If you don't want to fight for now you can runaway.");
                System.out.println("The wild case will stay there and you will move in a random direction");
                
            }
            int fight;
            int pokeChoice = -1;
            Pokemon pokemonCombat = null;
            do{
                fight = CheckInput.getInt();
                
            }while(!(fight == 1 || fight == 2 || fight == 3 && !isGymFight || fight == 4 && !isGymFight));
            switch(fight){
                case 1:
                System.out.println("You are going to attack  " + wild );
                System.out.println("You have to choose a pokemon from your list of pokemon");
                System.out.println(t.getPokemonList());
                pokeChoice = CheckInput.getInt();
                while(pokeChoice < 0 || pokeChoice >= t.getNumPokemon()){
                    System.out.println("Invalid choice of Pokemon");
                    pokeChoice = CheckInput.getInt();
                }
                pokemonCombat = t.getPokemon(pokeChoice);
                if (pokemonCombat.getHp() <= 0){
                    int rand3 = (int)(Math.random()*4 + 1);
                    System.out.println("Oh no, the pokemon you chose has no Hp.");
                    t.takeDamage(rand3);
                }
                System.out.println(pokemonCombat.getAttackTypeMenu());
                
                boolean switch3 = false;
                boolean switch4 = false;
                do{
                    int battle = CheckInput.getInt();
                    if(battle <= pokemonCombat.getNumAttackTypeMenuItems()){
                        switch3 = true;
                        System.out.println(pokemonCombat.getAttackMenu(battle));
                        do{
                            int move = CheckInput.getInt();
                            if(move <= pokemonCombat.getNumAttackMenuItems(battle)){
                                switch4 = true;
                                System.out.println(pokemonCombat.attack(wild, battle, move)); 
                                if(wildDouble.hashCode()!= wild.hashCode()){
                                    wildDouble.takeDamage(wildDouble.getHp()-wild.getHp());
                                }
                            }
                        }while(!switch4);  
                    }
                    
                }while(!switch3);
                int rand10 = (int)(Math.random()*4 + 1);
                if (rand10 == 1){
                    wild = PokemonGenerator.getinstance().addRandomDebuff(wild);
                    System.out.println("The enemy pokemon has been debuffed.");
                }
                if(wild.getHp() == 0){
                    combatEnded = true;
                }
                // wild has to chose either from special menu or basic menu
                // then randomly he chooses an attack and inflige les degats au pokemon choisit.
                break;
                case 2:
                System.out.println("Let's see if you have any potion in your bag.");
                if (t.hasPotion()){
                    System.out.println("You do have some potions which pokemon do you want to use it on?");
                    System.out.println(t.getPokemonList());
                    int pokePotion;
                    boolean switch15 = false;
                    do{
                        pokePotion = CheckInput.getInt();
                        if(pokePotion < t.getNumPokemon() && pokePotion >= 0){
                            switch15 = true;
                        }
                        
                    }while(!switch15);                        
                    t.usePotion(pokePotion);
                    
                    System.out.println(t.getPokemon(pokePotion).toString());
                }
                else{
                    System.out.println("Oh no, it looks like you have no potions left");
                }
                
                break;
                case 3: 
                if (t.hasPokeball()){
                    System.out.println("Let's try to catch that pokemon");
                    combatEnded = t.catchPokemon(wild);
                    if(t.getNumPokemon()>6){
                        System.out.println("You have too many pokemons, you can only have 6 pokemons, it is now time to choose which one you want to release");
                        System.out.println(t.getPokemonList());
                        boolean switch11 = true;
                        do{
                            int numRemoved = CheckInput.getInt();
                            Pokemon removed = t.removePokemon(numRemoved);
                            System.out.println("You released " + removed.toString());
                            
                        }while(switch11);
                        
                        
                    }
                }
                else{
                    System.out.println("You don't have enough pokeballs.");
                    combatEnded = false;
                }
                
                break;
                case 4:
                combatEnded = true;
                int rand8 = (int)(Math.random()*4+1);
                if (t.getLocation().getX() == 0){
                    switch(rand8){
                        case 1:
                        t.goSouth();
                        break;
                        case 2:
                        t.goSouth();
                        break;
                        case 3:
                        t.goEast();
                        break;
                        case 4:
                        t.goWest();
                        break;
                    }
                }
                else if (t.getLocation().getX() == 4){
                    switch(rand8){
                        case 1:
                        t.goNorth();
                        break;
                        case 2:
                        t.goNorth();
                        break;
                        case 3:
                        t.goEast();
                        break;
                        case 4:
                        t.goWest();
                        break;
                    }
                }
                else if (t.getLocation().getY() == 0){
                    switch(rand8){
                        case 1:
                        t.goNorth();
                        break;
                        case 2:
                        t.goSouth();
                        break;
                        case 3:
                        t.goEast();
                        break;
                        case 4:
                        t.goEast();
                        break;
                    }
                }
                else if (t.getLocation().getY() == 4){
                    switch(rand8){
                        case 1:
                        t.goNorth();
                        break;
                        case 2:
                        t.goSouth();
                        break;
                        case 3:
                        t.goWest();
                        break;
                        case 4:
                        t.goWest();
                        break;
                    }
                }
                else {
                    switch(rand8){
                        case 1:
                        t.goNorth();
                        break;
                        case 2:
                        t.goSouth();
                        break;
                        case 3:
                        t.goEast();
                        break;
                        case 4:
                        t.goWest();
                        break;
                    }
                }
                break;
                default:
                System.out.println("Invalid Input");
                break;
                
            }
            if (combatEnded == false){
                if (pokemonCombat == null){
                    int rand7 = (int)(Math.random()*t.getNumPokemon());
                    pokeChoice = rand7;
                    pokemonCombat = t.getPokemon(rand7);
                }
                
                int rand6 = (int)(Math.random()*2 + 1);
                int rand4 = (int)(Math.random()*3 + 1);
                int pokemonDead = 0;
                System.out.println(wild.attack(pokemonCombat, rand6, rand4));
                int rand13 = (int)(Math.random()*10 + 1);
                if(rand13 == 1){
                    t.debuffPokemon(pokeChoice);
                    
                    // pokemon qui a attaque doit etre debuff mais on ne peut pas set parce qu'on est dans main??
                }
                for(int i = 0; i< t.getNumPokemon(); i++){
                    if(t.getPokemon(i).getHp() <= 0){
                        pokemonDead++;
                    }
                }
                if(pokemonDead == t.getNumPokemon()){
                    combatEnded = true;
                    System.out.println("All of your Pokemons are KO.");
                }
            }
            System.out.println(t.toString());
            // get list of pokemons   
            
            // if k = 4 then ask who is this pokemon and display a pokemon and ask for an answer, the right answer will give some money to the trainer or a potion or a pokeball
            
        }while(t.getHp()>0 && !combatEnded);
    }
    
    /** 
    * @param t
    */
    public static void store(Trainer t){
        System.out.println("You entered the store");
        
        int store;
        boolean switch9;
        do{
            System.out.println(t);
            System.out.println("Do you want to buy:");
            System.out.println("1. Potions (5$)");
            System.out.println("2. Pokeballs (3$)");
            System.out.println("3. Leave the store.");
            store = CheckInput.getInt();
            switch9 = true;
            switch(store){
                case 1:
                if (t.spendMoney(5)){
                    System.out.println("You just got a potion.");
                    t.receivePotion();
                }
                else {
                    System.out.println("You don't have enough money to buy this.");
                }
                break;
                
                case 2:
                if (t.spendMoney(3)){
                    System.out.println("You just got a pokeball.");
                    t.receivePokeball();
                }
                else {
                    System.out.println("You don't have enough money to buy this I am sorry");
                }
                
                break;
                case 3:
                System.out.println("You are leaving the store...");
                switch9 = false;
                break;
                
                
            }
        }while(switch9);
        
    }
}
