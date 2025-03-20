import java.util.*;

public class FindIt {
    private static Scanner scanner = new Scanner(System.in);
    private static List<Item> items = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==== FIND IT: Lost & Found Tracker ====");
            System.out.println("1. Post Lost/Found Item");
            System.out.println("2. Search for an Item");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1 -> postItem();
                case 2 -> searchItem();
                case 3 -> {
                    System.out.println("Exiting... Thank you for using Find It!");
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    private static void postItem() {
        System.out.print("Enter item name: ");
        String name = scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter status (Lost/Found): ");
        String status = scanner.nextLine();

        items.add(new Item(name, description, status));
        System.out.println("Item posted successfully!");
    }

    private static void searchItem() {
        System.out.print("Enter keyword to search: ");
        String keyword = scanner.nextLine().toLowerCase();

        System.out.println("\nMatching Results:");
        for (Item item : items) {
            if (item.getName().toLowerCase().contains(keyword)) {
                System.out.println(item);
            }
        }
    }
}

// Item class to store item details
class Item {
    private String name, description, status;

    public Item(String name, String description, String status) {
        this.name = name;
        this.description = description;
        this.status = status;
    }

    public String getName() { return name; }

    @Override
    public String toString() {
        return "Item: " + name + " | Status: " + status + " | " + description;
    }
}
