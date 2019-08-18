
import java.util.*;

public class PokerGame {
    private static final int T_VALUE = 10;
    private static final int J_VALUE = 11;
    private static final int Q_VALUE = 12;
    private static final int K_VALUE = 13;
    private static final int A_VALUE = 14;
    private static final String WINNER_ONE = "Winner:player1";
    private static final String WINNER_TWO = "Winner:player2";
    private static final String DRAW = "draw";

    private List<Card> changeStringtoCard(String player) {
        List<Card> cardList = new ArrayList<>();
        String[] cards = player.split(" ");
        Arrays.asList(cards).forEach(card -> cardList.add(createNewCard(card)));
        cardList.sort(Card::compareTo);
        return cardList;
    }

    private Card createNewCard(String card) {
        Map<String, Integer> CharWithNumber = initCharWithNumber();
        String type = card.substring(1, 2);
        String number = card.substring(0, 1);
        return CharWithNumber.get(number) != null ? new Card(CharWithNumber.get(number), type) : new Card(Integer.parseInt(number), type);
    }

    private Map<String, Integer> initCharWithNumber() {
        Map<String, Integer> CharWithNumber = new HashMap<>();
        CharWithNumber.put("T", T_VALUE);
        CharWithNumber.put("J", J_VALUE);
        CharWithNumber.put("Q", Q_VALUE);
        CharWithNumber.put("K", K_VALUE);
        CharWithNumber.put("A", A_VALUE);
        return CharWithNumber;
    }

    private boolean isSequence(List<Card> cards) {
        for (int i = 0; i < cards.size() - 1; i++)
            if (cards.get(i).getNumber() != cards.get(i + 1).getNumber() - 1)
                return false;
        return true;
    }

    private boolean isFlush(List<Card> cards) {
        for (int i = 0; i < cards.size() - 1; i++)
            if (!cards.get(i).getType().equals(cards.get(i + 1).getType()))
                return false;
        return true;
    }

    private Poker getRangeOfCardList(List<Card> cards) {
        NavigableMap<Integer, Integer> numberOfCard = initNumberOfCard(cards);
        int range = 0;
        boolean containsTwoEqual = false;
        boolean containsThreeEqual = false;
        for (Map.Entry<Integer, Integer> entry : numberOfCard.entrySet()) {
            switch (entry.getValue()) {
                case 2:
                    range++;
                    containsTwoEqual = true;
                    break;
                case 3:
                    range = 3;
                    containsThreeEqual = true;
                    break;
                case 4:
                    range = 7;
                default:
                    break;
            }
        }
        numberOfCard = numberOfCard.descendingMap();
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(numberOfCard.entrySet());
        list.sort((pre, cur) -> cur.getValue().compareTo(pre.getValue()));
        Poker poker = new Poker(range, list);
        setPokerRange(cards, containsTwoEqual, containsThreeEqual, poker);
        return poker;
    }

    private void setPokerRange(List<Card> cards, boolean containsTwoEqual, boolean containsThreeEqual, Poker poker) {
        if (this.isSequence(cards) && poker.getRange() < 4) poker.setRange(4);
        if (this.isFlush(cards) && poker.getRange() < 5) poker.setRange(5);
        if (containsTwoEqual && containsThreeEqual) poker.setRange(6);
        if (this.isFlush(cards) && this.isSequence(cards)) poker.setRange(8);
    }

    private NavigableMap<Integer, Integer> initNumberOfCard(List<Card> cards) {
        NavigableMap<Integer, Integer> numberOfCard = new TreeMap<>();
        for (Card card : cards) {
            Integer integer = numberOfCard.get(card.getNumber());
            numberOfCard.put(card.getNumber(), integer == null ? 1 : integer + 1);
        }
        return numberOfCard;
    }

    String getWinner(String player1, String player2) {
        List<Card> cardlist1 = this.changeStringtoCard(player1);
        List<Card> cardlist2 = this.changeStringtoCard(player2);
        Poker poker1 = this.getRangeOfCardList(cardlist1);
        Poker poker2 = this.getRangeOfCardList(cardlist2);
        if (poker1.getRange() == poker2.getRange()) {
            List<Map.Entry<Integer, Integer>> numberOfCard1 = poker1.getNumberOfCard();
            List<Map.Entry<Integer, Integer>> numberOfCard2 = poker2.getNumberOfCard();
            for (int i = 0; i < numberOfCard1.size(); i++) {
                if (numberOfCard1.get(i).getKey() > numberOfCard2.get(i).getKey()) {
                    return WINNER_ONE;
                } else if (numberOfCard1.get(i).getKey() < numberOfCard2.get(i).getKey()) {
                    return WINNER_TWO;
                }
            }
            return DRAW;
        }
        return poker1.getRange() > poker2.getRange()?WINNER_ONE:WINNER_TWO;
    }
}
