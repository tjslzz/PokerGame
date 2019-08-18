import java.util.List;
import java.util.Map;

class Poker {
    private int range;
    private List<Map.Entry<Integer, Integer>> numberOfCard;

    List<Map.Entry<Integer, Integer>> getNumberOfCard() {
        return numberOfCard;
    }

    Poker(int range, List<Map.Entry<Integer, Integer>> numberOfCard) {
        this.range = range;
        this.numberOfCard = numberOfCard;
    }

    void setRange(int range) {
        this.range = range;
    }

    int getRange() {
        return range;
    }
}
