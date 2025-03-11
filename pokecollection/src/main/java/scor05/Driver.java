package scor05;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Driver {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        boolean loop = true;
        TreeMap<String, Digimon> userCollection = new TreeMap<>();
        do{
            Map<String, Digimon> pokemonDB = mapFactory();
            try{
                pokemonDB = getPokemons();
            }catch(IOException e){
                System.out.println("Error al abrir el archivo de datos con la información de los Pokémon. Revise el directorio del .csv y su nombre.");
                e.printStackTrace();
            }
            System.out.println("-".repeat(50));
            System.out.println("\t\tColección de Pokémon");
            System.out.println("-".repeat(50));
            System.out.println("Las funciones de este programa son:");
            System.out.println("1. Agregar un Pokémon a su colección");
            System.out.println("2. Mostrar datos de un Pokémon");
            System.out.println("3. Mostrar todos los Pokémon con su tipo primario en su colección");
            System.out.println("4. Mostrar todos Pokémon existentes");
            System.out.println("5. Mostrar Pokémon que tengan una habilidad específica");
            System.out.println("6. Salir");
            System.out.print("\nIngrese el número correspondiente a la función que desea ejecutar: ");
            int op = sc.nextInt();
            sc.nextLine();
            switch(op){
                case 1:
                    System.out.print("\nIngrese el nombre del Pokémon que desea agregar a su colección: ");
                    String name = sc.nextLine();
                    if (!pokemonDB.containsKey(name)){
                        throw new IllegalArgumentException("El Pokémon " + name + " no existe en la base de datos de los Pokémon.");
                    }

                    if (userCollection.containsKey(name)){
                        System.out.println("El Pokémon " + name + " ya existe en su colección.");
                    }else {
                        userCollection.put(name, pokemonDB.get(name));
                        System.out.println("El Pokémon " + name + " ha sido agregado a su colección.");
                    }
                    break;

                case 2: 
                    System.out.print("\nIngrese el nombre del Pokémon que desea consultar: ");
                    String nombre = sc.nextLine();
                    if (!pokemonDB.containsKey(nombre)){
                        throw new IllegalArgumentException("El Pokémon " + nombre + " no existe en la base de datos de los Pokémon.");
                    }
                    Digimon pokemon = pokemonDB.get(nombre);
                    System.out.println("Nombre: " + pokemon.getName());
                    System.out.println("Número de Pokédex: " + pokemon.getNumber());
                    System.out.println("Tipo primario: " + pokemon.getType1());
                    System.out.println("Tipo secundario: " + pokemon.getType2());
                    System.out.println("Clasificación: " + pokemon.getClassification());
                    System.out.println("Altura: " + pokemon.getHeight());
                    System.out.println("Peso: " + pokemon.getWeight());
                    System.out.println("Habilidades: " + pokemon.getAbilities());
                    System.out.println("Generación: " + pokemon.getGeneration());
                    System.out.println("Estado legendario: " + pokemon.isLegendary());
                    break;

                case 3:
                    if (userCollection.isEmpty()){
                        System.out.println("Su colección está vacía.");
                    } else {
                        System.out.println("\nLos Pokémon actualmente en su colección son:");
                        for (Digimon d : userCollection.values()){
                            System.out.println(d.getName());
                        }
                    }
            }
        }while (loop);
    }

    public static Map<String, Digimon> mapFactory() {
        int op = 0;
        while (op < 1 || op > 3){
            System.out.print("Ingrese el número correspondiente a la implementación de Map desea utilizar: \n1. HashMap\n2. TreeMap\n3. LinkedHashMap \n\nR/ ");
            op = sc.nextInt();
            sc.nextLine();
            switch(op){
                case 1: return new HashMap<String, Digimon>();
                case 2: return new TreeMap<String, Digimon>();
                case 3: return new LinkedHashMap<String, Digimon>();
                default: System.out.println("Opción inválida, pruebe de nuevo.");
            }
        }
        return null;
    }

    public static Map<String, Digimon> getPokemons() throws IOException {
        Map<String, Digimon> pokemonDB = new HashMap<>();
        BufferedReader br = new BufferedReader(new FileReader("pokemon_data_pokeapi.csv"));
        String line;
        while ((line = br.readLine()) != null) {
            String[] split = line.split(",");
            List<String> abilities = new LinkedList<String>();
            if (split[7].charAt(0) == '"'){ // En el CSV, si hay más de una habilidad, empiezan siempre con "
                String abilitiesText = split[7].substring(1, split[7].length()-1);
                for (String ability : abilitiesText.split(",")) {
                    abilities.add(ability);
                }
            }else{
                abilities.add(split[7]);
            }
            
            pokemonDB.put(split[0], new Digimon(
                                split[0], 
                                Integer.parseInt(split[1]), 
                                split[2], 
                                split[3], 
                                split[4], 
                                Integer.parseInt(split[5]), 
                                Integer.parseInt(split[6]), 
                                abilities,
                                Byte.parseByte(split[8]), 
                                Boolean.parseBoolean(split[9])));
        }
        br.close();
        return pokemonDB;
    }

}
