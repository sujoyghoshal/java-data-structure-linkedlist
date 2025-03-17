import java.util.Scanner;

class Item {
    int itemId;
    String itemName;
    int quantity;
    double price;
    Item next;

    public Item(int itemId, String itemName, int quantity, double price) {
        this.itemId = itemId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

class InventoryManagementSystem {
    private Item head;

    public void addItemAtEnd(int itemId, String itemName, int quantity, double price) {
        Item newItem = new Item(itemId, itemName, quantity, price);
        if (head == null) {
            head = newItem;
            return;
        }
        Item temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newItem;
    }

    public void addItemAtBeginning(int itemId, String itemName, int quantity, double price) {
        Item newItem = new Item(itemId, itemName, quantity, price);
        newItem.next = head;
        head = newItem;
    }

    public void removeItem(int itemId) {
        if (head == null) return;
        
        if (head.itemId == itemId) {
            head = head.next;
            return;
        }
        
        Item temp = head;
        while (temp.next != null && temp.next.itemId != itemId) {
            temp = temp.next;
        }
        
        if (temp.next != null) {
            temp.next = temp.next.next;
        }
    }

    public void updateQuantity(int itemId, int newQuantity) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemId == itemId) {
                temp.quantity = newQuantity;
                return;
            }
            temp = temp.next;
        }
    }

    public void searchItemById(int itemId) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemId == itemId) {
                System.out.println("Item Found: " + temp.itemName + " | Quantity: " + temp.quantity + " | Price: " + temp.price);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item with ID " + itemId + " not found.");
    }

    public void searchItemByName(String itemName) {
        Item temp = head;
        while (temp != null) {
            if (temp.itemName.equalsIgnoreCase(itemName)) {
                System.out.println("Item Found: " + temp.itemName + " | Quantity: " + temp.quantity + " | Price: " + temp.price);
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item with Name " + itemName + " not found.");
    }

    public double calculateTotalInventoryValue() {
        double totalValue = 0;
        Item temp = head;
        while (temp != null) {
            totalValue += temp.quantity * temp.price;
            temp = temp.next;
        }
        return totalValue;
    }

    public void displayInventory() {
        if (head == null) {
            System.out.println("Inventory is empty.");
            return;
        }
        Item temp = head;
        while (temp != null) {
            System.out.println("ID: " + temp.itemId + ", Name: " + temp.itemName + ", Quantity: " + temp.quantity + ", Price: " + temp.price);
            temp = temp.next;
        }
    }
}

public class InventoryManagementSystems {
    public static void main(String[] args) {
        InventoryManagementSystem inventory = new InventoryManagementSystem();
        Scanner scanner = new Scanner(System.in);
        
        inventory.addItemAtEnd(101, "Laptop", 5, 70000);
        inventory.addItemAtEnd(102, "Mouse", 20, 500);
        inventory.addItemAtEnd(103, "Keyboard", 15, 1500);
        
        System.out.println("Inventory List:");
        inventory.displayInventory();
        
        System.out.println("\nUpdating Quantity of Item ID 102");
        inventory.updateQuantity(102, 25);
        inventory.displayInventory();
        
        System.out.println("\nSearching for Item ID 103:");
        inventory.searchItemById(103);
        
        System.out.println("\nTotal Inventory Value: " + inventory.calculateTotalInventoryValue());
        
        scanner.close();
    }
}