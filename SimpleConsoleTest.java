import java.io.FileNotFoundException;

// CS211 BC, W.P. Iverson
// version 2019
// changed sku and numbers
public class SimpleConsoleTest {

    public static void main(String[] args) throws FileNotFoundException {
    	SKU one = new SKU("book",13); // SKU #123018
		SKU two = new SKU("book",13); // a different book at 13
		SKU three = new SKU("another",42,10,399); // SKU is now 123020
		System.out.println(one.equals(one));  // true
		System.out.println(one.equals(two));  // false
		System.out.println(one); // book, $13.00
		System.out.println(two.priceFor(123)); // 1599.0
		ShoppingCart basket = new ShoppingCart();
		System.out.println(basket.size()); // 0
		NumSelected five = new NumSelected(two,5); 
		NumSelected six = new NumSelected(three,4);		
		basket.add(five);
		basket.add(six);
		System.out.println(basket.size()); // 2 	
		System.out.println(three); // another, $42.00 (10 for $399.00)
		System.out.println(three.getSKU()); // 123020
		System.out.println(basket.getTotal());  // 233.0
		basket.add(new NumSelected(three,1));
		System.out.println(basket.getTotal());  // 107.0
		basket.add(new NumSelected(three,11)); // remove the 1, add 11
		System.out.println("A: "+basket.getTotal());  // 506.0
		basket.setDiscount(true);
		System.out.println(basket.getTotal());  // 455.4
    }
}