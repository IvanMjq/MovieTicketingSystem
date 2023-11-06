/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author User
 */

public class Merch {
    static Merch tshirt = new Merch("Barbie T-shirt", "Barbie movie", "Movie Collection", 40.00);
    static Merch poster = new Merch("Movie Poster", "High-quality movie poster", "Movie Collection", 20.00);
    static Merch badge = new Merch("Minion Badge", "Minion movie", "Movie Collection", 15.00);
    static Merch mug = new Merch("Barbie Mug", "Barbie movie", "Movie Collection", 38.00);
    static Merch hat = new Merch("Barbie Hat", "Barbie movie", "Movie Collection", 38.50);
        
    private static int nextID = 1;
    private int merchID;
    private String merchItem;
    private String merchCollection;
    private String merchCompany;
    private double merchPrice;

    public Merch(String merchItem, String merchCollection, String merchCompany, double merchPrice) {
        nextID++;
        this.merchID = nextID;
        this.merchItem = merchItem;
        this.merchCollection = merchCollection;
        this.merchCompany = merchCompany;
        this.merchPrice = merchPrice;
    }

    public int getMerchID() {
        return merchID;
    }

    public String getMerchItem() {
        return merchItem;
    }

    public String getMerchCollection() {
        return merchCollection;
    }

    public String getMerchCompany() {
        return merchCompany;
    }

    public double getMerchPrice() {
        return merchPrice;
    }

    public static void displayMerchItems() {
        System.out.println("\nAvailable Merch Items:");
        System.out.println("====================================");
        System.out.printf("%-10s %-15s %-10s\n", "Item ID", "Item Name", "Price (RM)");
        System.out.println("====================================");
        System.out.printf("%-10d %-15s %-10.2f\n", tshirt.getMerchID(), tshirt.getMerchItem(), tshirt.getMerchPrice());
        System.out.printf("%-10d %-15s %-10.2f\n", poster.getMerchID(), poster.getMerchItem(), poster.getMerchPrice());
        System.out.printf("%-10d %-15s %-10.2f\n", badge.getMerchID(), badge.getMerchItem(), badge.getMerchPrice());
        System.out.printf("%-10d %-15s %-10.2f\n", mug.getMerchID(), mug.getMerchItem(), mug.getMerchPrice());
        System.out.printf("%-10d %-15s %-10.2f\n", hat.getMerchID(), hat.getMerchItem(), hat.getMerchPrice());
        System.out.println("====================================");
    }
    
    
    
    //for display ordered item
    public static String getMerchItemName(int merchItemID) {
        if (merchItemID == tshirt.getMerchID()) {
            return tshirt.getMerchItem();
        } else if (merchItemID == poster.getMerchID()) {
            return poster.getMerchItem();
        } else if (merchItemID == badge.getMerchID()) {
            return badge.getMerchItem();
        } else if (merchItemID == mug.getMerchID()) {
            return mug.getMerchItem();
        } else if (merchItemID == hat.getMerchID()) {
            return hat.getMerchItem();
        } else {
            return "Unknown";
        }
    }

    public static double getMerchItemPrice(int merchItemID) {
        if (merchItemID == tshirt.getMerchID()) {
            return tshirt.getMerchPrice();
        } else if (merchItemID == poster.getMerchID()) {
            return poster.getMerchPrice();
        } else if (merchItemID == badge.getMerchID()) {
            return badge.getMerchPrice();
        } else if (merchItemID == mug.getMerchID()) {
            return mug.getMerchPrice();
        } else if (merchItemID == hat.getMerchID()) {
            return hat.getMerchPrice();
        } else {
            return 0.0;
        }
    }
}



