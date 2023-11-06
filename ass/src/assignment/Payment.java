/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.BufferedReader;


/**
 *
 * @author User
 */
public class Payment {

    private static int nextPaymentID = 1;
    static {
        nextPaymentID = loadNextPaymentID();
    }

    private int PaymentID;
    private double totalPaid;
    private String paymentMethod;

    public Payment(double totalPaid, String paymentMethod) {
        this.PaymentID = nextPaymentID;
        this.totalPaid = totalPaid;
        this.paymentMethod = paymentMethod;
    }

    public int getPaymentID() {
        return PaymentID;
    }

    public double getTotalPaid() {
        return totalPaid;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setTotalPaid(double totalPaid) {
        this.totalPaid = totalPaid;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    
    //load next payment id from a file
    private static int loadNextPaymentID() {
        try (BufferedReader reader = new BufferedReader(new FileReader("payment_id.txt"))) {
            String line = reader.readLine();
            if (line != null) { //if not found
            int lastPaymentID = Integer.parseInt(line);
            return lastPaymentID + 1;
            }
        } catch (IOException e) {
            System.err.println("Error loading next payment ID: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Invalid format for next payment ID: " + e.getMessage());
        }
        return 1; //if invalid
    }
    
    
    //save next payment id
    public static void saveNextPaymentID() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("payment_id.txt"))) {
            writer.write(Integer.toString(nextPaymentID));
        } catch (IOException e) {
            System.err.println("Error saving next payment ID: " + e.getMessage());
        }
    }

    public static double payment(Orders order, Scanner scanner, String name,int paymentChoice,double totalPrice) {
        String paymentMethod = null;
        double totalAmount = 0.0;
        double change = 0.0;
        double totalPaid = 0.0;
        boolean sucessfulPaid = true;
        String accNo = null;
        String cvv = null;

        switch (paymentChoice) {
            case 1:
                paymentMethod = "Cash";
                do{
                    System.out.print("Enter the amount of cash paid: ");
                
                    totalPaid = scanner.nextDouble();
                    totalAmount = totalPrice;
                    change = totalPaid - totalAmount;
                    if (totalPaid < totalPrice) {
                        sucessfulPaid = false;
                        System.out.println("Insufficient cash paid\n");
                    }else
                        sucessfulPaid = true;
                    
                }while(!sucessfulPaid);
                break;
            case 2:
                
                
                boolean isValid = false;
                
                paymentMethod = "Debit Card/Credit Card";
                do{
                    scanner.nextLine();
                    System.out.print("Enter bank account number: ");
                    accNo = scanner.nextLine();
                    //consists of >=1 no
                    if (!accNo.matches("\\d+")) {
                        System.out.println("Invalid account number. Please enter a numeric value.");
                    } else {
                        isValid = true;
                    }
                } while(!isValid);
                
                do{
                System.out.print("Enter cvv: ");
                cvv = scanner.nextLine();
                if (!cvv.matches("\\d{3}")) {   //check if have three digit
                    System.out.println("Invalid CVV. Please enter a three-digit number.");
                }else {
                        isValid = true;
                }
                } while(!isValid);
                
                totalAmount = order.getOtherTotalPrice();
                break;
            case 3:
                paymentMethod = "QR Payment";
                totalAmount = order.getTotalPrice();
                break;
            default:
                System.out.println("Invalid payment method choice.");
        }

        Payment payment = new Payment(totalAmount, paymentMethod);
        order.setPayment(payment);
        saveNextPaymentID();

        System.out.println("\nPayment Detail");
        System.out.println("------------------------------------");
        System.out.println("Payment ID: " + payment.getPaymentID());
        System.out.println("Username: " + name);
        System.out.println("Payment Method: " + payment.getPaymentMethod());
        System.out.println("------------------------------------");
        
        
        if (paymentChoice == 1) {
            System.out.printf("Total Paid: RM%.2f\n" ,totalPaid);
            System.out.printf("Change: RM%.2f\n", change);
        }
        
        else if (paymentChoice == 2) {
            System.out.printf("Account no: %s\n" ,accNo);
            System.out.printf("CVV: %s\n", cvv);
        }
        
        return totalAmount;
    }
    
    public static void writePaymentDetailsToFile(Orders order, Payment payment, String name, double total) {
        String fileName = "payment_details.txt";

        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            PrintWriter bufferedWriter = new PrintWriter(fileWriter);
            bufferedWriter.println("\nPayment ID: " + payment.getPaymentID());
            bufferedWriter.println("Username: " + name);
            bufferedWriter.println("Payment Method: " + payment.getPaymentMethod());
            bufferedWriter.printf("Total Amount: RM%.2f\n", total);

            //System.out.println("\nPayment details written to " + fileName);
            bufferedWriter.close();
        } catch (IOException e) {
            System.err.println("Error writing payment details to the file: " + e.getMessage());
            e.printStackTrace();
        }
    }

}
