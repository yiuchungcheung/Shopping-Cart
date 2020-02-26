/*  Yiu Chung Cheung
 07/08/2019
 CS 211
 Professor Iverson 
 Assignment #10 
 This project uses arraylist to act like a shopping cart. It is able to store item information, like its SKU 
 and price, and bulkprice if applicable. It will also be able to remove previous selections if the
 same item was selected again. It also checks if discounts could be apllied and if so, total price would be 10% off.
*/   
import java.text.*;
public class SKU {
//private fields
   private String name; 
   private double price;
   private int bulkQuantity;
   private double bulkPrice; 
   private int quantity; 
   private int sku;
   private static int pkey_next = 123018; //starting point of SKU would be 123018
   
   
   //constructor (no bulkPrice)
   public SKU(String name, double price) {
      sku = pkey_next++; 
      this.name = name;
      this.price = price;
      if(price < 0){
         throw new IllegalArgumentException(); //throws error if price is negative
      }
   }

//constructor (with bulkPrice)
   public SKU(String name, double price, int bulkQuantity, double bulkPrice){
      sku = pkey_next++;
      this.name = name;
      this.price = price; 
      this.bulkQuantity = bulkQuantity;
      this.bulkPrice = bulkPrice; 
      if(price < 0|| bulkQuantity < 0 || bulkPrice < 0){ //throws error if any number is negative
         throw new IllegalArgumentException();
      }
   }
   
   //Returns the price for a given quantity of the item
   public double priceFor(int quantity){
      this.quantity = quantity; 
      if (quantity < 0){
         throw new IllegalArgumentException(); //throws error if quantity is negative
      }
      if (quantity < bulkQuantity || bulkQuantity == 0){ //when theres no bulkQuantity or when quantity is less than bulkQuantity
         return quantity * price; 
      }
      else { //when quantity is larger than or equal to bulkQuantity
         int remainder = quantity % bulkQuantity; //get the remainder quantity of items excluded from bulkPrice deal
         return (quantity / bulkQuantity) * bulkPrice + remainder * price; 
      } 
   }
   
   //returns string representation 
   public String toString() {
      NumberFormat nf = NumberFormat.getCurrencyInstance(); //method used from pdf
      if (bulkQuantity > 0){
         return name + ", " + nf.format(price) + " (" + bulkQuantity + " for " + nf.format(bulkPrice) + ")"; 
      }
      else {
         return name + ", " + nf.format(price);
      }
   }
   
   //checks if two orders are the same (checks their SKU values)
   public boolean equals(SKU other){
      if (this.getSKU() == other.getSKU()){ //if they are the same
         return true;
      } 
      else {
         return false; 
      }
   }
   
   //returns order's SKU referrence
   public int getSKU(){
      return sku; 
   }
   
}