package com.labs1904;


import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class SecretRecipeDecoder {
    private static Map<String, String> ENCODING = new HashMap<String, String>() {
        {
            put("y", "a");
            put("h", "b");
            put("v", "c");
            put("x", "d");
            put("k", "e");
            put("p", "f");
            put("z", "g");
            put("s", "h");
            put("a", "i");
            put("b", "j");
            put("e", "k");
            put("w", "l");
            put("u", "m");
            put("q", "n");
            put("n", "o");
            put("l", "p");
            put("m", "q");
            put("f", "r");
            put("o", "s");
            put("i", "t");
            put("g", "u");
            put("j", "v");
            put("t", "w");
            put("d", "x");
            put("r", "y");
            put("c", "z");
            put("3", "0");
            put("8", "1");
            put("4", "2");
            put("0", "3");
            put("2", "4");
            put("7", "5");
            put("5", "6");
            put("9", "7");
            put("1", "8");
            put("6", "9");
        }
    };

    /**
     * Given a string named str, use the Caesar encoding above to return the decoded string.
     * @param str
     * @return
     */
    public static String decodeString(String str) {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            Character ch = str.charAt(i);
            if (ch.equals(' ')){
                stringBuilder.append(' ');
            } else {
                    String chStr = String.valueOf(ch);
                    String decodedChar = ENCODING.get(chStr);
                    stringBuilder.append(decodedChar);
            }
        }
        return stringBuilder.toString();
//        return "1 cup";
    }

    /**
     * Given an ingredient, decode the amount and description, and return a new Ingredient
     * @param line
     * @return
     */
    public static Ingredient decodeIngredient(String line) {
        int octothorpeIndex = line.indexOf("#");
        String amount = line.substring(0, octothorpeIndex);
        String description = line.substring(octothorpeIndex + 1);
        File f = new File("secret_recipe.txt");
        System.out.println(f.getAbsolutePath());
        System.out.println(Paths.get("").toAbsolutePath());

        return new Ingredient(decodeString(amount), decodeString(description));

    }

    public static void main(String[] args) {
        // I was unable to find a way to access my .txt files in resources. I tried multiple different combinations, including "resources/secret_recipe.txt" and "secret_recipe.txt". I did my best to finish the work despite these issues.
        Path decodedRecipePath = Paths.get("C:\\Users\\rjcoy\\OneDrive\\Documents\\Miscellaneous Projects\\Hours-with-Experts\\java\\src\\main\\resources\\decoded_recipe.txt");
        try{
            File secretRecipe = new File("C:\\Users\\rjcoy\\OneDrive\\Documents\\Miscellaneous Projects\\Hours-with-Experts\\java\\secret_recipe.txt");
            Scanner myReader = new Scanner(secretRecipe) ;
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                Ingredient decodedIngredient = decodeIngredient(data);
                String decodedIngredientString = decodedIngredient.toString();
                byte[] arr = decodedIngredientString.getBytes();
                try {
                    Files.write(decodedRecipePath, arr );
                } catch (IOException ex) {
                    System.out.println("Invalid Path");
                }
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occured");
            System.out.println(e);
        }


    }
}
