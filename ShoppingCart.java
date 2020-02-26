/*  Yiu Chung Cheung
 07/08/2019
 CS 211
 Professor Iverson 
 Assignment #10 
 This project uses arraylist to act like a shopping cart. It is able to store item information, like its SKU 
 and price, and bulkprice if applicable. It will also be able to remove previous selections if the
 same item was selected again. It also checks if discounts could be apllied and if so, total price would be 10% off.
*/
import java.util.*;
public class ShoppingCart extends ArrayList<NumSelected> {
//private fields
   private double total;
   private boolean getDiscount; 
   
   //constructor
   public ShoppingCart() {
      super();  
   }
   
   //adds an order to the list, and checks if there are duplicated orders
   //if so, replace the previous duplicated order with the new one on the list
   public boolean add(NumSelected yes) {
      boolean duplicate = false; 
      for( int i = 0; i < size(); i ++){ //goes over the whole list
         if(get(i).getSKU() == yes.getSKU()){ //if the same order appeared on the list
            set(i, yes);        //replace the previous one with the new one
            duplicate = true;
         } 
      }
      if (!duplicate){  //it the order was never duplicated, meaning a totally new order 
         super.add(yes); //add order to list
      }
      return true;	
   }

//checks if discount should be applied, takes in boolean "discount" as parameter
   public void setDiscount(boolean value) { 
      getDiscount = value; 
   }

//returns total amount customer has to pay 
   public double getTotal() {
      total = 0.0;
      for (int i = 0; i < this.size(); i++){ //goes through arraylist,
         total += get(i).getPrice();   //adds up their prices together
      }
      if (getDiscount){ //if getDiscount is true
         double discountedTotal = total * 0.9; //total 10% off
         return discountedTotal; 
      }
      return total; 
   }

}