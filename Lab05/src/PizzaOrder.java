
import java.util.Scanner;

public class PizzaOrder {

    private String orderID;
    private String customerName;
    private String size;
    private int toppingCount;
    private boolean isDelivery;

    // Default constructor
    public PizzaOrder() {
        orderID = "N/A";
        customerName = "Unknown";
        size = "Small";
        toppingCount = 0;
        isDelivery = false;
    }

    // Constructor with orderID and customerName
    public PizzaOrder(String orderID, String customerName) {
        this();
        this.orderID = orderID;
        this.customerName = customerName;
    }

    // Constructor with all five attributes
    public PizzaOrder(String orderID, String customerName,
                      String size, int toppingCount,
                      boolean isDelivery) {
        this.orderID = orderID;
        this.customerName = customerName;
        setSize(size);
        this.toppingCount = Math.max(0, toppingCount);
        this.isDelivery = isDelivery;
    }

    // Getter for size
    public String getSize() {
        return size;
    }

    // Setter for size with validation
    public void setSize(String size) {
        if (size != null &&
            (size.equalsIgnoreCase("Small") ||
             size.equalsIgnoreCase("Medium") ||
             size.equalsIgnoreCase("Large"))) {
            this.size = size.substring(0, 1).toUpperCase()
                    + size.substring(1).toLowerCase();
        } else {
            this.size = "Small";
        }
    }

    // Calculate base price
    public double getBasePrice() {
        switch (size) {
            case "Small":
                return 8.00;
            case "Medium":
                return 10.00;
            case "Large":
                return 12.00;
            default:
                return 8.00;
        }
    }

    // Calculate total price
    public double calculateTotalPrice() {
        double total = getBasePrice() + toppingCount * 1.50;

        if (isDelivery) {
            total += 5.00;
        }

        return total;
    }

    // Overloaded method with discount
    public double calculateTotalPrice(double discountPercentage) {
        double total = calculateTotalPrice();

        if (discountPercentage < 0 || discountPercentage > 100) {
            discountPercentage = 0;
        }

        return total - (total * discountPercentage / 100);
    }

    // Estimate preparation time
    public int estimatePreparationTime() {
        return 15 + (2 * toppingCount);
    }

    // Display order details
    public void displayOrderDetails() {
        System.out.println("Order ID: " + orderID);
        System.out.println("Customer Name: " + customerName);
        System.out.println("Pizza Size: " + size);
        System.out.println("Topping Count: " + toppingCount);
        System.out.println("Delivery: " +
                (isDelivery ? "Yes" : "No"));
        System.out.printf("Total Price: $%.2f%n",
                calculateTotalPrice());
        System.out.println("Estimated Preparation Time: "
                + estimatePreparationTime() + " minutes");
        System.out.println("--------------------------------");
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // First order
        PizzaOrder order1 = new PizzaOrder(
                "O3001", "Olivia Green", "Medium", 3, true);
        order1.displayOrderDetails();

        // Second order with discount
        PizzaOrder order2 = new PizzaOrder(
                "O3002", "Noah Smith", "Large", 2, false);
        order2.displayOrderDetails();

        System.out.print("Enter the discount percentage: ");
        double percentage = sc.nextDouble();

        System.out.printf(
                "Order 02 - Price after the discount: $%.2f%n",
                order2.calculateTotalPrice(percentage));

        System.out.println();

        // Third order using default constructor
        PizzaOrder order3 = new PizzaOrder();
        order3.displayOrderDetails();

        sc.close();
    }
}
