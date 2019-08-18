public class Card {
    private int number;
    private String type;

    Card(int number, String type) {
        this.number = number;
        this.type = type;

    }

    int getNumber() {
        return number;
    }

    String getType() {
        return type;
    }

    int compareTo(Card card) {
        if (number == card.getNumber()) return 0;
        return number > card.getNumber() ? 1 : -1;
    }
}
