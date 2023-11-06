/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author ivanmjq
 */
public class FoodCombo {
    private String foodID, foodName, foodDescription;
    private double foodPrice;

    public FoodCombo(String foodID, String foodName, String foodDescription, double foodPrice) {
        this.foodID = foodID;
        this.foodName = foodName;
        this.foodDescription =foodDescription;
        this.foodPrice = foodPrice;
    }
    
    public String getFoodID() {
        return foodID;
    }

    public String getFoodName() {
        return foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodID(String foodID) {
        this.foodID = foodID;
    }
    
    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public void setDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public void setPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }
    
    @Override
    public String toString() {
        return "Food Name: " + foodName + "\n" 
                + "  Description: " + foodDescription 
                + "  Price: RM" + foodPrice + "0\n";
    }
    
    
    private static final List<FoodCombo> foodCombos = getAllFoodCombo();
    
    public static char foodInput() {
        Scanner sn3 = new Scanner(System.in);
        char foodIpt;
        do {
                System.out.print("Do you want to buy a food combo? (y/n) > ");
                foodIpt = sn3.next().charAt(0);
                if(foodIpt != 'y' && foodIpt != 'n') {  //error msg
                    System.out.println("Wrong Input, Please enter 'y' or 'n' only!!");
                }
        } while(foodIpt != 'y' && foodIpt != 'n');
        
        if(foodIpt == 'y') {
            chooseFoodCombo();
        }
        return foodIpt;
    }
    
    public static void displayFoodCombo() {
        System.out.println("");
        for(int i=0;i<getFoodComboSize();i++) {
            System.out.print(i+1 + ".");
            System.out.println(foodCombos.get(i).toString());
        }
    }
    
    public static int getFoodComboSize() {
        return foodCombos.size();
    }
    
    public static void chooseFoodCombo() {
        Scanner sn4 = new Scanner(System.in);
        boolean validInput = false;
        int foodComboCount = getFoodComboSize();
        int foodChoice = 0;
        char ctnFoodCombo = 'n';
        
        
        do {
            displayFoodCombo();
        
            do {
                validInput = false;
                do {
                    try {
                        System.out.print("Enter the number of food combo > ");
                        foodChoice = sn4.nextInt();
                        validInput = true;
                    } catch (Exception e) {
                            System.out.println("Invalid input. Please enter a number.");
                            sn4.nextLine(); // Clear the input buffer
                    }
                } while (!validInput);
                
                if(foodChoice < 1 || foodChoice > foodComboCount) {  //error msg
                    System.out.println("Please enter from 1 to " + foodComboCount);
                }
            } while(foodChoice < 1 || foodChoice > foodComboCount);
            
            //save foodCombo into txt file
            appendFoodPurchase(foodChoice);
            
            //ask continue to buy another food combo or not
            do {
                System.out.print("Do you want to buy another food combo? (y/n) > ");
                ctnFoodCombo = sn4.next().charAt(0);
                if(ctnFoodCombo != 'y' && ctnFoodCombo != 'n') {  //error msg
                    System.out.println("Wrong Input, Please enter 'y' or 'n' only!!");
                }
            } while(ctnFoodCombo != 'y' && ctnFoodCombo != 'n');
            
        } while(ctnFoodCombo == 'y');
        
    }
    
    public static void appendFoodPurchase(int foodChoice) {
        FoodCombo food = foodCombos.get(foodChoice - 1);
        
        try (BufferedWriter writeFoodPurc = new BufferedWriter(new FileWriter("membTicket&Food.txt",true))) {
            writeFoodPurc.write(food.getFoodName());
            writeFoodPurc.write(':');
            writeFoodPurc.write(Double.toString(food.getFoodPrice()));
            writeFoodPurc.write(':');
            
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    //read food combo from text file 
    public static List<FoodCombo> getAllFoodCombo() {
        List<FoodCombo> foodCombos = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader("food.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splt = line.split(":");
                String foodID = splt[0];
                String foodName = splt[1];
                String foodDescription = splt[2];
                double foodPrice = Double.parseDouble(splt[3]);
                
                FoodCombo foodBuffer = null;
                
                foodBuffer = new FoodCombo(foodID, foodName, foodDescription, foodPrice);
                
               foodCombos.add(foodBuffer); // Add the movie to the ArrayList
               
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return foodCombos;
    }
    
}
