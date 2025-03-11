package scor05;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;

import org.junit.jupiter.api.Test;

/**
 * Pruebas unitarias para el driver.
 */

public class PokeTests {

    @Test
    public void shouldReturnProperlyParsedPokemons(){
        HashMap<String, Digimon> pokemonDB = new HashMap<>();
        try{
            pokemonDB = (HashMap<String, Digimon>) Driver.getPokemons();
        }catch(IOException e){
            e.printStackTrace();
        }
        
        assertTrue(pokemonDB.containsKey("Bulbasaur"));
        assertTrue(pokemonDB.get("Bulbasaur").getType1().equals("Grass"));
        LinkedList<String> theoreticalBulbasaurAbilities = new LinkedList<>();
        theoreticalBulbasaurAbilities.add("Overgrow");
        theoreticalBulbasaurAbilities.add("Chlorophyll");
        assertTrue(pokemonDB.get("Bulbasaur").getAbilities().equals(theoreticalBulbasaurAbilities));
        System.out.println(pokemonDB.get("Mewtwo").isLegendary());
        assertTrue(pokemonDB.get("Mewtwo").isLegendary());
    }

    @Test
    public void shouldReturnProperlySortedPokemons(){
        HashMap<String, Digimon> pokemonDB = new HashMap<>();
        try{
            pokemonDB = (HashMap<String, Digimon>) Driver.getPokemons();
        }catch(IOException e){
            e.printStackTrace();
        }
        HashMap<String, Digimon> testUserCollection = new HashMap<>();
        testUserCollection.put("Pikachu", pokemonDB.get("Pikachu"));
        testUserCollection.put("Raichu", pokemonDB.get("Raichu"));
        testUserCollection.put("Sentret", pokemonDB.get("Sentret"));
        testUserCollection.put("Voltorb", pokemonDB.get("Voltorb"));
        // Pikachu, Raichu y Voltorb -> Electric, van primero porque Sentret -> Normal. Debe de retornar Pikachu, Raichu, Voltorb, Sentret. (orden alfabético de tipo1)
        LinkedList<Digimon> sortedPokemons = new LinkedList<>(testUserCollection.values());
        Collections.sort(sortedPokemons, new DigimonComparator());
        assertTrue(sortedPokemons.get(0).getName().equals("Pikachu"));
        assertTrue(sortedPokemons.get(1).getName().equals("Raichu"));
        assertTrue(sortedPokemons.get(2).getName().equals("Voltorb"));
        assertTrue(sortedPokemons.get(3).getName().equals("Sentret"));
    }
}
