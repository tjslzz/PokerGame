
import java.util.*;

public class PokerGame {
    static final int T_VALUE = 10;
    static final int J_VALUE = 11;
    static final int Q_VALUE = 12;
    static final int K_VALUE = 13;
    static final int A_VALUE = 14;
    static final String WINNER_ONE = "Winner:player1";
    static final String WINNER_TWO = "Winner:player2";
    static final String DRAW = "draw";

    private List<Card> changeStringtoCard(String player1) {
        Map<String, Integer> CharWithNumber = new HashMap<>();
        CharWithNumber.put("T", T_VALUE);
        CharWithNumber.put("J", J_VALUE);
        CharWithNumber.put("Q", Q_VALUE);
        CharWithNumber.put("K", K_VALUE);
        CharWithNumber.put("A", A_VALUE);
        List<Card> cardList = new ArrayList<>();
        String[] cards = player1.split(" ");
        for (String card : cards) {
            int actualNumber;
            String type = card.substring(1, 2);
            String number = card.substring(0, 1);
            if (CharWithNumber.get(number) != null) {
                actualNumber = CharWithNumber.get(number);
            } else {
                actualNumber = Integer.parseInt(number);
            }
            cardList.add(new Card(actualNumber, type));
        }
        Collections.sort(cardList, new Comparator<Card>() {
            @Override
            public int compare(Card c1, Card c2) {
                if (c1.getNumber() > c2.getNumber()) {
                    return 1;
                }
                if (c1.getNumber() == c2.getNumber()) {
                    return 0;
                }
                return -1;
            }
        });
        return cardList;
    }

    private boolean isSequence(List<Card> cards) {
        boolean isSequence = true;
        for (int i = 0; i < cards.size() - 1; i++) {
            if (cards.get(i).getNumber() != cards.get(i + 1).getNumber() - 1)
                isSequence = false;
        }
        return isSequence;
    }

    private boolean isFlush(List<Card> cards) {
        boolean isFlush = true;
        for (int i = 0; i < cards.size() - 1; i++) {
            if (!cards.get(i).getType().equals(cards.get(i + 1).getType())) {
                isFlush = false;
            }
        }
        return isFlush;
    }

    private Poker getRangeOfCardList(List<Card> cards) {
        Map<Integer, Integer> numberOfCard = new TreeMap<>();
        int range = 0;
        boolean containsTwoEqual = false;
        boolean containsThreeEqual = false;
        for (int i = 0; i < cards.size(); i++) {
            Integer integer = numberOfCard.get(cards.get(i).getNumber());
            numberOfCard.put(cards.get(i).getNumber(), integer == null ? 1 : integer + 1);
        }
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
        numberOfCard = ((TreeMap) numberOfCard).descendingMap();
        List<Map.Entry<Integer, Integer>> list = new ArrayList<Map.Entry<Integer, Integer>>(numberOfCard.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer>>() {
            public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        Poker poker = new Poker(range, list);
        if (this.isSequence(cards) && poker.getRange() < 4) {
            poker.setRange(4);
        }
        if (this.isFlush(cards) && poker.getRange() < 5) {
            poker.setRange(5);
        }
        if (containsTwoEqual && containsThreeEqual) poker.setRange(6);
        if (this.isFlush(cards) && this.isSequence(cards)) poker.setRange(8);
        return poker;
    }

    String getWinner(String player1, String player2) {
        List<Card> cardlist1 = this.changeStringtoCard(player1);
        List<Card> cardlist2 = this.changeStringtoCard(player2);
        Poker poker1 = this.getRangeOfCardList(cardlist1);
        Poker poker2 = this.getRangeOfCardList(cardlist2);
        String result = DRAW;
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

        } else if (poker1.getRange() > poker2.getRange()) {
            result = WINNER_ONE;
        } else {
            result = WINNER_TWO;
        }
        return result;
    }
}
