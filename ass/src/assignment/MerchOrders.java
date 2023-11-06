/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package assignment;

/**
 *
 * @author User
 */
public class MerchOrders {
    private int orderID;          
    private int merchItemID;      

    public MerchOrders(int orderID, int merchItemID) {
        this.orderID = orderID;
        this.merchItemID = merchItemID;
    }

    public int getOrderID() {
        return orderID;
    }

    public int getMerchItemID() {
        return merchItemID;
    }
    
    
}
