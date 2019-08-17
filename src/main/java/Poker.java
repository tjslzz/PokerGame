import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Poker {
    List<Card>cardList;
    int range;
    List<Map.Entry<Integer,Integer>>numberOfCard;

    public List<Map.Entry<Integer, Integer>> getNumberOfCard() {
        return numberOfCard;
    }

    public Poker(List<Card> cardList, int range, List<Map.Entry<Integer, Integer>> numberOfCard) {
        this.cardList = cardList;
        this.range = range;
        this.numberOfCard = numberOfCard;
    }

    public int getRange() {
        return range;
    }
}
