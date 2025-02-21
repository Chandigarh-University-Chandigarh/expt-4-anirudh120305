import java.util.*;

class Card {
    String symbol;
    String value;

    public Card(String symbol, String value) {
        this.symbol = symbol;
        this.value = value;
    }

    @Override
    public String toString() {
        return "[Symbol: " + symbol + ", Value: " + value + "]";
    }
}

public class CardCollection {
    private static Collection<Card> cards = new ArrayList<>();

    public static void addCard(String symbol, String value) {
        cards.add(new Card(symbol, value));
        System.out.println("Card added successfully!");
    }

    public static void findCardsBySymbol(String symbol) {
        boolean found = false;
        for (Card card : cards) {
            if (card.symbol.equalsIgnoreCase(symbol)) {
                System.out.println(card);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No cards found with symbol: " + symbol);
        }
    }

    public static void displayAllCards() {
        if (cards.isEmpty()) {
            System.out.println("No cards available.");
        } else {
            for (Card card : cards) {
                System.out.println(card);
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Add Card\n2. Find Cards by Symbol\n3. Display All Cards\n4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    System.out.print("Enter Card Symbol: ");
                    String symbol = scanner.nextLine();
                    System.out.print("Enter Card Value: ");
                    String value = scanner.nextLine();
                    addCard(symbol, value);
                    break;
                case 2:
                    System.out.print("Enter Symbol to search: ");
                    String searchSymbol = scanner.nextLine();
                    findCardsBySymbol(searchSymbol);
                    break;
                case 3:
                    displayAllCards();
                    break;
                case 4:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
