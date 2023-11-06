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
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Orders {

    private static int nextOrderID = 1;
    private int orderID;
    private double totalPrice;
    private List<MerchOrders> merchandiseOrders;
    private Payment payment;
    
    static {
        nextOrderID = loadNextOrderID();
    }

    public Orders(String CustUsername) {
        this.orderID = nextOrderID++;
        this.totalPrice = 0.0;
        this.merchandiseOrders = new ArrayList<>();
    }

    public int getOrderID() {
        return orderID;
    }
    
    private static int loadNextOrderID() {
        try (BufferedReader reader = new BufferedReader(new FileReader("order_id.txt"))) {
            String line = reader.readLine();
            if (line != null) {
                return Integer.parseInt(line);
            }
        } catch (IOException e) {
            System.err.println("Error loading next order ID: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid format for next order ID: " + e.getMessage());
        }
        return 1; 
    }

    public static void saveNextOrderID() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("order_id.txt"))) {
            writer.write(Integer.toString(nextOrderID));
        } catch (IOException e) {
            System.err.println("Error saving next order ID: " + e.getMessage());
        }
    }

    public double getTotalPrice() {
        totalPrice = 0.0;
        for (MerchOrders merchOrder : merchandiseOrders) {
            int merchItemID = merchOrder.getMerchItemID();
            double merchItemPrice = getMerchItemPrice(merchItemID);
            totalPrice += merchItemPrice;

        }
        return totalPrice;
    }

    public double getOtherTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public List<MerchOrders> getMerchandiseOrders() {
        return merchandiseOrders;
    }

    public void setMerchandiseOrders(List<MerchOrders> merchandiseOrders) {
        this.merchandiseOrders = merchandiseOrders;
    }

    public void addMerchandiseOrder(MerchOrders merchOrder) {
        merchandiseOrders.add(merchOrder);
    }

    private double getMerchItemPrice(int merchID) {
        switch (merchID) {
            case 1:
                return 40.0;
            case 2:
                return 20.0;
            case 3:
                return 15.0;
            case 4:
                return 38.0;
            case 5:
                return 38.50;
            default:
                return 0.0;
        }
    }

}