import java.util.List;

public class Poker {
    List<Card>cardList;
    int range;

    public Poker(List<Card> cardList, int range) {
        this.cardList = cardList;
        this.range = range;
    }

    public int getRange() {
        return range;
    }
}
