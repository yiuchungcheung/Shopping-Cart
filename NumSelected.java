/*  Yiu Chung Cheung
 07/08/2019
 CS 211
 Professor Iverson 
 Assignment #10 
 This project uses arraylist to act like a shopping cart. It is able to store item information, like its SKU 
 and price, and bulkprice if applicable. It will also be able to remove previous selections if the
 same item was selected again. It also checks if discounts could be apllied and if so, total price would be 10% off.
*/

public class NumSelected {
//private fields 
   private SKU SKU; 
   private int quantity;
   //constructor
   public NumSelected(SKU SKU, int quantity){
      this.SKU = SKU;
      this.quantity = quantity;
   }

//returns the price * quantity, includes bulkPrice
   public double getPrice() {
      return SKU.priceFor(quantity);
   }
   
   //returns SKU referrence 
   public int getSKU(){
      return SKU.getSKU();
   }
   
   //returns string representation 
   public String toString(){
      return SKU.toString(); 
   }
}